package json;

import ui.GameState;
import ui.battleResult.Rewards;


public class SaveData {
    GameState gameState;

    public SaveData() {
        gameState = GameState.getInstance();
    }

    public GameState getGameState() {
        return gameState;
    }

}