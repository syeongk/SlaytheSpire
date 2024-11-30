package gameEntity.monsters.weak;

import gameEntity.characters.Character;
import gameEntity.monsters.Monster;
import gameEntity.monsters.MonsterRank;

import card.Card;

import java.util.Stack;

import static card.CardType.StatusEffect;
import static statusEffect.StatusType.Weak;

public class SlimeM extends Monster {

    Stack<Integer> usedSkills = new Stack<>(); //1:lick, 2:tackle, 3:corrosiveSpit

    public SlimeM(){
        super(initHealth(), MonsterRank.WEAK, "src/imgs/slimeM.png", 950, 350);
        damage = 10;
    }

    public static int initHealth(){
        return r.nextInt(28,33);
    }

    public Object[] lick(){
        Object[] statusEffect = new Object[2];
        statusEffect[0] = Weak;
        statusEffect[1] = 1;
        usedSkills.push(1);
        System.out.println("lick");
        return statusEffect;
    }

    public void tackle(){
        attack();
        System.out.println("tackle");
        usedSkills.push(2);
    }

    public void corrosiveSpit(){
        attack();

        Character character = gameState.getCharacter();
        Card slime = new Card("슬라임", StatusEffect, 1);
        character.addTemporaryPile(slime);
        System.out.println("corrosiveSpit");
        usedSkills.push(3);
    }

    @Override
    public void performTurn(){
        while(true){

            int selection = r.nextInt(1,101);

            if (selection <= 30 && usedSkillsCheck(3)){
                corrosiveSpit();
                break;
            } else if (selection > 30 && selection <= 70 && usedSkillsCheck(2)){
                tackle();
                break;
            } else if (selection > 70 && selection <= 100 && usedSkillsCheck(1)){
                lick();
                break;
            }
        }
    }

    public boolean usedSkillsCheck(int skill){

        if (usedSkills.isEmpty()){
            return true;
        } else if (usedSkills.size() == 1) {
            int usedSkill = usedSkills.pop();
            if (skill != usedSkill)
                return true;
            else {
                usedSkills.push(usedSkill);
                if (skill != 3)
                    return false;
                else
                    return true;
            }
        } else {
            if (skill != 3) {
                usedSkills.clear();
                return true;
            } else {
                return false;
            }
        }


    }

}
