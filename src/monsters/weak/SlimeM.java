package monsters.weak;

import monsters.Monster;
import monsters.MonsterInterface;
import monsters.MonsterRank;

public class SlimeM extends Monster implements MonsterInterface {

    public SlimeM(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/slimeM.png");
    }

    public static int initHealth(){
        return r.nextInt(28,33);
    }

    @Override
    public void performTurn(){

    }
}
