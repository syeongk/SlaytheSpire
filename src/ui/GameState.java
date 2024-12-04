package ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gameEntity.characters.Character;
import gameEntity.monsters.Monster;

import java.util.LinkedList;

public class GameState {

    private static GameState instance;
    @JsonIgnore
    private StatusBar statusBar;
    private Character character;
    private int turnCount = 1;
    private int floor;
    private LinkedList<Monster> monsters;
    private int killedMonsters;
    private int killedElites;
    private int killedBosses;

    private GameState(Character character){
        this.character = character;
        this.statusBar = new StatusBar(character);
        this.monsters = new LinkedList<>();
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
    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public LinkedList<Monster> getMonsters() {
        return monsters;
    }

    public void removeMonster(Monster monster){
        monsters.remove(monster);
    }

    public static void setInstance(GameState instance) {
        GameState.instance = instance;
    }

    public int getKilledElites() {
        return killedElites;
    }

    public void setKilledElites(int killedElites) {
        this.killedElites = killedElites;
    }

    public int getKilledBosses() {
        return killedBosses;
    }

    public void setKilledBosses(int killedBosses) {
        this.killedBosses = killedBosses;
    }

    public int getKilledMonsters() {
        return killedMonsters;
    }

    public void setKilledMonsters(int killedMonsters) {
        this.killedMonsters = killedMonsters;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
