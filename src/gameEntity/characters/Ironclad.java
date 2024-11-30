package gameEntity.characters;

import card.Card;
import card.CardEffect;
import characterStatus.Relic;

import static card.CardEffectType.*;
import static card.CardType.Attack;
import static card.CardType.Skill;


public class Ironclad extends Character {

    public Ironclad(){
        super("아이언 클래드", 99, 80, 80, new Relic("불타는 혈액", "전투 종료 시 체력을 6 회복합니다."), "src/imgs/ironclad.png");    //부모 생성자 호출 : 선택된 캐릭터 이름, 현재 체력, 최대 체력, 에너지
        initDeck();    //아이언 클래드 덱 초기화
    }

    @Override
    void initDeck(){
        for(int i=0; i<5; i++){
            Card strike = new Card("타격", Attack, 1);    //타격 카드(객체) 생성 : 타격 카드, 타입 공격, 카드 내용, 에너지
            CardEffect strikeEffect = new CardEffect(DAMAGE,"피해를 6 줍니다", 6);
            strike.getCardEffectList().add(strikeEffect);
            deck.add(strike);    //덱에 타격 카드 추가
        }
        for(int i=0; i<4; i++){
            Card defend = new Card("수비", Skill, 1);
            CardEffect defendEffect = new CardEffect(BLOCK,"방어도를 5 얻습니다.", 5);
            defend.getCardEffectList().add(defendEffect);
            deck.add(defend);
        }
        Card bash = new Card("강타", Attack, 2);
        CardEffect bashEffect1 = new CardEffect(DAMAGE, "피해를 8 줍니다.", 8);    //카드 하나에 여러 효과를 고려
        CardEffect bashEffect2 = new CardEffect(VULNERABLE, "취약을 2 부여합니다.", 2);
        bash.getCardEffectList().add(bashEffect1);    //효과들을 배열에 추가
        bash.getCardEffectList().add(bashEffect2);
        deck.add(bash);

        drawPile.addAll(deck);    //뽑을 카드 더미에 deck에 있는 카드 모두 추가

    }


}
