package battle.character.characterInfo;

import status.Health;
import status.Relic;

public abstract class CharacterInfo {
    private String name;
    private Health health;
    private int money;
    private Relic relic;
    private String explanation;

    public CharacterInfo(String name, Health health, int money, String explanation, Relic relic){
        this.name = name;
        this.health = health;
        this.money = money;
        this.explanation = explanation;
        this.relic = relic;
    }

    public String getName() {
        return name;
    }

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getExplanation() {
        return explanation;
    }

}
