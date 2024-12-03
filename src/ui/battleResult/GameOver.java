package ui.battleResult;

import ui.GameState;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOver extends JPanel {
    private ImageIcon bannerImageIcon;
    private ImageIcon rewardSheetImageIcon;
    private GameState gameState;

    public GameOver(){
        setBackground(new Color(0, 0, 0, 200)); // 검은색, 투명도 100 설정
        setOpaque(true);
        setLayout(null);

        gameState = GameState.getInstance();

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
        g.setColor(Color.BLACK);
        g.setFont(MainFrame.font);
        g.drawString("패배", 770, 190);
        g.setColor(Color.WHITE);
        g.setFont(MainFrame.smallFont);
        int floor =  gameState.getStatusBar().getFloor();
        int monsters = gameState.getKilledMonsters();
        int elites = gameState.getKilledElites();
        int bosses = gameState.getKilledBosses();
        int score = (floor * 5) + (monsters * 2) + (elites * 10) + (bosses * 50);
        g.drawString("오른층 수" + "(" + floor + ")", 600, 300);
        g.drawString("처치한 적" + "(" + monsters + ")", 600, 350);
        g.drawString("엘리트 처치" + "(" + elites + ")", 600, 400);
        g.drawString("보스 처치" + "(" + bosses + ")", 600, 450);
        g.setFont(MainFrame.font);
        g.drawString("점수 : " + score, 600, 600);
    }

    public ImageIcon scaleImage(String imagePath, int w, int h){
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaleImage = originalIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(scaleImage);

        return scaleIcon;
    }
}

