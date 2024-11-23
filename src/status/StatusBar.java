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

        setLayout(new BorderLayout());

        ImageIcon backgroundImage = new ImageIcon("src/imgs/bar.png");

        JPanel barPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, this);
            }
        };
        add(barPanel, BorderLayout.NORTH);

        barPanel.setLayout(new GridLayout(1,5));

        //상태바 JLabel 및 JButton 추가
        //TODO : 데이터베이스에서 닉네임 가져오기
        Font font = new Font("Arial", Font.PLAIN, 20);
        JLabel nameLabel = new JLabel("seo");
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);

        JLabel characterLabel = new JLabel(character.getCharacterName());
        characterLabel.setFont(font);
        characterLabel.setForeground(Color.WHITE);


        JLabel healthImageLabel = new JLabel(scaleImage("src/imgs/panelHeart.png"));
        JLabel healthLabel = new JLabel(myHealth.getCurrentHealth() + "/" + myHealth.getMaxHealth());
        healthLabel.setForeground(Color.RED);
        healthLabel.setFont(font);


        JLabel moneyImageLabel = new JLabel(scaleImage("src/imgs/panelGoldBag.png"));
        JLabel moneyLabel = new JLabel(Integer.toString(myMoney));
        moneyLabel.setFont(font);

        JLabel floorImageLabel = new JLabel(scaleImage("src/imgs/floor.png"));
        JLabel floorLabel = new JLabel(floor + "");
        floorLabel.setFont(font);
        floorLabel.setForeground(Color.WHITE);


        JButton mapButton = new JButton(scaleImage("src/imgs/map.png"));
        JButton cardAmountButton = new JButton(scaleImage("src/imgs/deck.png"));
        JButton settingButton = new JButton(scaleImage("src/imgs/settings.png"));
        mapButton.setPreferredSize(new Dimension(45, 45));
        cardAmountButton.setPreferredSize(new Dimension(45, 45));
        settingButton.setPreferredSize(new Dimension(45, 45));
        mapButton.setBorderPainted(false);
        cardAmountButton.setBorderPainted(false);
        settingButton.setBorderPainted(false);
        mapButton.setFocusPainted(false);
        cardAmountButton.setFocusPainted(false);
        settingButton.setFocusPainted(false);



        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel empty1 = new JPanel();
        JPanel empty2 = new JPanel();
        barPanel.add(panel1);
        barPanel.add(empty1);
        barPanel.add(panel2);
        barPanel.add(empty2);
        barPanel.add(panel3);

        panel1.add(nameLabel);
        panel1.add(characterLabel);
        panel1.add(healthImageLabel);
        panel1.add(healthLabel);
        panel1.add(moneyImageLabel);
        panel1.add(moneyLabel);

        panel2.add(floorImageLabel);
        panel2.add(floorLabel);

        panel3.add(mapButton);
        panel3.add(cardAmountButton);
        panel3.add(settingButton);

        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        empty1.setOpaque(false);
        empty2.setOpaque(false);
    }

    public ImageIcon scaleImage(String imagePath){
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaleImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(scaleImage);

        return scaleIcon;
    }

}
