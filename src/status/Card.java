package status;

import java.util.ArrayList;

public class Card {
    private String cardName;
    private CardType cardType;
    private String cardContent;
    private ArrayList<CardEffect> cardEffectList;
    private int energyCost;

    public Card(String cardName, CardType cardType, String cardContent, int energyCost) {
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardContent = cardContent;
        this.cardEffectList = new ArrayList<>();
        this.energyCost = energyCost;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardContent() {
        return cardContent;
    }

    public void setCardContent(String cardContent) {
        this.cardContent = cardContent;
    }


    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }

    public ArrayList<CardEffect> getCardEffectList() {
        return cardEffectList;
    }

    public void setCardEffectList(ArrayList<CardEffect> cardEffectList) {
        this.cardEffectList = cardEffectList;
    }
}
