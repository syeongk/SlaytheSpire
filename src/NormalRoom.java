import javax.swing.*;
import java.awt.*;
import battle.character.Character;
import status.Energy;

public class NormalRoom extends JPanel {

    private final GameState gameState;
    private Character character;
    private Energy energy;

    public NormalRoom(GameState gameState){

        setLayout(new BorderLayout());

        this.gameState = gameState;
        add(gameState.getStatusBar(), BorderLayout.NORTH);


        this.character = gameState.getCharacter();
        energy = character.getEnergy();



    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawOval(100,700, 50, 50);
        g.drawString(energy.getCurrentEnergy() + "/" + energy.getMaxEnergy(), 100, 700);
    }
}
