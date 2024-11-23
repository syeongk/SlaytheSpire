package characters.characterInfo;

import characterStatus.Health;
import characterStatus.Relic;

public class SilentInfo extends CharacterInfo {

    public SilentInfo(){
        super("사일런트", new Health(70, 70), 99, "안개 지대에서 온 치명적인 사냥꾼입니다. \n단검과 독으로 적들을 박멸합니다. ", new Relic("뱀의 반지", "첫 턴에만 2장의 카드를 추가로 뽑습니다."));
    }
}
