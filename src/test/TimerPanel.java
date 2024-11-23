package test;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    int i = 0;
    public TimerPanel(){

        Timer timer = new Timer(1000, e->{
            i++;
            repaint();
            System.out.println(i);

            if (i>=5){
                ((Timer) e.getSource()).stop();
            }
        });

        timer.start();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(String.valueOf(i), 30, 30);
    }
}
