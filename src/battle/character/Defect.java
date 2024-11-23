package battle.character;

import status.Relic;

public class Defect extends Character{

    public Defect(){
        super("디펙트", 99, 75, 75, new Relic("부서진 핵", "전투 시작 시 전기를 1번 영창합니다."));
    }
    @Override
    void initDeck() {

    }

}
