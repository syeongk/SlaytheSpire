package test;

import javax.swing.*;

public class TimerExample extends JFrame {

    int i = 0;
    public TimerExample(){
        setTitle("Slay the Spire");
        setSize(100, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.NORMAL);

        JPanel p = new TimerPanel();
        add(p);

        setVisible(true);

    }
    public static void main(String[] args){
        new TimerExample();
    }
}
