package statusEffect;

public enum StatusEffect {
    Vulnerable("취약", "50% 더 많은 피해를 입습니다.", 1),
    Weak("약화", "공격 으로 25% 적은 피해를 입힙니다.", 1),
    Strength("힘", "공격력이 증가합니다.", 2),
    Block("방어", "적의 공격을 방어합니다.", 0);

    private final String name;
    private final String description;
    private int duration; //0 : 턴이 끝나면 사라짐, 1 : 1씩 줄어듦, 2 : 지속됨


    StatusEffect(String name, String description, int duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

}
