package battle.monster;

import java.util.PriorityQueue;

public abstract class Monster {
    private int health;  //체력
    private MonsterRank type; //일반, 엘리트, 보스
    private PriorityQueue<StatusEffect> statusEffect; //상태 이상
    private static BattleInformation battleInformation;

    public Monster(int h, MonsterRank t){
        health = h;
        type = t;
        statusEffect = new PriorityQueue<>();
        battleInformation = new BattleInformation();
    }

    public boolean IsMonsterTurn(){
        if (battleInformation.getTurnCount() % 2 == 1)
            return true;
        else
            return false;
    }

    abstract void attack();

    abstract void buff();

    abstract void debuff();


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


}
