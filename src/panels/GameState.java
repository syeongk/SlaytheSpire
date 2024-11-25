package panels;

import characters.Character;

public class GameState {
    private static GameState instance;
    private StatusBar statusBar;
    private Character character;
    private int turnCount = 1;


    private GameState(Character character){
        this.character = character;
        this.statusBar = new StatusBar(this.character);
    }

    public static GameState getInstance(Character character){
        if (instance == null){
            instance = new GameState(character);
        }
        return instance;
    }

    public static GameState getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GameState is not initialized. Call getInstance(Character character) first.");
        }
        return instance;
    }

    public boolean isMyTurn(){
        return turnCount % 2 == 1;
    }

    public StatusBar getStatusBar(){
        return statusBar;
    }

    public Character getCharacter(){
        return character;
    }
}
