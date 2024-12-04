package gameEntity.characters;

import card.Card;
import characterStatus.Energy;
import characterStatus.Health;
import characterStatus.Potion;
import characterStatus.Relic;
import gameEntity.monsters.Monster;
import statusEffect.StatusEffect;

import java.util.*;

import static statusEffect.StatusEffect.*;

public abstract class Character {
    private String characterName;
    private Health health;
    private int money;
    private LinkedList<Potion> potions;
    private ArrayList<Relic> relics;
    protected LinkedList<Card> deck;
    protected LinkedList<Card> handPile;
    protected LinkedList<Card> drawPile;
    protected LinkedList<Card> discardPile;
    protected LinkedList<Card> temporaryPile;
    protected LinkedList<Card> exhaustedPile;
    private Energy energy;
    private final int x = 250;
    private final int y = 350;
    private String imagePath;
    private int cardCount = 5;

    protected HashMap<StatusEffect, Integer> statusEffects;

    protected Character(String characterName, int money, int currentHealth, int maxHealth, Relic relic, String imagePath){
        this.characterName = characterName;
        this.health = new Health(currentHealth, maxHealth);
        this.money = money;
        this.potions = new LinkedList<>();
        this.relics = new ArrayList<>(); relics.add(relic);
        this.deck = new LinkedList<>();
        this.handPile = new LinkedList<>();
        this.drawPile = new LinkedList<>();
        this.discardPile = new LinkedList<>();
        this.temporaryPile = new LinkedList<>();
        this.exhaustedPile = new LinkedList<>();
        this.energy = new Energy(3);
        this.imagePath = imagePath;
        this.statusEffects = new HashMap<>();
        this.statusEffects.put(Vulnerable, 0);
        this.statusEffects.put(Weak, 0);
        this.statusEffects.put(Strength, 0);
        this.statusEffects.put(Block, 0);
    }


    public void addStatusEffect(StatusEffect statusEffect, Integer statusEffectAmount){
        statusEffects.put(statusEffect, statusEffects.get(statusEffect) + statusEffectAmount);
    }

    public void reduceEffectAmount(){
        for (StatusEffect statusEffect : statusEffects.keySet()) {
            int duration = statusEffect.getDuration();
            if (duration == 1 && statusEffects.get(statusEffect) > 0){
                statusEffects.put(statusEffect, statusEffects.get(statusEffect) - 1);
            } else if (duration == 0){
                statusEffects.put(statusEffect, 0);
            }
        }
    }

    public void endBattle(){
        energy.setCurrentEnergy(energy.getMaxEnergy());

        statusEffects.put(Vulnerable, 0);
        statusEffects.put(Weak, 0);
        statusEffects.put(Strength, 0);
        statusEffects.put(Block, 0);
    }

    abstract void initDeck();

    public void takeDamage(int damage) {
        int currentHealth = health.getCurrentHealth();

        if (statusEffects.get(Block) - damage <= 0) {
            health.setCurrentHealth(currentHealth + (statusEffects.get(Block) - damage));
            statusEffects.put(Block, 0);
        } else {
            statusEffects.put(Block, statusEffects.get(Block) - damage);
        }
    }

    public void useCard(Card card, Monster monster){
        if(energy.getCurrentEnergy() - card.getEnergyCost() >= 0){
            handPile.remove(card);
            discardPile.add(card);
            energy.setCurrentEnergy(energy.getCurrentEnergy() - card.getEnergyCost());

            card.activateCard(monster, statusEffects);
        }
    }

    public void useCard(Card card){
        if(energy.getCurrentEnergy() - card.getEnergyCost() >= 0){
            handPile.remove(card);
            discardPile.add(card);
            energy.setCurrentEnergy(energy.getCurrentEnergy() - card.getEnergyCost());

            card.activateCard(this);
        }

    }

    public void addTemporaryPile(Card card) {
        temporaryPile.add(card);
    }

    public void temporaryCardToDiscardPile(Card card){
        discardPile.add(card);
    }

    //drawPile 에 있는 임시카드들 모두 삭제하고, temporaryPile 카드 모두 삭제한다.
    public void clearTemporaryCards(){
        for (Card temporaryCard : temporaryPile){
            Iterator<Card> iterator = drawPile.iterator();
            while (iterator.hasNext()){
                Card drawCard = iterator.next();
                if (drawCard.equals(temporaryCard)) {
                    iterator.remove();
                    break;
                }
            }
        }
        temporaryPile.clear();

    }

    //abstract void performTurn();
    //abstract void attack();
    //abstract void buff();
    //abstract void debuff();

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public LinkedList<Potion> getPotions() {
        return potions;
    }

    public void setPotions(LinkedList<Potion> potions) {
        this.potions = potions;
    }

    public ArrayList<Relic> getRelics() {
        return relics;
    }

    public void setRelics(ArrayList<Relic> relics) {
        this.relics = relics;
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }

    public void setDeck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public LinkedList<Card> getDiscardPile() {
        return discardPile;
    }


    public LinkedList<Card> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(LinkedList<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public LinkedList<Card> getHandPile() {
        return handPile;
    }

    public void setHandPile(LinkedList<Card> handPile) {
        this.handPile = handPile;
    }

    public LinkedList<Card> getExhaustedPile() {
        return exhaustedPile;
    }

    public void setExhaustedPile(LinkedList<Card> exhaustedPile) {
        this.exhaustedPile = exhaustedPile;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImagePath() {
        return imagePath;
    }

    public HashMap<StatusEffect, Integer> getStatusEffects() {
        return statusEffects;
    }

    public int getCardCount() {
        return this.cardCount;
    }

    public LinkedList<Card> getTemporaryPile() {
        return temporaryPile;
    }

    public void setTemporaryPile(LinkedList<Card> temporaryPile) {
        this.temporaryPile = temporaryPile;
    }

}
