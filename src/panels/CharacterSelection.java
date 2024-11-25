package panels;

import characters.*;
import characters.Character;
import characters.characterInfo.*;
import characterStatus.Relic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static panels.GameState.getInstance;

public class CharacterSelection extends JPanel implements ActionListener {
    private JButton ironclad;
    private JButton silent;
    private JButton defect;
    private JButton watcher;

    private int selectedCharacter = 0;

    private Character character;
    private GameState gameState;
    private JPanel mainPanel;

    JButton departure;
    JButton cancel;

    CardLayout cardLayout = MainFrame.cardLayout;
    JPanel cardPanel = MainFrame.cardPanel;

    ImageIcon backgroundImage;

    CharacterInfo[] characterInfos;

    JLabel characterNameLabel;
    JLabel characterHealthLabel;
    JLabel characterMoneyLabel;
    String[] characterExplanation;
    JLabel characterExplanationLabel1;
    JLabel characterExplanationLabel2;
    Relic characterRelic;
    JLabel characterRelicLabel;
    JLabel characterRelicContentLabel;

    Font font;

    public CharacterSelection(JPanel mainPanel) throws IOException, FontFormatException {
        this.mainPanel = mainPanel;

        File fontFile = new File("src/font/GyeonggiCheonnyeonBatangBold.ttf");

        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getResource("/images/ui/charSelect/ironcladPortrait.jpg"));
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel);


        //패널 추가//
        JPanel south = new JPanel();
        JPanel east = new JPanel();
        JPanel west = new JPanel();
        JPanel center = new JPanel(new GridLayout(7,1, 0, -350));

        backgroundPanel.add(south, BorderLayout.SOUTH);
        backgroundPanel.add(east, BorderLayout.EAST);
        backgroundPanel.add(west, BorderLayout.WEST);
        backgroundPanel.add(center, BorderLayout.CENTER);

        south.setOpaque(false);
        east.setOpaque(false);
        west.setOpaque(false);
        center.setOpaque(false);


        //캐릭터 정보//
        characterInfos = new CharacterInfo[4];
        characterInfos[0] = new IroncladInfo();
        characterInfos[1] = new SilentInfo();
        characterInfos[2] = new DefectInfo();
        characterInfos[3] = new WatcherInfo();


        //캐릭터 라벨 추가
        ArrayList<JLabel> characterLabels = new ArrayList<>();
        characterNameLabel = new JLabel(characterInfos[0].getName());
        characterHealthLabel = new JLabel("체력 : " + characterInfos[0].getHealth().getCurrentHealth()+ "/" + characterInfos[0].getHealth().getMaxHealth());
        characterMoneyLabel = new JLabel("골드 : " + characterInfos[0].getMoney());
        characterExplanation = characterInfos[0].getExplanation().split("\n");
        characterExplanationLabel1 = new JLabel(characterExplanation[0]);
        characterExplanationLabel2 = new JLabel(characterExplanation[1]);
        characterRelic = characterInfos[0].getRelic();
        characterRelicLabel = new JLabel(characterRelic.getName());
        characterRelicContentLabel = new JLabel(characterRelic.getRelicContent());
        characterLabels.add(characterNameLabel);
        characterLabels.add(characterHealthLabel);
        characterLabels.add(characterMoneyLabel);
        characterLabels.add(characterExplanationLabel1);
        characterLabels.add(characterExplanationLabel2);
        characterLabels.add(characterRelicLabel);
        characterLabels.add(characterRelicContentLabel);


        //캐릭터 라벨 폰트 설정 및 패널에 추가
        for (int i=0; i<characterLabels.size(); i++){
            JLabel characterLabel = characterLabels.get(i);
            if (i==0)
                characterLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(50f));
            else
                characterLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(20f));
            characterLabel.setForeground(Color.WHITE);
            center.add(characterLabel);
        }


        //캐릭터 버튼 추가//
        ironclad = new JButton(new ImageIcon("src/imgs/ironcladButton.png"));
        silent = new JButton(new ImageIcon("src/imgs/silentButton.png"));
        defect = new JButton(new ImageIcon("src/imgs/defectButton.png"));
        watcher = new JButton(new ImageIcon("src/imgs/watcherButton.png"));

        //버튼 배경 & 포커스 없애기
        ironclad.setBorderPainted(false);
        silent.setBorderPainted(false);
        defect.setBorderPainted(false);
        watcher.setBorderPainted(false);
        ironclad.setFocusPainted(false);
        silent.setFocusPainted(false);
        defect.setFocusPainted(false);
        watcher.setFocusPainted(false);

        //버튼들 패널에 추가
        south.add(ironclad);
        south.add(silent);
        south.add(defect);
        south.add(watcher);

        //버튼들 액션 리스너 추가
        ironclad.addActionListener(this);
        silent.addActionListener(this);
        defect.addActionListener(this);
        watcher.addActionListener(this);

        //출정하기 버튼 추가//
        font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(30f);
        departure = new JButton(new ImageIcon("src/imgs/confirmButton.png"));
        departure.setHorizontalTextPosition(SwingConstants.CENTER);
        departure.setText("출정");
        departure.setForeground(new Color(0, 41, 39));
        departure.setFont(font);
        east.add(departure);
        departure.addActionListener(this);
        departure.setBorderPainted(false);
        departure.setFocusPainted(false);

        //뒤로 버튼 추가//
        cancel = new JButton(new ImageIcon("src/imgs/cancelButton.png"));
        cancel.setHorizontalTextPosition(SwingConstants.CENTER);
        cancel.setText("뒤로");
        cancel.setFont(font);
        cancel.setForeground(new Color(49,20,18));
        west.add(cancel);
        cancel.addActionListener(this);
        cancel.setBorderPainted(false);
        cancel.setFocusPainted(false);

    }

    //버튼들에 마우스 리스너 추가
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == ironclad) {
            // TODO: 아이언 클래드 정보
            selectedCharacter = 0;
            backgroundImage = new ImageIcon(getClass().getResource("/images/ui/charSelect/ironcladPortrait.jpg"));
            repaint();
        } else if (e.getSource() == silent){
            selectedCharacter = 1;
            backgroundImage = new ImageIcon(getClass().getResource("/images/ui/charSelect/silentPortrait.jpg"));
            repaint();
        } else if (e.getSource() == defect){
            selectedCharacter = 2;
            backgroundImage = new ImageIcon(getClass().getResource("/images/ui/charSelect/defectPortrait.jpg"));
            repaint();
        } else if (e.getSource() == watcher){
            selectedCharacter = 3;
            backgroundImage = new ImageIcon(getClass().getResource("/images/ui/charSelect/watcherPortrait.jpg"));
            repaint();
        }
        setCharacterLabels(selectedCharacter);
        repaint();

        //출정 버튼
        if (e.getSource() == departure) {
            if (selectedCharacter == 0){
                character = new Ironclad();
            } else if (selectedCharacter == 1){
                character = new Silent();
            } else if (selectedCharacter == 2){
                character = new Defect();
            } else if (selectedCharacter == 3){
                character = new Watcher();
            }

            gameState = getInstance(character);
            Map map = new Map();
            cardPanel.add(map, "panels.Map");
            cardLayout.show(cardPanel, "panels.Map");
        }

        //뒤로 버튼
        if (e.getSource() == cancel){
            //cardLayout.show(cardPanel, "GameStart");
        }
    }

    public void setCharacterLabels(int selectedCharacter){
        characterNameLabel.setText(characterInfos[selectedCharacter].getName());
        characterHealthLabel.setText("체력 : " + characterInfos[selectedCharacter].getHealth().getCurrentHealth()+ "/" + characterInfos[selectedCharacter].getHealth().getMaxHealth());
        characterMoneyLabel.setText("골드 : " + characterInfos[selectedCharacter].getMoney());
        characterExplanation = characterInfos[selectedCharacter].getExplanation().split("\n");
        characterExplanationLabel1.setText(characterExplanation[0]);
        characterExplanationLabel2.setText(characterExplanation[1]);
        characterRelic = characterInfos[selectedCharacter].getRelic();
        characterRelicLabel.setText(characterRelic.getName());
        characterRelicContentLabel.setText(characterRelic.getRelicContent());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

    }
}
