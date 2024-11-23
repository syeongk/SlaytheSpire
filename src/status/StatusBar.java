package status;

import battle.character.Character;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class StatusBar extends JPanel {

    private Character character;
    private Health myHealth;
    private int myMoney;
    private LinkedList<Card> myDeck;
    private int floor = 1;
    private LinkedList<Potion> myPotions;

    public StatusBar(Character character){

        this.character = character;
        myHealth = this.character.getHealth();
        myMoney = this.character.getMoney();
        myDeck = this.character.getDeck();
        myPotions = this.character.getPotions();

        ImageIcon backgroundImage = new ImageIcon("src/imgs/status_bar.png");

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        add(mainPanel);

        mainPanel.setLayout(new GridLayout(1,3));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //상태바 JLabel 및 JButton 추가
        //TODO : 데이터베이스에서 닉네임 가져오기
        Font font = new Font("Arial", Font.PLAIN, 20);
        JLabel nameLabel = new JLabel("seo");
        nameLabel.setFont(font);

        JLabel characterLabel = new JLabel(character.getCharacterName());
        characterLabel.setFont(font);

        ImageIcon healthImageIcon = new ImageIcon("src/imgs/health.png");
        Image img1 = healthImageIcon.getImage();
        Image healthImg = img1.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        healthImageIcon = new ImageIcon(healthImg);
        JLabel healthImageLabel = new JLabel(healthImageIcon);
        JLabel healthLabel = new JLabel(myHealth.getCurrentHealth() + "/" + myHealth.getMaxHealth());
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(font);


        ImageIcon moneyImageIcon = new ImageIcon("src/imgs/money.png");
        Image img2 = moneyImageIcon.getImage();
        Image moneyImg = img2.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        moneyImageIcon = new ImageIcon(moneyImg);
        JLabel moneyImageLabel = new JLabel(moneyImageIcon);
        JLabel moneyLabel = new JLabel(Integer.toString(myMoney));
        moneyLabel.setForeground(Color.YELLOW);
        moneyLabel.setFont(font);

        JLabel floorLabel = new JLabel(floor + "");
        floorLabel.setFont(font);

        JButton mapButton = new JButton("맵");
        JButton cardAmountButton = new JButton(Integer.toString(myDeck.size()));
        JButton settingButton = new JButton("환경설정");

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        panel1.add(nameLabel);
        panel1.add(characterLabel);
        panel1.add(healthImageLabel);
        panel1.add(healthLabel);
        panel1.add(moneyImageLabel);
        panel1.add(moneyLabel);

        panel2.add(floorLabel);

        panel3.add(mapButton);
        panel3.add(cardAmountButton);
        panel3.add(settingButton);
    }

}
