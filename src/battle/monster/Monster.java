package battle.monster;

import javax.swing.*;
import java.awt.*;
import java.util.PriorityQueue;

public abstract class Monster {
    private int health;  //체력
    private MonsterRank type; //일반, 엘리트, 보스
    private PriorityQueue<StatusEffect> statusEffect; //상태 이상
    private static BattleInformation battleInformation;
    private ImageIcon monsterImageIcon;


    public Monster(int h, MonsterRank t, ImageIcon imageIcon){
        health = h;
        type = t;
        statusEffect = new PriorityQueue<>();
        battleInformation = new BattleInformation();
        monsterImageIcon = imageIcon;

        //몬스터 이미지 사이즈 조절
        Image image = monsterImageIcon.getImage();
        Image monsterImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        monsterImageIcon = new ImageIcon(monsterImage);
    }

    public boolean IsMonsterTurn(){
        if (battleInformation.getTurnCount() % 2 == 1)
            return true;
        else
            return false;
    }

    public void createMonster(){

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
    public void setStatusEffect(PriorityQueue<StatusEffect> statusEffect) {
        this.statusEffect = statusEffect;
    }

    public static BattleInformation getBattleInformation() {
        return battleInformation;
    }

    public static void setBattleInformation(BattleInformation battleInformation) {
        Monster.battleInformation = battleInformation;
    }

    public ImageIcon getMonsterImageIcon() {
        return monsterImageIcon;
    }

    public void setMonsterImageIcon(ImageIcon monsterImageIcon) {
        this.monsterImageIcon = monsterImageIcon;
    }


}
