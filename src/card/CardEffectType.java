package card;

import gameEntity.characters.Character;
import gameEntity.monsters.Monster;
import statusEffect.StatusEffect;
import ui.GameState;

import java.util.HashMap;

import static statusEffect.StatusEffect.*;

public enum CardEffectType {
    // 피해 관련 효과
    DAMAGE,        // 적에게 피해를 입힙니다.
    VULNERABLE,    // 적이 받는 피해가 증가합니다. 취약
    WEAK,          // 적의 공격력이 감소합니다. 약화
    THORNS,        // 적이 공격할 때 피해를 반사합니다.
    PIERCE,        // 방어를 무시하고 피해를 입힙니다.

    // 방어 관련 효과
    BLOCK,         // 방어도를 제공합니다.
    INTANGIBLE,    // 모든 피해를 무효화합니다.
    ARTIFACT,      // 상태 이상 효과를 무효화합니다.

    // 카드 뽑기 및 리소스 관련 효과
    DRAW,          // 추가 카드를 뽑습니다.
    EXHAUST,       // 카드를 소모하여 제거합니다.
    DISCARD,       // 카드를 버리고 효과를 발동합니다.
    ENERGY,        // 추가 행동을 위해 사용할 수 있는 에너지를 증가시킵니다.

    // 상태 이상 효과
    POISON,        // 매 턴마다 피해를 줍니다.
    BURN,          // 사용 시 추가 피해를 주고, 지속적인 피해를 줍니다.
    CONFUSION,     // 다음 카드의 효과를 무작위로 변경합니다.
    FRAILTY,       // 방어도가 감소합니다.

    // 버프 및 디버프 관련 효과
    STRENGTH,      // 공격력을 증가시킵니다.
    DEXTERITY,     // 방어도를 증가시킵니다.
    POWER,         // 지속적인 효과를 제공합니다.

    // 기타 효과
    HEAL,          // 체력을 회복합니다.
    SUMMON,        // 유닛이나 효과를 소환합니다.
    TRANSFORM,     // 카드를 다른 카드로 변형합니다.
    UPGRADE;       // 카드를 강화합니다.

    public void applyEffect(CardEffect cardEffect , Monster monster, HashMap<StatusEffect, Integer> statusEffects){
        if (this == DAMAGE) {
            int value = cardEffect.getCardEffectAmount();
            if (statusEffects.get(Weak) > 0){
                value = (int) Math.round(value * 0.25);
            }
            if (statusEffects.get(Strength) > 0){
                value += statusEffects.get(Strength);
            }
            monster.takeDamage(value);
        } else if (this == VULNERABLE) {
            monster.addStatusEffect(Vulnerable, cardEffect.getCardEffectAmount());
        }
    }

    public void applyEffect(CardEffect cardEffect, Character character){
        if (this == BLOCK) {
            character.getStatusEffects().put(Block, character.getStatusEffects().get(Block) + cardEffect.getCardEffectAmount());
        }
    }
}
