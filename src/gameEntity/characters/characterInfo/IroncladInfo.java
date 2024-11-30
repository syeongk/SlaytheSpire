package gameEntity.characters.characterInfo;

import characterStatus.Health;
import characterStatus.Relic;


public class IroncladInfo extends CharacterInfo {

    public IroncladInfo(){
        super("아이언 클래드", new Health(80, 80), 99, "아이언클래드의 살아남은 병사입니다.\n악마의 힘을 사용하기 위해 영혼을 팔았습니다. ", new Relic("불타는 혈액","전투 종료 시 체력을 6 회복합니다."));
    }
}
