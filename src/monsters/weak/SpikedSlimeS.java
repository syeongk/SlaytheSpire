package monsters.weak;

import monsters.Monster;
import monsters.MonsterInterface;
import monsters.MonsterRank;

public class SpikedSlimeS extends Monster implements MonsterInterface {

    public SpikedSlimeS(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/spikedSlimeS.png");
        damage = 5;
    }

    public static int initHealth(){
        return r.nextInt(10,15);
    }

    public int tackle(){
        return attack();
    }

    public void performTurn(){

    }
}
