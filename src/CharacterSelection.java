import battle.character.Ironclad;
import battle.character.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterSelection extends JPanel implements ActionListener {
    private JButton ironclad;
    private JButton silent;
    private JButton defect;
    private JButton watcher;
    private int selectedCharacter = 1;
    private Character character;

    JButton departure;

    CardLayout cardLayout = MainFrame.cardLayout;
    JPanel cardPanel = MainFrame.cardPanel;


    public CharacterSelection(){

        setLayout(new BorderLayout());

        JPanel south = new JPanel();
        add(south, BorderLayout.SOUTH);

        JPanel east = new JPanel();
        add(east, BorderLayout.EAST);

        ironclad = new JButton("아이언 클래드");
        silent = new JButton("사일런트");
        defect = new JButton("디펙트");
        watcher = new JButton("와쳐");
        south.add(ironclad);
        south.add(silent);
        south.add(defect);
        south.add(watcher);
        ironclad.addActionListener(this);
        silent.addActionListener(this);
        defect.addActionListener(this);
        watcher.addActionListener(this);


        departure = new JButton("출정하기");
        east.add(departure);
        departure.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == ironclad) {
            // TODO: 아이언 클래드 정보
            selectedCharacter = 1;
        } else if (e.getSource() == silent){
            selectedCharacter = 2;
        } else if (e.getSource() == defect){
            selectedCharacter = 3;
        } else if (e.getSource() == watcher){
            selectedCharacter = 4;
        } else if (e.getSource() == departure){
            if (selectedCharacter == 1){
                character = new Ironclad();
            }
            Map map = new Map(character);
            cardPanel.add(map, "Map");
            cardLayout.show(cardPanel, "Map");
        }
    }
}
