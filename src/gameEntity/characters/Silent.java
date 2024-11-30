package gameEntity.characters;

import characterStatus.Relic;

public class Silent extends Character {

    public Silent(){
        super("사일런트", 99, 70, 70, new Relic("뱀의 반지", "첫 턴에만 2장의 카드를 추가로 뽑습니다."), "src/imgs/silent.png");
    }
    @Override
    void initDeck() {

    }
}
