package gameEntity.monsters.weak;

import gameEntity.monsters.Monster;
import gameEntity.monsters.MonsterRank;

import java.util.*;

import static statusEffect.StatusEffect.Block;
import static statusEffect.StatusEffect.Strength;

public class JawWorm extends Monster {
    Stack<Integer> usedSkills = new Stack<>(); //1:bellow, 2:thrash, 3:chomp

    public JawWorm() {
        super(initHealth(), MonsterRank.WEAK, "src/imgs/jawWorm.png", 1000, 350);
    }

    public static int initHealth(){
        return r.nextInt(40,45);
    }

    //공격
    public void chomp() {
        damage = 11;
        attack();
        usedSkills.push(3);
        System.out.println("chomp");
    }

    //공격 & 방어
    public void thrash(){
        statusEffects.put(Block, statusEffects.get(Block) + 5);
        damage = 7;

        attack();
        usedSkills.push(2);
        System.out.println("thrash");
    }

    //힘 & 방어
    public void bellow(){
        statusEffects.put(Strength, statusEffects.get(Strength) + 3);
        statusEffects.put(Block, statusEffects.get(Block) + 6);
        usedSkills.push(1);
        System.out.println("bellow");
    }

    @Override
    public void performTurn() {
        monsterTurn += 1;
        statusEffects.put(Block, 0);
        if (monsterTurn == 1){
            chomp();
        } else {
            while (true) {
                int selection = r.nextInt(1, 101);

                if (selection <= 45 && usedSkillsCheck(1)) { //bellow 45% 확률
                    bellow();
                    break;
                } else if (selection <= 75 && usedSkillsCheck(2)) { //thrash 30% 확률
                    thrash();
                    break;
                } else if (selection <= 100 && usedSkillsCheck(3)) { // chomp 25% 확률
                    chomp();
                    break;
                }
            }
        }
        endTurn();
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
                if (skill != 2)
                    return false;
                else
                    return true;
            }
        } else {
            if (skill != 2){
                usedSkills.clear();
                return true;
            } else {
                return false;
            }

        }

    }
}

