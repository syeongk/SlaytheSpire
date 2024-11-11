package status;

import battle.character.Character;
import javax.swing.*;
import java.util.LinkedList;

public class StatusBar extends JPanel {

    private Character character;
    private Health myHealth;
    private Money myMoney;
    private LinkedList<Card> myDeck;

    public StatusBar(Character character){
        this.character = character;
        myHealth = this.character.getHealth();
        myMoney = this.character.getMoney();
        myDeck = this.character.getDeck();

        //TODO : 데이터베이스에서 닉네임 가져오기
        JLabel nameLabel = new JLabel("seo");
        JLabel healthLabel = new JLabel(myHealth.getCurrentHealth() + "/" + myHealth.getMaxHealth());
        JLabel moneyLabel = new JLabel(Integer.toString(myMoney.getMoneyAmount()));
        JLabel cardAmountLabel = new JLabel(Integer.toString(myDeck.size()));

        add(nameLabel);
        add(healthLabel);
        add(moneyLabel);
        add(cardAmountLabel);
    }
}
