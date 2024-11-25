import panels.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        JFrame mainFrame = new MainFrame();
        System.out.println(mainFrame.getX());
        System.out.println(mainFrame.getY());
    }
}