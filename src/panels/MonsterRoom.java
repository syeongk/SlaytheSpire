package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Random;

import characters.Character;
import card.Card;
import card.CardEffect;
import characterStatus.Energy;
import monsters.Monster;
import monsters.weak.*;

public class MonsterRoom extends JPanel {

    private GameState gameState;
    private Character character;
    private Energy energy;
    private ImageIcon[] monsterImageIcons;
    private LinkedList<Card> drawPile;
    private LinkedList<Card> handPile;
    private LinkedList<Card> discardPile;
    private int i;
    private ImageIcon characterImageIcon;
    private ImageIcon backgroundImageIcon;
    private LinkedList<Monster> monsters;

    public MonsterRoom(){

        setLayout(new BorderLayout());
        setOpaque(false);

        //GameState, Charater 정보 가져오기
        this.gameState = GameState.getInstance();
        this.character = gameState.getCharacter();

        //상태바 추가
        add(gameState.getStatusBar(), BorderLayout.NORTH);

        //캐릭터 에너지, 카드 정보 가져오기
        energy = character.getEnergy();
        drawPile = character.getDrawPile();
        handPile = character.getHandPile();
        discardPile = character.getDiscardPile();

        //몬스터 랜덤 생성
        monsters = new LinkedList<>();
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

        monsterImageIcons = new ImageIcon[monsters.size()];
        for (int i=0; i<monsters.size(); i++){
            monsterImageIcons[i] = scaleImage(monsters.get(i).getImagePath(), 180, 180);
        }

        characterImageIcon = scaleImage(character.getImagePath(), 270, 200);
        backgroundImageIcon = scaleImage("src/imgs/scene1.png", 1600, 1150);


        addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e){
               int mouseX = e.getX();
               int mouseY = e.getY();
               System.out.println(mouseX);
               System.out.println(mouseY);
           }
        });

        drawCards();
    }


    public void drawCards(){
        if (gameState.isMyTurn()){
            Collections.shuffle(drawPile);

            Timer timer = new Timer(150, e->{
                handPile.add(drawPile.removeFirst());
                i += 1;
                repaint();

                if (i>=5){
                    ((Timer) e.getSource()).stop();
                }
            });

            timer.start();

        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //에너지 상태
        int energyX = 150;
        int energyY = 610;
        int energyW = 100;
        int energyH = 100;

        g.setColor(Color.BLACK);
        g.fillOval(energyX, energyY, energyW, energyH);

        Font font = new Font("Arial", Font.BOLD, 30);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(energy.getCurrentEnergy() + "/" + energy.getMaxEnergy(), energyX + energyW / 2 - 20, energyY + energyH / 2 + 10);


        //배경
        g.drawImage(backgroundImageIcon.getImage(), 0, -250, null);

        //캐릭터
        g.drawImage(characterImageIcon.getImage(), character.getX(), character.getY(), null);


        //몬스터
        for (int i=0; i<monsters.size(); i++){
            g.drawImage(monsterImageIcons[i].getImage(), monsters.get(i).getX(), monsters.get(i).getY(),null);
        }


        //뽑을 카드
        int drawPileX = 50;
        int drawPileY = 750;
        int drawPileW = 70;
        int drawPileH = 70;
        g.setColor(Color.BLACK);
        g.fillRect(drawPileX, drawPileY, drawPileW, drawPileH);

        font = new Font("Arial", Font.BOLD, 20);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(Integer.toString(drawPile.size()), drawPileX + drawPileW / 2 - 10, drawPileY + drawPileH / 2 + 5);

        //버린 카드
        int discardCardX = 1500;
        int discardCardY = 750;
        int discardCardW = 70;
        int discardCardH = 70;
        g.setColor(Color.BLACK);
        g.fillRect(discardCardX, discardCardY, discardCardW, discardCardH);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(Integer.toString(discardPile.size()), discardCardX + discardCardW / 2 - 10, discardCardY + discardCardH / 2 + 5);

        //현재 카드
        int handCardX = 650 - (40 * (handPile.size()));
        int handCardY = 650;
        int handCardW = 200;
        int handCardH = 300;


/*
        for (Card card : handPile){
            handCardX += 100;
            g.setColor(Color.BLACK);
            g.drawRect(handCardX, handCardY, handCardW, handCardH);
            g.setColor(Color.WHITE);
            g.fillRect(handCardX, handCardY, handCardW, handCardH);
        }
*/


        for (int i = 0; i < handPile.size(); i++) {
            float handPileSize = handPile.size();
            int centerCard = Math.round(handPileSize / 2 - 1);
            double angle = (i - centerCard) * 5;
            double radian = Math.toRadians(angle);

            handCardY = 650;

            if (i < centerCard) {
                handCardY += (centerCard - i) * 20;
            } else if (i > centerCard) {
                handCardY += (i - centerCard) * 20;
            }

            g2d.rotate(radian, handCardX + handCardW / 2.0, handCardY + handCardH / 2.0); //카드 회전

            //카드 그리기
            g2d.setColor(Color.WHITE);
            g2d.fillRect(handCardX, handCardY, handCardW, handCardH);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(handCardX, handCardY, handCardW, handCardH);

            //카드 이름 작성
            font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            g2d.drawString(handPile.get(i).getCardName(), handCardX + (handCardW / 2 - 10), handCardY + 30);
            g2d.drawLine(handCardX, handCardY + 50, handCardX + handCardW, handCardY + 50);
            g2d.drawLine(handCardX, handCardY + (handCardH / 2 + 30) , handCardX + handCardW, handCardY + (handCardH/2 + 30));

            //카드 효과 작성
            font = new Font("Arial", Font.BOLD, 15);
            g.setFont(font);
            ArrayList<CardEffect> cardEffects = handPile.get(i).getCardEffectList();
            int cardEffectX = handCardX + 10;
            int cardEffectY = handCardY + handCardH / 2 + 50 ;
            for (CardEffect cardEffect : cardEffects){
                g2d.drawString(cardEffect.getCardEffectContent(), cardEffectX, cardEffectY);
                cardEffectY += 20;
            }

            g2d.rotate(-radian, handCardX + handCardW / 2.0, handCardY + handCardH / 2.0); //회전 초기화

            handCardX += (int) (20 - handPileSize) * 10;


    }



    }

    public ImageIcon scaleImage(String imagePath, int w, int h){
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaleImage = originalIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(scaleImage);

        return scaleIcon;
    }

}
