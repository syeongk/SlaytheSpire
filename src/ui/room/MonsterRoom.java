package ui.room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Random;

import characterStatus.Health;
import gameEntity.characters.Character;
import card.Card;
import card.CardEffect;
import characterStatus.Energy;
import gameEntity.monsters.Monster;
import gameEntity.monsters.weak.*;
import ui.battleResult.GameOver;
import ui.GameState;
import ui.battleResult.Rewards;

public class MonsterRoom extends JPanel implements ActionListener {

    private GameState gameState;
    private Character character;
    private Energy energy;
    private ImageIcon[] monsterImageIcons;
    private LinkedList<Card> drawPile;
    private LinkedList<Card> handPile;
    private LinkedList<Card> discardPile;
    private int cardCount;
    private ImageIcon characterImageIcon;
    private ImageIcon backgroundImageIcon;
    private LinkedList<Monster> monsters;
    private ImageIcon energyImageIcon;
    private JButton endTurn;
    private ArrayList<int[]> handCardsLocation;
    private int selectedCard = -1;
    private ArrayList<int[]> monstersLocation;
    private int selectedMonster = -1;
    private Timer timer;
    private int discardCount;
    private int handCount;
    private JLayeredPane layeredPane;

    public MonsterRoom(){
        setLayout(null);
        setOpaque(false);

        handCardsLocation = new ArrayList<>();
        monstersLocation = new ArrayList<>();

        layeredPane = new JLayeredPane();

        //GameState, Character 정보 가져오기
        this.gameState = GameState.getInstance();
        this.character = gameState.getCharacter();

        //상태바 추가
        add(gameState.getStatusBar(), BorderLayout.NORTH);

        //캐릭터 에너지, 카드 정보 가져오기
        energy = character.getEnergy();
        drawPile = character.getDrawPile();
        handPile = character.getHandPile();
        discardPile = character.getDiscardPile();

        //턴 종료 버튼
        endTurn = new JButton(scaleImage("src/imgs/endTurnButton.png", 200,200));
        endTurn.setBounds(1300, 550, 200, 200);
        endTurn.setBorderPainted(false);
        endTurn.setFocusPainted(false);
        endTurn.setHorizontalTextPosition(SwingConstants.CENTER);
        endTurn.setText("턴 종료");
        endTurn.addActionListener(this);
        add(endTurn);

        //몬스터 랜덤 생성
        monsters = gameState.getMonsters();
        Random random = new Random();
        int monsterSelection = random.nextInt(1,5);
        switch (monsterSelection) {
            case 1:
                Monster cultist = new Cultist();
                monsters.add(cultist);
                break;
            case 2:
                Monster jawWorm = new JawWorm();
                monsters.add(jawWorm);
                break;
            case 3:
                Monster louseG = new LouseG();
                monsters.add(louseG);
                Monster louseR = new LouseR();
                monsters.add(louseR);
                break;
            case 4:
                Monster slimeM = new SlimeM();
                monsters.add(slimeM);
                Monster slimeS = new SlimeS();
                monsters.add(slimeS);
                break;
        }

        //이미지 아이콘 추가 -> paintComponent
        characterImageIcon = scaleImage(character.getImagePath(), 270, 200);
        backgroundImageIcon = scaleImage("src/imgs/scene1.png", 1600, 1150);
        energyImageIcon = scaleImage("src/imgs/energyRedVFX.png", 200, 200);

        drawCards(() -> { });

        //마우스 이벤트 처리
        addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e){
               int mouseX = e.getX();
               int mouseY = e.getY();

               //카드 선택
               for (int i=0; i<handCardsLocation.size(); i++){
                   int[] cardLeft = handCardsLocation.get(i);
                   if (i+1 < handCardsLocation.size()) {
                       int[] cardRight = handCardsLocation.get(i + 1);
                       if (mouseX >= cardLeft[0] && mouseX <= (cardLeft[0] + cardLeft[2]) - (cardLeft[0] + cardLeft[2] - cardRight[0]) && mouseY >= cardLeft[1] && mouseY <= cardLeft[1] + cardLeft[3]) {
                           selectedCard = i;
                           break;
                       }
                   } else {
                           if (mouseX >= cardLeft[0] && mouseX <= cardLeft[0] + cardLeft[2] && mouseY >= cardLeft[1] && mouseY <= cardLeft[1] + cardLeft[3]) {
                               selectedCard = i;
                           }
                       }

               }

               //몬스터 선택
               selectedMonster = -1;
               for (int i=0; i<monstersLocation.size(); i++){
                   int[] monsterLocation = monstersLocation.get(i);
                   if (mouseX >= monsterLocation[0] && mouseX <= monsterLocation[0] + monsterLocation[2] && mouseY >= monsterLocation[1] && mouseY <= monsterLocation[1] + monsterLocation[3]){
                       selectedMonster = i;
                   }
               }

               //카드, 몬스터 선택 시 카드 효과 발동
               if (selectedCard != -1 && selectedMonster != -1) {
                   character.useCard(handPile.get(selectedCard), monsters.get(selectedMonster));

                   selectedMonster = -1;
                   selectedCard = -1;
                   repaint();

                   if (monsters.isEmpty()) {
                       handPileToDiscardPile(new Runnable() {
                           @Override
                           public void run() {
                               discardPileToDrawPile();
                           }
                       });
                       Energy energy = character.getEnergy();
                       energy.setCurrentEnergy(energy.getMaxEnergy());

                       layeredPane.setBounds(0, 0, getWidth(), getHeight());

                       Rewards rewardsPanel = new Rewards();
                       rewardsPanel.setBounds(0, 0, getWidth(), getHeight());

                       layeredPane.add(rewardsPanel, JLayeredPane.POPUP_LAYER);
                       add(layeredPane, 0); // 맨 위에 추가

                       revalidate(); // 레이아웃 갱신
                       repaint();    // 화면 다시 그리기
                   }

               } //몬스터 먼저 선택 시 무효
               else if (selectedCard == -1 && selectedMonster != -1){
                   selectedMonster = -1;
                   selectedCard = -1;
               }

           }

        });
    }

    //(턴 종료 시) 손에 있는 모든 카드 -> 버린 카드 목록으로
    public void handPileToDiscardPile(Runnable onComplete){
        handCount = handPile.size();

        timer = new Timer(100, e->{
            discardPile.add(handPile.removeFirst());
            handCount--;
            repaint();

            if(handCount <= 0){
                ((Timer) e.getSource()).stop();
                onComplete.run();
            }
        });

        timer.start();

    }

    //(뽑을 카드 목록이 비었을 때) 버린 카드에 있는 모든 카드 -> 뽑을 카드 목록으로
    public void discardPileToDrawPile(){
        Collections.shuffle(discardPile);

        drawPile.addAll(discardPile);
        discardPile.clear();
        discardCount--;
        repaint();
    }

    //(내 턴 시작 시) 뽑을 카드에 있는 일부 카드 -> 손에 있는 카드 목록으로
    public void drawCards(Runnable onComplete){
        cardCount = character.getCardCount();

        if (gameState.isMyTurn()){
            if (drawPile.isEmpty()){
                discardPileToDrawPile();
            }

            Collections.shuffle(drawPile);

            timer = new Timer(100, e->{
                handPile.add(drawPile.removeFirst());
                cardCount--;
                repaint();

                if(cardCount <= 0){
                    ((Timer) e.getSource()).stop();
                    onComplete.run();
                }
            });

            timer.start();

        }
    }

    //그리기
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //배경 - 배경이 그려진 후 나머지가 그려져야 함
        g.drawImage(backgroundImageIcon.getImage(), 0, -250, null);

        //에너지 상태
        int energyX = 100;
        int energyY = 600;
        int energyW = energyImageIcon.getIconWidth();
        int energyH = energyImageIcon.getIconHeight();
        g.drawImage(energyImageIcon.getImage(), energyX, energyY, null);

        Font font = new Font("Arial", Font.BOLD, 30);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(energy.getCurrentEnergy() + "/" + energy.getMaxEnergy(), energyX + (energyW / 2) - 20, energyY + (energyH/ 2) + 10);


        //캐릭터
        g.drawImage(characterImageIcon.getImage(), character.getX(), character.getY(), null);
        Health characterHealth = character.getHealth();

        //캐릭터 체력바
        g.setColor(Color.RED);
        g.fillRoundRect(character.getX(), character.getY() + characterImageIcon.getIconHeight() + 10, characterImageIcon.getIconWidth(), 15,15, 15);
        g.setColor(Color.WHITE);
        font = new Font("Arial", Font.PLAIN, 15);
        g.setFont(font);
        g.drawString((characterHealth.getCurrentHealth()) + "/" + characterHealth.getMaxHealth(), character.getX() + (characterImageIcon.getIconWidth() / 2) - 20, character.getY() + (characterImageIcon.getIconHeight()) + 15 );

        //몬스터
        font = new Font("Arial", Font.PLAIN, 15);
        g.setFont(font);
        monstersLocation.clear();

        monsterImageIcons = new ImageIcon[monsters.size()];
        for (int i=0; i<monsters.size(); i++){
            monsterImageIcons[i] = scaleImage(monsters.get(i).getImagePath(), 180, 180);
        }

        for (int i=0; i<monsters.size(); i++){
            //몬스터 이미지
            Monster monster = monsters.get(i);
            int monsterX = monster.getX();
            int monsterY = monster.getY();
            g.drawImage(monsterImageIcons[i].getImage(), monsterX, monsterY,null);

            //몬스터 체력바
            int healthBarX = monsterX;
            int healthBarY = monsterY + monsterImageIcons[i].getIconHeight() + 15;
            int healthBarW = monsterImageIcons[i].getIconWidth();
            int healthBarH = 15;

            //몬스터 체력
            g.setColor(Color.RED);
            g.fillRoundRect(healthBarX, healthBarY, healthBarW, healthBarH,15, 15);
            g.setColor(Color.WHITE);
            g.drawString((monster.getCurrentHealth()) + "/" + monster.getMaxHealth(), healthBarX + (healthBarW / 2) - 20, healthBarY + (healthBarH / 2) );

            //몬스터 좌표 저장
            monstersLocation.add(new int[]{monsterX, monsterY, monsterImageIcons[i].getIconWidth(), monsterImageIcons[i].getIconHeight()});
        }


        //뽑을 카드 목록
        int drawPileX = 20;
        int drawPileY = 750;
        int drawPileW = 100;
        int drawPileH = 100;
        ImageIcon drawPileImage = scaleImage("src/imgs/base1.png", drawPileW, drawPileH);
        g.drawImage(drawPileImage.getImage(), drawPileX, drawPileY, null);

        font = new Font("Arial", Font.BOLD, 20);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(Integer.toString(drawPile.size()), drawPileX + drawPileW / 2 - 10, drawPileY + drawPileH / 2 + 5);

        //버린 카드 목록
        int discardPileX = 1480;
        int discardPileY = 750;
        int discardPileW = 100;
        int discardPileH = 100;
        ImageIcon discardPileImage = scaleImage("src/imgs/base2.png", discardPileW, discardPileH);
        g.drawImage(discardPileImage.getImage(), discardPileX, discardPileY, null);

        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(Integer.toString(discardPile.size()), discardPileX + discardPileW / 2 - 10, discardPileY + discardPileH / 2 + 5);

        //손에 있는 카드 목록
        int handCardX = 650 - (40 * (handPile.size()));
        int handCardY = 650;
        int handCardW = 200;
        int handCardH = 300;


        float handPileSize = handPile.size();
        handCardsLocation.clear();
        for (int i = 0; i < handPileSize; i++) {

            int centerCard = Math.round(handPileSize / 2 - 1);
            double angle = (i - centerCard) * 3;
            double radian = Math.toRadians(angle);

            handCardY = 650;

            if (i < centerCard) {
                handCardY += (centerCard - i) * 20;
            } else if (i > centerCard) {
                handCardY += (i - centerCard) * 20;
            }

            g2d.rotate(radian, handCardX + handCardW / 2.0, handCardY + handCardH / 2.0); //카드 회전

            //카드 그리기
            g2d.setColor(Color.BLACK);
            g2d.fillRect(handCardX, handCardY, handCardW, handCardH);
            g2d.setColor(Color.WHITE);
            g2d.drawRect(handCardX, handCardY, handCardW, handCardH);
            handCardsLocation.add(new int[]{handCardX, handCardY, handCardW, handCardH});

            //카드 에너지
            g2d.setColor(Color.BLACK);
            g2d.fillOval(handCardX - 8, handCardY - 8, 35,35);
            g2d.setColor(Color.WHITE);
            g2d.drawOval(handCardX - 8, handCardY - 8, 35,35);
            g2d.setColor(Color.WHITE);
            g2d.drawString(handPile.get(i).getEnergyCost() + "", handCardX + 5 , handCardY + 15);

            //카드 이름 작성
            g2d.setColor(Color.WHITE);
            font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            g2d.drawString(handPile.get(i).getCardName(), handCardX + (handCardW / 2 - 10), handCardY + 30);
            g2d.drawLine(handCardX, handCardY + 50, handCardX + handCardW, handCardY + 50);
            //g2d.drawLine(handCardX, handCardY + (handCardH / 2 + 30), handCardX + handCardW, handCardY + (handCardH / 2 + 30));

            //카드 효과 작성
            font = new Font("Arial", Font.BOLD, 15);
            g.setFont(font);
            ArrayList<CardEffect> cardEffects = handPile.get(i).getCardEffectList();
            int cardEffectX = handCardX + 10;
            int cardEffectY = handCardY + 100;
            for (CardEffect cardEffect : cardEffects) {
                g2d.drawString(cardEffect.getCardEffectContent(), cardEffectX, cardEffectY);
                cardEffectY += 20;
            }

            g2d.rotate(-radian, handCardX + handCardW / 2.0, handCardY + handCardH / 2.0); //회전 초기화

            handCardX += (int) (20 - handPileSize) * 10;

        }

    }

    //이미지 크기 조절
    public ImageIcon scaleImage(String imagePath, int w, int h){
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaleImage = originalIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(scaleImage);

        return scaleIcon;
    }

    //버튼 이벤트 처리
    @Override
    public void actionPerformed(ActionEvent e) {
        //턴 종료 버튼 누를 시
        if (e.getSource() == endTurn){
            endTurn.setEnabled(false);

            //handPileToDiscardPile 메소드가 끝난 후 나머지 명령어 처리
            handPileToDiscardPile(new Runnable() {
                @Override
                public void run(){
                    //몬스터 턴
                    gameState.setTurnCount(gameState.getTurnCount()+1);
                    for (Monster monster : monsters){
                        monster.performTurn();
                        gameState.getStatusBar().updateStatusBarHealth();

                        if (character.getHealth().getCurrentHealth() <= 0) {
                            layeredPane.setBounds(0, 0, getWidth(), getHeight());

                            JPanel gameOverPanel = new GameOver();
                            gameOverPanel.setBounds(0, 0, getWidth(), getHeight());

                            layeredPane.add(gameOverPanel, JLayeredPane.POPUP_LAYER);
                            add(layeredPane, 0);

                            revalidate();
                            repaint();
                        }
                    }
                    repaint();

                    //캐릭터 턴
                    if (drawPile.isEmpty()){
                        discardPileToDrawPile();
                    }
                    gameState.setTurnCount(gameState.getTurnCount()+1);
                    drawCards(new Runnable(){
                        @Override
                        public void run() {
                            endTurn.setEnabled(true);
                        }
                    });
                    character.getEnergy().setCurrentEnergy(character.getEnergy().getMaxEnergy());
                    repaint();

                }
            });

        }
    }
}
