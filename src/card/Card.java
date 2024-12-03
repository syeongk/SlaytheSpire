package card;

import gameEntity.characters.Character;
import gameEntity.monsters.Monster;
import statusEffect.StatusEffect;

import java.util.ArrayList;
import java.util.HashMap;

public class Card {
    private String cardName;
    private CardType cardType;
    private ArrayList<CardEffect> cardEffectList;
    private int energyCost;
    private CardTarget[] cardTarget;

    public Card(String cardName, CardType cardType, int energyCost, CardTarget[] cardTarget) {
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardEffectList = new ArrayList<>();
        this.energyCost = energyCost;
        this.cardTarget = cardTarget;
    }

    public void activateCard(Monster monster, HashMap<StatusEffect, Integer> statusEffects){
        for (CardEffect cardEffect : cardEffectList){
            CardEffectType cardEffectType = cardEffect.getCardEffectType();
            cardEffectType.applyEffect(cardEffect, monster, statusEffects);
        }
    }


    public void activateCard(Character character){
        for (CardEffect cardEffect : cardEffectList){
            CardEffectType cardEffectType = cardEffect.getCardEffectType();
            cardEffectType.applyEffect(cardEffect, character);
        }
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


    public CardTarget[] getCardTarget() {
        return cardTarget;
    }


}
