package test;

import javax.swing.*;
import java.awt.*;

public class CardStackWithButtons extends JFrame {

    public CardStackWithButtons() {
        setTitle("Card Stack with JButton");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // JLayeredPane 생성
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 600);

        // JButton 카드 생성
        for (int i = 0; i < 5; i++) {
            JButton cardButton = createCardButton("Card " + (i + 1), i);
            int x = 300 + i * 20; // 겹치는 x 좌표
            int y = 200 + i * 10; // 겹치는 y 좌표

            cardButton.setBounds(x, y, 100, 150); // 카드 위치와 크기
            layeredPane.add(cardButton, i); // 레이어 설정
        }

        add(layeredPane);
        setVisible(true);
    }

    private JButton createCardButton(String text, int index) {
        JButton cardButton = new JButton(text);
        cardButton.setFocusPainted(false); // 버튼 클릭 시 포커스 표시 제거
        cardButton.setOpaque(true);
        cardButton.addActionListener(e -> System.out.println("Clicked: " + text)); // 클릭 이벤트 추가
        return cardButton;
    }

    public static void main(String[] args) {
        new CardStackWithButtons();
    }
}
