package battle.character;

import battle.monster.BattleInformation;
import status.Card;
import status.Health;
import status.Potion;
import status.Relic;

import java.util.LinkedList;

public abstract class Character {

    private String selectedCharacter;
    private Health health;
    private ArrayList<Relic> relics;
    protected LinkedList<Card> deck;
    protected LinkedList<Card> handPile;
    protected LinkedList<Card> drawPile;
    protected LinkedList<Card> discardPile;
    protected LinkedList<Card> exhaustedPile;
    private static BattleInformation battleInformation;
    private int energy;
    private LinkedList<Potion> potionList;

    public Character(String selectedCharacter, int currentHealth, int maxHealth, int energy){
        this.selectedCharacter = selectedCharacter;
        health = new Health(currentHealth, maxHealth);
        battleInformation = new BattleInformation();
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

    public Relic getRelic() {
        return relic;
    }

    public void setRelic(Relic relic) {
        this.relic = relic;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }

    public void setDeck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public static BattleInformation getBattleInformation() {
        return battleInformation;
    }

    public static void setBattleInformation(BattleInformation battleInformation) {
        Character.battleInformation = battleInformation;
    }

    public LinkedList<Card> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(LinkedList<Card> discardPile) {
        this.discardPile = discardPile;
    }

    public LinkedList<Card> getHandPile() {
        return handPile;
    }

    public void setHandPile(LinkedList<Card> handPile) {
        this.handPile = handPile;
    }

    public LinkedList<Card> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(LinkedList<Card> drawPile) {
        this.drawPile = drawPile;
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
