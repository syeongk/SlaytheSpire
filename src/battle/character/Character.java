package battle.character;

import battle.monster.BattleInformation;
import status.*;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Character {

    private String characterName;
    private Health health;
    private Money money;
    private LinkedList<Potion> potions;
    private static BattleInformation battleInformation;
    private ArrayList<Relic> relics;
    protected LinkedList<Card> deck;
    protected LinkedList<Card> handPile;
    protected LinkedList<Card> drawPile;
    protected LinkedList<Card> discardPile;
    protected LinkedList<Card> exhaustedPile;
    private int energy;

    public Character(String characterName, int currentHealth, int maxHealth, int energy){
        this.characterName = characterName;
        health = new Health(currentHealth, maxHealth);
        money = new Money();
        potions = new LinkedList<>();
        battleInformation = new BattleInformation();
        relics = new ArrayList<>();
        deck = new LinkedList<>();
        handPile = new LinkedList<>();
        drawPile = new LinkedList<>();
        discardPile = new LinkedList<>();
        exhaustedPile = new LinkedList<>();
        this.energy = energy;
    }

    public boolean isMyTurn(){
        if(battleInformation.getTurnCount() % 2 == 0)
            return true;
        else
            return false;
    }

    abstract void performTurn();
    abstract void initDeck();
    abstract void attack();
    abstract void buff();
    abstract void debuff();

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

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
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

    public static BattleInformation getBattleInformation() {
        return battleInformation;
    }

    public static void setBattleInformation(BattleInformation battleInformation) {
        Character.battleInformation = battleInformation;
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

    public void setDiscardPile(LinkedList<Card> discardPile) {
        this.discardPile = discardPile;
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

}
