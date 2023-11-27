package typespeed.view; 

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.util.List;
import typespeed.model.GameModel; 
import typespeed.FileHandler; 

public class LevelSelectionGUI{
    private JFrame mainFrame;
    private TypespeedGUI gameGUI;  
    private GameModel gameModel; 
    private List<String> wordList; 
    private FileHandler fileHandler;

    public LevelSelectionGUI(){
        this.fileHandler = new FileHandler(); // Ensure this is properly initialized
        String difficulty = "PlayEasy.txt";
        this.wordList = fileHandler.loadWords(difficulty);
    
        this.gameModel = new GameModel(wordList);

        mainFrame = new JFrame("Typespeed Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(400, 300));
        mainFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Typespeed", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); 
        mainFrame.add(titleLabel, BorderLayout.NORTH); 

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10)); 
        JButton easyButton = new JButton("Play Easy Level"); 
        JButton difficultButton = new JButton("Play Difficult Level"); 

        easyButton.addActionListener(e -> startGame("PlayEasy.txt"));
        difficultButton.addActionListener(e -> startGame("PlayHard.txt"));

        bottomPanel.add(easyButton);
        bottomPanel.add(difficultButton);

        mainFrame.add(bottomPanel, BorderLayout.CENTER);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

private void startGame(String difficulty){
    if (fileHandler.doesFileExist(difficulty)) {
        if(gameGUI != null){
            gameGUI.closeWindow(); 
        }
        List<String> selectedWordList = fileHandler.loadWords(difficulty);
        gameGUI = new TypespeedGUI(gameModel, difficulty);
    } else {
        JOptionPane.showMessageDialog(null, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}