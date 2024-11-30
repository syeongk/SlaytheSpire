package gameEntity.monsters.weak;

import gameEntity.monsters.Monster;
import gameEntity.monsters.MonsterRank;

import java.util.Stack;

import static statusEffect.StatusType.Weak;

public class SlimeS extends Monster {

    Stack<Integer> stack = new Stack<>();

    public SlimeS(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/slimeS.png", 1200, 350);
        damage = 3;
    }

    public static int initHealth(){
        return r.nextInt(8,12);
    }

    public Object[] lick(){
        Object[] statusEffect = new Object[2];
        statusEffect[0] = Weak;
        statusEffect[1] = 1;
        System.out.println("lick");
        stack.push(1);
        return statusEffect;
    }

    public void tackle(){
        attack();
        stack.push(2);
        System.out.println("tackle");
    }

    public void performTurn(){
        monsterTurn += 1;
        if (monsterTurn == 1){
            int selection = r.nextInt(1,3);

            if (selection == 1){
                lick();
            } else {
                tackle();
                stack.push(2);
            }
        } else {
            int skillCheck = stack.pop();
            if(skillCheck == 1){
                tackle();
                stack.push(2);
            } else {
                lick();
                stack.push(1);
            }
        }
    }
}
