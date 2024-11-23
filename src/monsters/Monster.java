package monsters;

import statusEffect.StatusEffect;

import java.util.PriorityQueue;

public abstract class Monster {
    private int health;  //체력
    private MonsterRank type; //약한, 강한, 보스
    private PriorityQueue<StatusEffect> statusEffect; //상태 이상
    private String imagePath;


    public Monster(int health, MonsterRank type, String imagePath){
        this.health = health;
        this.type = type;
        this.statusEffect = new PriorityQueue<>();
        this.imagePath = imagePath;
    }

    public abstract void attack();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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



}
