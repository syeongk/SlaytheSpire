package gameEntity.characters.characterInfo;

import characterStatus.Health;
import characterStatus.Relic;


public class DefectInfo extends CharacterInfo {
    public DefectInfo(){
        super("디펙트", new Health(75, 75), 99, "자아를 깨달은 전투 자동인형입니다.\n고대의 기술로 구체를 만들 수 있습니다.", new Relic("부서진 핵","전투 시작 시 전기를 1번 영창합니다."));
    }
}
