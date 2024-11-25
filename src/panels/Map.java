package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Map extends JPanel implements ActionListener {

    private JButton normalRoom;
    private GameState gameState;
    CardLayout cardLayout = MainFrame.cardLayout;
    JPanel cardPanel = MainFrame.cardPanel;


    public Map(){

        this.gameState = GameState.getInstance();

        setLayout(new BorderLayout());
        add(gameState.getStatusBar(), BorderLayout.NORTH);


        JPanel center = new JPanel();
        add(center, BorderLayout.CENTER);
        normalRoom = new JButton("일반 몬스터");
        normalRoom.addActionListener(this);
        center.add(normalRoom);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource()==normalRoom) {
            MonsterRoom monsterRoom = new MonsterRoom();
            cardPanel.add(monsterRoom, "panels.NormalRoom");
            cardLayout.show(cardPanel, "panels.NormalRoom");
        }
    }

}
