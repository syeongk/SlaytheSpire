package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainPanel extends JPanel implements ActionListener {

    private ImageIcon backgroundImage;
    private ArrayList<JButton> buttons;
    private JButton start;
    private JButton record;
    private JButton exit;

    public MainPanel() throws IOException, FontFormatException {
        // 배경 이미지 설정
        backgroundImage = new ImageIcon("src/imgs/main.png");

        setLayout(new GridLayout(1, 3));

        // 배경을 그리기 위한 오버라이드
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridLayout(1, 3));
        add(mainPanel);

        // 폰트 설정
        File fontFile = new File("src/font/GyeonggiCheonnyeonBatangBold.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(30f);

        // 왼쪽 패널 생성
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        JPanel subPanel1 = new JPanel();
        JPanel subPanel2 = new JPanel();
        leftPanel.add(subPanel1);
        leftPanel.add(subPanel2);

        // 버튼 생성 및 설정
        buttons = new ArrayList<>();
        start = new JButton("게임 시작");
        record = new JButton("기록");
        exit = new JButton("종료");
        buttons.add(start);
        buttons.add(record);
        buttons.add(exit);

        // 버튼 스타일 설정
        for (JButton button : buttons) {
            button.setFont(font);
            button.setForeground(Color.WHITE);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(300, 70));
        }

        // 버튼 이벤트 추가
        start.addActionListener(this);
        for (JButton button : buttons) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton source = (JButton) e.getSource();
                    source.setIcon(new ImageIcon("src/imgs/menu_option_highlight.png"));
                    source.setHorizontalTextPosition(SwingConstants.CENTER);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JButton source = (JButton) e.getSource();
                    source.setIcon(null);
                }
            });
        }

        // 버튼 패널에 추가
        subPanel2.add(start);
        subPanel2.add(record);
        subPanel2.add(exit);

        // 왼쪽, 중간, 오른쪽 패널 추가
        JPanel centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        rightPanel.setOpaque(false);
        subPanel1.setOpaque(false);
        subPanel2.setOpaque(false);

        mainPanel.add(leftPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(rightPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            try {
                MainFrame.addPanel(new CharacterSelection(), "CharacterSelection");
                MainFrame.switchPanel("CharacterSelection");
            } catch (IOException | FontFormatException ex) {
                ex.printStackTrace();
            }
        }
    }
}
