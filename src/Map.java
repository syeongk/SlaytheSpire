import status.StatusBar;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {

    private StatusBar statusBar = new StatusBar();

    public Map(){

        JPanel p = new JPanel();
        p.add(statusBar);

        add(p);

    }
}
