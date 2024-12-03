package ui.battleResult;

import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rewards extends JPanel {
    private ImageIcon bannerImageIcon;
    private ImageIcon rewardSheetImageIcon;

    public Rewards(){
        setBackground(new Color(0, 0, 0, 200)); // 검은색, 투명도 100 설정
        setOpaque(true);
        setLayout(null);

        bannerImageIcon = scaleImage("src/imgs/selectBanner.png", 900, 200);
        rewardSheetImageIcon = scaleImage("src/imgs/rewardScreenSheet.png", 700, 600);
        JButton toMap = new JButton(scaleImage("src/imgs/proceedButton.png", 230, 150));
        toMap.setFont(MainFrame.font);
        toMap.setForeground(Color.WHITE);
        toMap.setText("다음 층");
        toMap.setBounds(1200, 600, 230, 150);
        toMap.setHorizontalTextPosition(SwingConstants.CENTER);
        toMap.setBorderPainted(false);
        toMap.setFocusPainted(false);
        toMap.setOpaque(false);
        add(toMap);
        toMap.addActionListener(e -> {
            if(e.getSource() == toMap){
                MainFrame.switchPanel("Map");
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
            }
        });
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(rewardSheetImageIcon.getImage(), 800 - (rewardSheetImageIcon.getIconWidth() / 2), 150 , null);
        g.drawImage(bannerImageIcon.getImage(), 800 - (bannerImageIcon.getIconWidth() / 2), 100, null);
        g.setColor(Color.BLACK);
        g.setFont(MainFrame.font);
        g.drawString("전리품!", 750, 190);
    }

    public ImageIcon scaleImage(String imagePath, int w, int h){
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaleImage = originalIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(scaleImage);

        return scaleIcon;
    }
}
