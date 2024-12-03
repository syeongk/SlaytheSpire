package gameEntity.monsters;

import characterStatus.Health;
import gameEntity.characters.Character;
import ui.GameState;
import statusEffect.StatusEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import static statusEffect.StatusEffect.*;
import static ui.GameState.getInstance;

public abstract class Monster {
    protected GameState gameState;
    protected Character character;
    protected Health health;  //체력
    protected MonsterRank type; //약한, 강한, 보스
    protected String imagePath;
    protected int monsterTurn = 0;
    protected static Random r = new Random();
    protected int damage = 0;
    protected int x;
    protected int y;
    protected HashMap<StatusEffect, Integer> statusEffects;


    public Monster(int health, MonsterRank type, String imagePath, int x, int y){
        this.health = new Health(health, health);
        this.type = type;
        this.imagePath = imagePath;
        this.gameState = getInstance();
        this.x = x;
        this.y = y;
        this.statusEffects = new HashMap<>();
        this.statusEffects.put(Vulnerable, 0);
        this.statusEffects.put(Weak, 0);
        this.statusEffects.put(Strength, 0);
        this.statusEffects.put(Block, 0);
        character = gameState.getCharacter();
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

    public void endTurn(){
        reduceEffectAmount();
    }

    public abstract void performTurn();


    public int attack(){
        character.takeDamage(damage + statusEffects.get(Strength));

        return damage + statusEffects.get(Strength);
    }

    public void takeDamage(int damage) {
        if (statusEffects.get(Vulnerable) > 0) {
            damage = (int) Math.round(damage * 1.5);
        }

        if (statusEffects.get(Block) - damage < 0) {
            health.setCurrentHealth(health.getCurrentHealth() + (statusEffects.get(Block) - damage));
            statusEffects.put(Block, 0);
        } else if (statusEffects.get(Block) - damage >= 0){
            statusEffects.put(Block, statusEffects.get(Block) - damage);
        }

        if (health.getCurrentHealth() <= 0) {
            die();
        }
    }

    public void die() {
        gameState.removeMonster(this);
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public int getCurrentHealth(){
        return this.health.getCurrentHealth();
    }
    public void setCurrentHealth(int health){
        this.health.setCurrentHealth(health);
    }

    public int getMaxHealth(){
        return this.health.getMaxHealth();
    }



    public String getImagePath() {
        return imagePath;
    }

    public MonsterRank getType() {
        return type;
    }

    public void setType(MonsterRank type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public HashMap<StatusEffect, Integer> getStatusEffects() {
        return statusEffects;
    }


}
