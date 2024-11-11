package battle.character;

import status.Card;
import status.CardEffect;
import status.CardType;

import static status.CardEffectType.*;


public class Ironclad extends Character{

    public Ironclad(){
        super("아이언 클래드", 80, 80, 3);
        initDeck();
    }

    @Override
    void initDeck(){
        for(int i=0; i<5; i++){
            Card strike = new Card("타격", CardType.Attack, "피해를 6 줍니다.", 1);
            CardEffect strikeEffect = new CardEffect(DAMAGE, 6);
            strike.getCardEffectList().add(strikeEffect);
            deck.add(strike);
        }
        for(int i=0; i<4; i++){
            Card defend = new Card("수비", CardType.Skill, "방어도를 5 얻습니다.", 1);
            CardEffect defendEffect = new CardEffect(BLOCK, 5);
            defend.getCardEffectList().add(defendEffect);
            deck.add(defend);
        }
        Card bash = new Card("강타", CardType.Attack, "피해를 8 줍니다. 취약을 2 부여합니다.", 2);
        CardEffect bashEffect1 = new CardEffect(DAMAGE, 8);
        CardEffect bashEffect2 = new CardEffect(VULNERABLE, 2);
        bash.getCardEffectList().add(bashEffect1);
        bash.getCardEffectList().add(bashEffect2);
        deck.add(bash);

        drawPile.addAll(deck);

    }

    @Override
    void performTurn(){
        if (!isMyTurn()){
            return;
        }

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
