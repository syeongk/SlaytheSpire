package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameOver extends JPanel {
    private ImageIcon bannerImageIcon;
    private ImageIcon rewardSheetImageIcon;

    public GameOver(){
        setBackground(new Color(0, 0, 0, 200)); // 검은색, 투명도 100 설정
        setOpaque(true);
        setLayout(null);

        bannerImageIcon = scaleImage("src/imgs/selectBanner.png", 900, 200);
        rewardSheetImageIcon = scaleImage("src/imgs/rewardScreenSheet.png", 700, 600);
        JButton toMain = new JButton(scaleImage("src/imgs/endTurnButton.png", 300, 250));
        toMain.setFont(MainFrame.font);
        toMain.setForeground(Color.WHITE);
        toMain.setText("메인 메뉴");
        toMain.setBounds(650, 700, 300, 150);
        toMain.setHorizontalTextPosition(SwingConstants.CENTER);
        toMain.setBorderPainted(false);
        toMain.setFocusPainted(false);
        toMain.setOpaque(false);
        add(toMain);
        toMain.addActionListener(e -> {
            if(e.getSource() == toMain){
                MainFrame.switchPanel("Main");
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
            }
        });

        GameState.setInstance(null);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(rewardSheetImageIcon.getImage(), 800 - (rewardSheetImageIcon.getIconWidth() / 2), 150 , null);
        g.drawImage(bannerImageIcon.getImage(), 800 - (bannerImageIcon.getIconWidth() / 2), 100, null);
    }

    public ImageIcon scaleImage(String imagePath, int w, int h){
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaleImage = originalIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(scaleImage);

        return scaleIcon;
    }
}

