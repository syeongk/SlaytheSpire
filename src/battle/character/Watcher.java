package battle.character;

import status.Relic;

public class Watcher extends Character{

    public Watcher(){
        super("와쳐", 99, 72, 72, new Relic("순수한 물", "매 전투 시작 시 기적을 손으로 가져옵니다."));
    }
    @Override
    void initDeck() {

    }
}
