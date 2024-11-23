package battle.character;

import battle.monster.BattleInformation;
import battle.monster.StatusEffect;
import status.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class Character {

    private String characterName;
    private Health health;
    private int money;
    private LinkedList<Potion> potions;
    private static BattleInformation battleInformation;
    private ArrayList<Relic> relics;
    protected LinkedList<Card> deck;
    protected LinkedList<Card> handPile;
    protected LinkedList<Card> drawPile;
    protected LinkedList<Card> discardPile;
    protected LinkedList<Card> exhaustedPile;
    private Energy energy;
    private PriorityQueue<StatusEffect> statusEffect;

    public Character(String characterName, int money, int currentHealth, int maxHealth, Relic relic){
        this.characterName = characterName;
        this.health = new Health(currentHealth, maxHealth);
        this.money = money;
        this.potions = new LinkedList<>();
        this.battleInformation = new BattleInformation();
        this.relics = new ArrayList<>(); relics.add(relic);
        this.deck = new LinkedList<>();
        this.handPile = new LinkedList<>();
        this.drawPile = new LinkedList<>();
        this.discardPile = new LinkedList<>();
        this.exhaustedPile = new LinkedList<>();
        this.energy = new Energy(3);
        this.statusEffect = new PriorityQueue<>();
    }

    public boolean isMyTurn(){
        if(battleInformation.getTurnCount() % 2 == 0)
            return true;
        else
            return false;
    }

    //abstract void performTurn();
    abstract void initDeck();
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

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

}
