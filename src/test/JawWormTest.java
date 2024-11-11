package test;

import battle.monster.JawWorm;
import battle.monster.MonsterRank;
import org.junit.Test;

import static battle.monster.MonsterRank.NORMAL;
import static org.junit.Assert.assertTrue;

public class JawWormTest {

    @Test
    public void testJawWormHealth(){
        JawWorm jawWorm = new JawWorm();
        int health = jawWorm.getHealth();
        System.out.println(health);
        assertTrue(health >= 40 && health < 45);
    }

    @Test
    public void testJawWormMonsterType(){
        JawWorm jawWorm = new JawWorm();
        MonsterRank monsterRank = jawWorm.getType();
        assertTrue(monsterRank == NORMAL);
    }

}
