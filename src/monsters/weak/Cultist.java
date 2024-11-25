package monsters.weak;

import monsters.Monster;
import monsters.MonsterInterface;
import monsters.MonsterRank;

import java.util.Random;

public class Cultist extends Monster implements MonsterInterface {
    private boolean ritualActivated;

    public Cultist(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/cultist.png", 1000, 350);
        damage = 6;
    }

    // 체력 설정
    public static int initHealth(){
        Random r = new Random();
        return r.nextInt(48,55);
    }


    // 의식 버프 발동 - 데미지 3 증가
    public void ritualBuff() {
        if (ritualActivated)
            damage += 3;
    }

    // 턴 수행 첫 턴에는 의식 버프를 활성화만 시키고, 그 이후 턴부턴 버프를 발동(데미지 3 증가)하고 공격
    @Override
    public void performTurn() {
        if (monsterTurn == 1)
            ritualActivated = true;
        else {
            ritualBuff();
            attack();
        }
        monsterTurn += 1;
    }

}
