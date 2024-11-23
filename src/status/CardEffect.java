package status;

import java.util.ArrayList;

public class CardEffect {

    CardEffectType cardEffectType;
    int cardEffectAmount;
    String cardEffectContent;

    public CardEffect(CardEffectType cardEffectType, String cardEffectContent, int cardEffectAmount) {
        this.cardEffectType = cardEffectType;
        this.cardEffectAmount = cardEffectAmount;
        this.cardEffectContent = cardEffectContent;
    }

    public int getCardEffectAmount() {
        return cardEffectAmount;
    }

    public void setCardEffectAmount(int cardEffectAmount) {
        this.cardEffectAmount = cardEffectAmount;
    }

    public CardEffectType getCardEffectType() {
        return cardEffectType;
    }

    public void setCardEffectType(CardEffectType cardEffectType) {
        this.cardEffectType = cardEffectType;
    }


    public String getCardEffectContent() {
        return cardEffectContent;
    }

    public void setCardEffectContent(String cardEffectContent) {
        this.cardEffectContent = cardEffectContent;
    }

}
