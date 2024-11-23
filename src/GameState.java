import status.StatusBar;
import battle.character.Character;

public class GameState {
    private static GameState instance;
    private StatusBar statusBar;
    private Character character;
    private int turnCount = 1;


    public GameState(Character character){
        this.character = character;
        this.statusBar = new StatusBar(this.character);
    }

    public static GameState getInstance(Character character){
        if (instance == null){
            instance = new GameState(character);
        }
        return instance;
    }

    public boolean isMyTurn(){
        if (turnCount % 2 == 1)
            return true;
        else
            return false;
    }

    public StatusBar getStatusBar(){
        return statusBar;
    }

    public Character getCharacter(){
        return character;
    }
}
