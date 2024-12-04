package ui.room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Random;

import card.CardTarget;
import card.CardType;
import characterStatus.Health;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameEntity.characters.Character;
import card.Card;
import card.CardEffect;
import characterStatus.Energy;
import gameEntity.monsters.Monster;
import gameEntity.monsters.weak.*;
import json.SaveData;
import statusEffect.StatusEffect;
import ui.battleResult.GameOver;
import ui.GameState;
import ui.battleResult.Rewards;

import static statusEffect.StatusEffect.Block;

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
    private boolean selectCharacter;
    private CardTarget[] cardTarget;
    private int monsterX;
    private int monsterY;
    private int check;
    private int selectedCard2 = -2;
    private ImageIcon blockImageIcon;
    private int monsterCount;
    private int checkKilledMonsters;

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
        checkKilledMonsters = monsters.size();
        System.out.println(checkKilledMonsters);

        //이미지 아이콘 추가 -> paintComponent
        characterImageIcon = scaleImage(character.getImagePath(), 270, 200);
        backgroundImageIcon = scaleImage("src/imgs/scene1.png", 1600, 1150);
        energyImageIcon = scaleImage("src/imgs/energyRedVFX.png", 200, 200);
        blockImageIcon = scaleImage("src/imgs/block.png", 55, 55);

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
                           if(selectedCard != -1){
                               selectedCard2 = i;
                               break;
                           } else {
                               selectedCard = i;
                               cardTarget = handPile.get(selectedCard).getCardTarget();
                               repaint();
                               break;
                           }
                       }
                   } else {
                           if (mouseX >= cardLeft[0] && mouseX <= cardLeft[0] + cardLeft[2] && mouseY >= cardLeft[1] && mouseY <= cardLeft[1] + cardLeft[3]) {
                               if(selectedCard != -1){
                                   selectedCard2 = i;
                                   break;
                               } else {
                                   selectedCard = i;
                                   cardTarget = handPile.get(selectedCard).getCardTarget();
                                   repaint();
                                   break;
                               }
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

               //캐릭터 선택
               selectCharacter = false;
               if (mouseX >= character.getX() && mouseX <= character.getX() + characterImageIcon.getIconWidth() && mouseY >= character.getY() && mouseY <= character.getY() + characterImageIcon.getIconHeight()){
                   selectCharacter = true;
               }


               //카드 선택, 몬스터 선택 or 카드 선택, 캐릭터 선택
               if ((selectedMonster != -1 && selectedCard != -1) || (selectCharacter && selectedCard != -1) || (selectedCard2 == selectedCard)){
                   for (int i=0; i<cardTarget.length; i++){
                       if (cardTarget[i] == CardTarget.Monster && selectedMonster != -1){
                           useCardToMonster();
                       } else if (cardTarget[i] == CardTarget.Character && selectCharacter){
                           useCardToCharacter();
                       } else if (cardTarget[i] == CardTarget.None && selectedCard2 != -1){
                           useCard();
                       }
                   }
               }


                //몬스터 먼저 선택 시 무효
               if (selectedMonster != -1){
                   selectedMonster = -1;
                   selectedCard = -1;
                   repaint();

               } //캐릭터 먼저 선택 시 무효
               else if (selectCharacter) {
                   selectCharacter = false;
                   selectedCard = -1;
                   repaint();
               }
               //다른 카드 선택 시 무효
               else if (selectedCard2 != -2 && selectedCard != -1 && selectedCard2 != selectedCard){
                   selectedCard2 = -2;
                   selectedCard = -1;
                   repaint();
               }
           }

        });
    }

    //카드 발동
    public void useCard(){
        character.useCard(handPile.get(selectedCard));

        selectedCard = -1;
        selectedCard2 = -2;
        repaint();
    }

    //몬스터 타겟 카드 발동
    public void useCardToMonster() {
        character.useCard(handPile.get(selectedCard), monsters.get(selectedMonster));

        selectedMonster = -1;
        selectedCard = -1;
        repaint();

        //몬스터 다 죽었는지 확인
        if (monsters.isEmpty()) {
            handPileToDiscardPile(new Runnable() {
                @Override
                public void run() {
                    discardPileToDrawPile(new Runnable() {
                        @Override
                        public void run() {
                            character.clearTemporaryCards();
                        }
                    });
                }
            });

            character.endBattle();
            System.out.println(gameState.getKilledMonsters()); System.out.println(checkKilledMonsters);
            gameState.setKilledMonsters(gameState.getKilledMonsters() + checkKilledMonsters);

            layeredPane.setBounds(0, 0, getWidth(), getHeight());

            Rewards rewardsPanel = new Rewards();
            rewardsPanel.setBounds(0, 0, getWidth(), getHeight());

            layeredPane.add(rewardsPanel, JLayeredPane.POPUP_LAYER);
            add(layeredPane, 0); // 맨 위에 추가

            revalidate(); // 레이아웃 갱신
            repaint();    // 화면 다시 그리기

            ObjectMapper mapper = new ObjectMapper();
            SaveData saveData = new SaveData();
            try {
                File file = new File("saveData.json");
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, saveData);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    //캐릭터 타겟 카드 발동
    public void useCardToCharacter() {
        character.useCard(handPile.get(selectedCard));

        selectCharacter = false;
        selectedCard = -1;
        repaint();
    }

    //(턴 종료 시) 손에 있는 모든 카드 -> 버린 카드 목록으로
    public void handPileToDiscardPile(Runnable onComplete){
        handCount = handPile.size();

        timer = new Timer(100, e->{
            discardPile.add(handPile.removeFirst());
            handCount -= 1;
            repaint();

            if(handCount <= 0){
                ((Timer) e.getSource()).stop();
                onComplete.run();
            }
        });

        timer.start();

    }

    //(뽑을 카드 목록이 비었을 때) 버린 카드에 있는 모든 카드 -> 뽑을 카드 목록으로
    public void discardPileToDrawPile(Runnable onComplete){
        Collections.shuffle(discardPile);

        drawPile.addAll(discardPile);
        discardPile.clear();
        discardCount--;
        repaint();

        onComplete.run();
    }

    //(내 턴 시작 시) 뽑을 카드에 있는 일부 카드 -> 손에 있는 카드 목록으로
    public void drawCards(Runnable onComplete){
        cardCount = character.getCardCount();

        if (gameState.isMyTurn()){

            Collections.shuffle(drawPile);

            timer = new Timer(100, e->{
                if (drawPile.isEmpty()){
                    discardPileToDrawPile(() -> { });
                } else {
                    handPile.add(drawPile.removeFirst());
                    cardCount--;
                    repaint();
                }

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


        int characterBuffX = character.getX();
        int characterBuffY = character.getY() + characterImageIcon.getIconHeight() + 50;

        //캐릭터 버프
        for (StatusEffect statusEffect : character.getStatusEffects().keySet()){
            Integer statusEffectAmount = character.getStatusEffects().get(statusEffect);
            if (statusEffect == Block && character.getStatusEffects().get(Block) > 0) {
                g.drawImage(blockImageIcon.getImage(), character.getX() - 20, character.getY() + characterImageIcon.getIconHeight() - 15, null);
                g.setColor(Color.BLACK);
                g.drawString(character.getStatusEffects().get(Block) + "", character.getX(), character.getY() + (characterImageIcon.getIconHeight()) + 15);
            } else {
                if (statusEffectAmount > 0) {
                    g.setColor(Color.WHITE);
                    g.drawString(statusEffect.getName() + statusEffectAmount, characterBuffX, characterBuffY );
                    characterBuffX += 50;
                }
            }
        }

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
            monsterX = monster.getX();
            monsterY = monster.getY();
            g.drawImage(monsterImageIcons[i].getImage(), monsterX, monsterY,null);

            //몬스터 공격력
            if (gameState.getTurnCount() % 2 == 0){
                g.drawString(monster.getDamage() + "", monsterX + monsterImageIcons[i].getIconWidth() / 2, monsterY);
            }

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


            int monsterBuffX = monster.getX();
            int monsterBuffY = monster.getY() + monsterImageIcons[i].getIconHeight() + 50;
            //몬스터 버프
            for (StatusEffect statusEffect : monster.getStatusEffects().keySet()){
                Integer statusEffectAmount = monster.getStatusEffects().get(statusEffect);
                if (statusEffect == Block && monster.getStatusEffects().get(Block) > 0) {
                    g.drawImage(blockImageIcon.getImage(), monster.getX() - 20, monster.getY() + monsterImageIcons[i].getIconHeight() - 15, null);
                    g.setColor(Color.BLACK);
                    g.drawString(monster.getStatusEffects().get(Block) + "", monster.getX(), monster.getY() + (monsterImageIcons[i].getIconHeight()) + 15);
                } else {
                    if (statusEffectAmount > 0) {
                        g.setColor(Color.WHITE);
                        g.drawString(statusEffect.getName() + statusEffectAmount, monsterBuffX, monsterBuffY);
                        monsterBuffX += 50;
                    }
                }
            }

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
            if (i == selectedCard) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.WHITE);
            }
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
                    monsterCount = 0;

                    timer = new Timer(500, e->{
                        if (monsterCount < monsters.size()) {
                            monsters.get(monsterCount).performTurn();
                            repaint();
                            gameState.getStatusBar().updateStatusBarHealth();
                            repaint();

                            //캐릭터 죽었는지 확인
                            if (character.getHealth().getCurrentHealth() <= 0) {
                                checkKilledMonsters = checkKilledMonsters - monsters.size();
                                gameState.setKilledMonsters(gameState.getKilledMonsters() + checkKilledMonsters);
                                layeredPane.setBounds(0, 0, getWidth(), getHeight());

                                JPanel gameOverPanel = new GameOver();
                                gameOverPanel.setBounds(0, 0, getWidth(), getHeight());

                                layeredPane.add(gameOverPanel, JLayeredPane.POPUP_LAYER);
                                add(layeredPane, 0);

                                revalidate();
                                repaint();
                            }

                            monsterCount += 1;
                        } else {
                            timer.stop();

                            if (drawPile.isEmpty()){
                                discardPileToDrawPile(() -> {});
                            }
                            gameState.setTurnCount(gameState.getTurnCount()+1);
                            drawCards(new Runnable(){
                                @Override
                                public void run() {
                                    character.getEnergy().setCurrentEnergy(character.getEnergy().getMaxEnergy());
                                    character.reduceEffectAmount();
                                    endTurn.setEnabled(true);
                                }
                            });
                            repaint();
                        }
                    });
                    timer.start();

                }
            });

        }
    }
}
