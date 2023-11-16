package typespeed.view; 
import typespeed.controller.GameController;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

public class LevelSelectionGUI {
    private JFrame mainFrame;
    private GameController controller; 

    public LevelSelectionGUI(){
        controller = new GameController(this); 
        mainFrame = new JFrame("Typespeed Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(400, 300));
        mainFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Typespeed", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial, Font.BOLD, 30")); 
        mainFrame.add(titleLabel, BorderLayout.NORTH); 

        bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10)); 
        JButton easyButton = new JButton("Play Easy Level"); 
        JButton difficultButton = new JButton("Play Difficult Level"); 
        
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                startGame("PlayEasy.txt");
            }
        }); 

        difficultButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                startGame("PlayHard.txt");
            }
        });

        buttonPanel.add(easyButton);
        buttonPanel.add(difficultButton);

        mainFrame.add(buttonPanel, BorderLayout.CENTER);

        mainFrame.pack(); 
        mainFrame.setVisible(true);
    }

    private void startGame(String difficulty){
        mainFrame.dispose(); 
        TypespeedGUI gameGUI = new TypespeedGUI(difficulty);
    }

}