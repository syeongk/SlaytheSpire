import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

import battle.character.Character;
import status.Card;
import status.CardEffect;
import status.Energy;

public class NormalRoom extends JPanel {

    private GameState gameState;
    private Character character;
    private Energy energy;
    private ImageIcon monsterImageIcon;
    private LinkedList<Card> drawPile;
    private LinkedList<Card> handPile;
    private LinkedList<Card> discardPile;
    private int i;


    public NormalRoom(GameState gameState){

        setLayout(new BorderLayout());
        setOpaque(false);

        this.gameState = gameState;
        this.character = gameState.getCharacter();

        add(gameState.getStatusBar(), BorderLayout.NORTH);

        energy = character.getEnergy();
        drawPile = character.getDrawPile();
        handPile = character.getHandPile();
        discardPile = character.getDiscardPile();



        addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e){
               int mouseX = e.getX();
               int mouseY = e.getY();
               System.out.println(mouseX);
               System.out.println(mouseY);
           }
        });

        monsterImageIcon = new ImageIcon("src/imgs/185.png");
        Image img = monsterImageIcon.getImage();
        Image monsterImg = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        monsterImageIcon = new ImageIcon(monsterImg);

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


        //몬스터
        g.drawImage(monsterImageIcon.getImage(), getWidth() - 450, getHeight() - 500, null);
        g.drawRect(1300, 350, 100, 100);


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

}
