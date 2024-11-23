package monsters.weak;

import monsters.Monster;
import monsters.MonsterInterface;
import monsters.MonsterRank;

import java.util.ArrayDeque;
import java.util.Queue;

public class LouseG extends Monster implements MonsterInterface {
    private boolean damaged = false; //데미지 받았는지 기록
    Queue<Integer> usedSkills = new ArrayDeque<>(); // grow : 1, bite : 2

    public LouseG(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/LouseG.png");
        damage = r.nextInt(5,8);
    }

    public static int initHealth(){
        return r.nextInt(10,16);
    }

    public void grow(){
        strength += 3;
    }

    public int bite(){
        return attack();
    }

    public void takeDamage(int damage){
        if (block - damage < 0){
            health += block - damage;
            damaged = true;
        } else {
            damaged = false;
        }
    }

    public void curlUp(){
        block += r.nextInt(3,8);
    }

    public void performTurn(){
        block = 0;
        if (damaged){
            curlUp();
        }

        if (usedSkills.size() == 3){
            usedSkills.poll();
        }

        int selection = r.nextInt(1,101);

        if (selection <= 25 && usedSkillsCheck(1)){
            grow();
            usedSkills.offer(1);
        } else if (selection <= 100 && usedSkillsCheck(2)) {
            bite();
            usedSkills.offer(2);
        }

        monsterTurn += 1;
    }

    public boolean usedSkillsCheck(int skill){
        for (int usedSkill : usedSkills){
            if (skill == usedSkill)
                return false;
        }
        return true;
    }
}
