package panels;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    static JPanel cardPanel;
    static CardLayout cardLayout;
    static File fontFile = new File("src/font/GyeonggiCheonnyeonBatangBold.ttf");
    static Font font;
    static Font smallFont;


    public MainFrame() throws IOException, FontFormatException {
        // 프레임 설정
        setTitle("Slay the Spire");
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.NORMAL);

        //폰트 설정
        font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(30f);
        smallFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(15f);

        // 패널 전환을 위한 CardLayout 설정
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);

        // MainPanel 추가
        cardPanel.add(new MainPanel(), "Main");

        setVisible(true);
    }

    public static void addPanel(JPanel panel, String panelName) {
        cardPanel.add(panel, panelName);
    }

    public static void switchPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }
}
