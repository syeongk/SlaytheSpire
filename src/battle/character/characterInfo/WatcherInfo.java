package battle.character.characterInfo;

import status.Health;
import status.Relic;

public class WatcherInfo extends CharacterInfo {
    public WatcherInfo(){
        super("와쳐", new Health(72, 72), 99, "첨탑을 '평가'하기 위해 찾아온 눈먼 수도사입니다.\n강림의 경지에 이른 고수입니다", new Relic("순수한 물", "매 전투 시작 시 기적을 손으로 가져옵니다."));

    }
}
