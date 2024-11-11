package status;

import javax.swing.*;

public class StatusBar extends JPanel {

    private Health myHealth = new Health(80, 80);
    private Money myMoney = new Money();
    private Deck myDeck = new Deck();

    public StatusBar(){
        int currentHealth = myHealth.getCurrentHealth();
        int maxHealth = myHealth.getMaxHealth();
        int moneyAmount = myMoney.getMoneyAmount();
        int cardAmount = myDeck.getDeck().size();

        JLabel nameLabel = new JLabel("seo");
        JLabel healthLabel = new JLabel(currentHealth + "/" + maxHealth);
        JLabel moneyLabel = new JLabel(Integer.toString(moneyAmount));
        JLabel cardAmountLabel = new JLabel(Integer.toString(cardAmount));

        add(nameLabel);
        add(healthLabel);
        add(moneyLabel);
        add(cardAmountLabel);
    }
}
