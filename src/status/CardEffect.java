package status;

public class CardEffect {

    CardEffectType cardEffectType;
    int cardEffectAmount;

    public CardEffect(CardEffectType cardEffectType, int cardEffectAmount) {
        this.cardEffectType = cardEffectType;
        this.cardEffectAmount = cardEffectAmount;
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

}
