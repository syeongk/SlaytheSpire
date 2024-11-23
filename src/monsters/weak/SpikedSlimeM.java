package monsters.weak;

import monsters.Monster;
import monsters.MonsterInterface;
import monsters.MonsterRank;

public class SpikedSlimeM extends Monster implements MonsterInterface {

    public SpikedSlimeM(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/spikedSlimeM.png");
    }

    public static int initHealth(){
        return r.nextInt(28,33);
    }

    public void performTurn(){

    }
}
