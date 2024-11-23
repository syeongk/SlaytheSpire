package monsters.weak;

import monsters.Monster;
import monsters.MonsterInterface;
import monsters.MonsterRank;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class JawWorm extends Monster implements MonsterInterface {
    ArrayList<Integer> usedSkills = new ArrayList<>();

    public JawWorm() {
        super(initHealth(), MonsterRank.WEAK, "src/imgs/jawWorm.png");
        usedSkills.add(-1);
    }

    public static int initHealth(){
        return r.nextInt(40,45);
    }

    //공격
    public int chomp() {
        damage = 11;
        return attack();
    }

    //공격 & 방어
    public int thrash(){
        block += 5;
        damage = 7;

        return attack();
    }

    //힘 & 방어
    public void bellow(){
        strength += 3;
        block += 6;
    }

    @Override
    public void performTurn() {
        block = 0;
        if (monsterTurn == 1){
            chomp();
        } else {
            while(true) {
                int selection = r.nextInt(1, 101);

                if (selection <= 45 && usedSkills.get(monsterTurn-1) != 1) { //bellow 45% 확률
                    bellow();
                    usedSkills.add(1);
                    break;
                } else if (selection <= 75 && usedSkills.get(monsterTurn-1) != 2 && (monsterTurn > 2 && usedSkills.get(monsterTurn-2) != 2)) { //thrash 30% 확률
                    thrash();
                    usedSkills.add(2);
                    break;
                } else if (selection <= 100 && usedSkills.get(monsterTurn-1) != 3) // chomp 25% 확률
                    chomp();
                    usedSkills.add(3);
                    break;
                }
            }

        monsterTurn += 1;
        }
    }

