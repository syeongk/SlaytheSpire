package monsters;

import characters.Character;
import panels.GameState;
import statusEffect.StatusEffect;

import java.util.PriorityQueue;
import java.util.Random;

import static panels.GameState.getInstance;

public abstract class Monster {
    protected GameState gameState;
    protected int health;  //체력
    protected MonsterRank type; //약한, 강한, 보스
    protected PriorityQueue<StatusEffect> statusEffect; //상태 이상
    protected String imagePath;
    protected int monsterTurn = 1;
    protected int block = 0;
    protected int strength = 0;
    protected static Random r = new Random();
    protected int damage = 0;
    protected int x;
    protected int y;


    public Monster(int health, MonsterRank type, String imagePath, int x, int y){
        this.health = health;
        this.type = type;
        this.statusEffect = new PriorityQueue<>();
        this.imagePath = imagePath;
        this.gameState = getInstance();
        this.x = x;
        this.y = y;
    }


    public void attack(){
        Character character = gameState.getCharacter();
        character.takeDamage(damage + strength);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public PriorityQueue<StatusEffect> getStatusEffect() {
        return statusEffect;
    }
    public void setStatusEffect(PriorityQueue<StatusEffect> statusEffect) {
        this.statusEffect = statusEffect;
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



}
