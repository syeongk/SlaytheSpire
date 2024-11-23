package monsters.weak;

import monsters.Monster;
import monsters.MonsterInterface;
import monsters.MonsterRank;

import java.util.Stack;

import static statusEffect.StatusType.Weak;

public class SlimeS extends Monster implements MonsterInterface {

    Stack<Integer> stack = new Stack<>();

    public SlimeS(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/slimeS.png");
        damage = 3;
    }

    public static int initHealth(){
        return r.nextInt(8,12);
    }

    public Object[] lick(){
        Object[] statusEffect = new Object[2];
        statusEffect[0] = Weak;
        statusEffect[1] = 1;
        return statusEffect;
    }

    public int tackle(){
        return attack();
    }

    public void performTurn(){
        if (monsterTurn == 1){
            int selection = r.nextInt(1,3);

            if (selection == 1){
                lick();
                stack.push(1);
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
