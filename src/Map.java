import status.StatusBar;
import battle.character.Character;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {

    private StatusBar statusBar;
    private Character character;

    public Map(Character character){
        this.character = character;
        statusBar = new StatusBar(this.character);



        JPanel p = new JPanel();
        p.add(statusBar);

        add(p);

    }
}
