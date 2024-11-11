import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private ImageIcon backgroundImage;
    private JButton start;
    private JButton record;
    private JButton exit;
    static JPanel cardPanel;
    static CardLayout cardLayout;

    public MainFrame(){
        //프레임 설정
        setTitle("Slay the Spire");
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);


        //패널 전환하기 위한 카드 레이아웃
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);


        //배경 패널
        backgroundImage = new ImageIcon("src/imgs/background1.jpg");

        JPanel mainPanel = new JPanel() {
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


        //mainPanel의 subPanel2에 시작 버튼, 종료 버튼 등 추가
        start = new JButton(new ImageIcon("src/imgs/start.png")); //버튼을 이미지로 설정
        record = new JButton("기록");
        exit = new JButton("게임 종료");
        start.setPreferredSize(new Dimension(300, 70)); //버튼 크기 지정
        start.setFocusPainted(false);   //버튼 포커스 표시 제거
        record.setPreferredSize(new Dimension(300, 70));
        record.setFocusPainted(false);
        exit.setPreferredSize(new Dimension(300, 70));
        exit.setFocusPainted(false);

        subPanel2.add(start);
        subPanel2.add(record);
        subPanel2.add(exit);

        start.addActionListener(this);


        //레이아웃을 위한 빈 패널, 메인 프레임의 1행 2열, 1행 3열 위치에 추가
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
            JPanel characterSelection = new CharacterSelection();
            cardPanel.add(characterSelection, "CharacterSelection");
            cardLayout.show(cardPanel, "CharacterSelection");
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
