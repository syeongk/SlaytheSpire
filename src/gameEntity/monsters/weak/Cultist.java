package gameEntity.monsters.weak;

import gameEntity.monsters.Monster;
import gameEntity.monsters.MonsterRank;

import java.util.Random;

import static statusEffect.StatusEffect.Strength;

public class Cultist extends Monster {
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

    public void darkStrike(){
        attack();
        System.out.println("darkStrike");
    }

    // 의식 버프 발동 - 데미지 3 증가
    public void ritualBuff() {
        if (ritualActivated) {
            statusEffects.put(Strength, statusEffects.get(Strength) + 3);
            System.out.println("ritualBuff");
        }
    }

    // 턴 수행 첫 턴에는 의식 버프를 활성화만 시키고, 그 이후 턴부턴 버프를 발동(데미지 3 증가)하고 공격
    @Override
    public void performTurn() {
        monsterTurn += 1;
        if (monsterTurn == 1)
            ritualActivated = true;
        else {
            ritualBuff();
            darkStrike();
        }
        endTurn();
    }


}
