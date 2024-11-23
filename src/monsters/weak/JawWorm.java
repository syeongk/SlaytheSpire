package monsters.weak;

import monsters.Monster;
import monsters.MonsterRank;

import java.util.Random;

public class JawWorm extends Monster {
    public JawWorm() {
        super(initHealth(), MonsterRank.WEAK, "src/imgs/185.png");
    }

    public static int initHealth(){
        Random r = new Random();
        return r.nextInt(40,45);
    }

    @Override
    public void attack() {
        System.out.println("공격");
    }

}
