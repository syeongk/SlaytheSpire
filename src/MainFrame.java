import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {

    private ImageIcon backgroundImage;
    private ArrayList<JButton> buttons;
    private JButton start;
    private JButton record;
    private JButton exit;
    static JPanel cardPanel;
    static CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() throws IOException, FontFormatException {
        //프레임 설정
        setTitle("Slay the Spire");
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.NORMAL);
        //setResizable(false);

        File fontFile = new File("src/font/GyeonggiCheonnyeonBatangBold.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(30f);;

        //패널 전환하기 위한 카드 레이아웃
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);

        //배경 패널
        backgroundImage = new ImageIcon("src/imgs/main.png");

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridLayout(1, 3));
        cardPanel.add(mainPanel, "Main"); //배경 패널을 JFrame에 추가


        //패널 설정
        JPanel leftPanel = new JPanel(new GridLayout(2,1)); //버튼 넣을 패널 추가
        JPanel subPanel1 = new JPanel(); //mainPanel 1행 1열의 빈 패널
        JPanel subPanel2 = new JPanel();
        leftPanel.add(subPanel1);
        leftPanel.add(subPanel2);


        //mainPanel의 subPanel2에 시작 버튼, 종료 버튼 등 추가//
        buttons = new ArrayList<>();
        start = new JButton("게임 시작"); //버튼을 이미지로 설정
        record = new JButton("기록");
        exit = new JButton("종료");
        buttons.add(start);
        buttons.add(record);
        buttons.add(exit);

        //폰트 및 색상 설정
        start.setFont(font);
        start.setForeground(Color.WHITE);
        record.setFont(font);
        record.setForeground(Color.WHITE);
        exit.setFont(font);
        exit.setForeground(Color.WHITE);

        //버튼 배경 없애기
        start.setBorderPainted(false);
        record.setBorderPainted(false);
        exit.setBorderPainted(false);

        //버튼 사이즈 지정 및 포커스 제거
        start.setPreferredSize(new Dimension(300, 70));
        start.setFocusPainted(false);
        record.setPreferredSize(new Dimension(300, 70));
        record.setFocusPainted(false);
        exit.setPreferredSize(new Dimension(300, 70));
        exit.setFocusPainted(false);

        //버튼 패널에 추가
        subPanel2.add(start);
        subPanel2.add(record);
        subPanel2.add(exit);

        //버튼에 액션 리스너 추가
        start.addActionListener(this);

        for (JButton button : buttons) {

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (e.getSource() == start) {
                        start.setIcon(new ImageIcon("src/imgs/menu_option_highlight.png"));
                        start.setHorizontalTextPosition(SwingConstants.CENTER);  //	텍스트를 아이콘과 동일한 중심에 배치
                        start.setText("게임 시작");
                    } else if (e.getSource() == record) {
                        record.setIcon(new ImageIcon("src/imgs/menu_option_highlight.png"));
                        record.setHorizontalTextPosition(SwingConstants.CENTER);
                        record.setText("기록");
                    } else if (e.getSource() == exit) {
                        exit.setIcon(new ImageIcon("src/imgs/menu_option_highlight.png"));
                        exit.setHorizontalTextPosition(SwingConstants.CENTER);
                        exit.setText("종료");
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (e.getSource() == start) {
                        start.setIcon(null);
                        start.setText("게임 시작");
                    } else if (e.getSource() == record) {
                        record.setIcon(null);
                        record.setText("기록");
                    } else if (e.getSource() == exit) {
                        exit.setIcon(null);
                        exit.setText("종료");

                    }
                }
            });
            }


        //레이아웃을 위한 빈 패널, 메인 프레임의 1행 2열, 1행 3열 위치에 추가//
        JPanel centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();


        //패널 투명하게
        leftPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        rightPanel.setOpaque(false);
        subPanel1.setOpaque(false);
        subPanel2.setOpaque(false);


        mainPanel.add(leftPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(rightPanel);


        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start){
            JPanel characterSelection = null;
            try {
                characterSelection = new CharacterSelection(mainPanel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
            cardPanel.add(characterSelection, "CharacterSelection");
            cardLayout.show(cardPanel, "CharacterSelection");
        }
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new MainFrame();
    }
}
