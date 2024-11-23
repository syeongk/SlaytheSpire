package characters;

import card.Card;
import characterStatus.Energy;
import characterStatus.Health;
import characterStatus.Potion;
import characterStatus.Relic;
import statusEffect.StatusEffect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

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
    protected LinkedList<Card> exhaustedPile;
    private Energy energy;
    private PriorityQueue<StatusEffect> statusEffect;
    private final int x = 250;
    private final int y = 350;


    private String imagePath;

    public Character(String characterName, int money, int currentHealth, int maxHealth, Relic relic, String imagePath){
        this.characterName = characterName;
        this.health = new Health(currentHealth, maxHealth);
        this.money = money;
        this.potions = new LinkedList<>();
        this.relics = new ArrayList<>(); relics.add(relic);
        this.deck = new LinkedList<>();
        this.handPile = new LinkedList<>();
        this.drawPile = new LinkedList<>();
        this.discardPile = new LinkedList<>();
        this.exhaustedPile = new LinkedList<>();
        this.energy = new Energy(3);
        this.statusEffect = new PriorityQueue<>();
        this.imagePath = imagePath;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImagePath() {
        return imagePath;
    }

}
