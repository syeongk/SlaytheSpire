package gameEntity.monsters.weak;

import gameEntity.monsters.Monster;
import gameEntity.monsters.MonsterRank;

import java.util.ArrayDeque;
import java.util.Stack;

public class LouseG extends Monster {
    private boolean damaged = false; //데미지 받았는지 기록
    Stack<Integer> usedSkills = new Stack<>(); // grow : 1, bite : 2

    public LouseG(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/LouseG.png", 950, 350);
        damage = r.nextInt(5,8);
    }

    public static int initHealth(){
        return r.nextInt(10,16);
    }

    public void grow(){
        strength += 3;
        usedSkills.push(1);
        System.out.println("grow");
    }

    public void bite(){
        attack();
        usedSkills.push(2);
        System.out.println("bite");
    }

    public void curlUp(){
        block += r.nextInt(3,8);
        System.out.println("curlUp");
    }

    public void performTurn(){
        block = 0;
        if (damaged){
            curlUp();
        }
        while(true){
            int selection = r.nextInt(1,101);

            if (selection <= 25 && usedSkillsCheck(1)){
                grow();
                break;
            } else if (selection > 25 && selection <= 100 && usedSkillsCheck(2)) {
                bite();
                break;
            }
        }

    }

    public boolean usedSkillsCheck(int skill){

        if (usedSkills.isEmpty()){
            return true;
        } else if (usedSkills.size() == 1) {
            int usedSkill = usedSkills.pop();
            if (skill != usedSkill)
                return true;
            else {
                usedSkills.push(usedSkill);
                return true;
            }
        } else {
            if (usedSkills.peek() == skill){
                return false;
            } else {
                usedSkills.clear();
                return true;
            }

        }

    }
}
