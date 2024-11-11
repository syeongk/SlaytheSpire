package battle.monster;

import java.util.Random;

public class JawWorm extends Monster{
    public JawWorm() {
        super(initHealth(), MonsterRank.NORMAL);
    }

    public static int initHealth(){
        Random r = new Random();
        return r.nextInt(40,45);
    }

    @Override
    void attack() {

    }

    @Override
    void buff() {

    }

    @Override
    void debuff() {

    }
}
