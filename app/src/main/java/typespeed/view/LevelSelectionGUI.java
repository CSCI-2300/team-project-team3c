package typespeed.view; 

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.util.List;

import typespeed.model.Difficulty;
import typespeed.model.GameModel; 
import typespeed.FileHandler; 

public class LevelSelectionGUI{
    private JFrame mainFrame;
    private TypespeedGUI gameGUI;  
    private GameModel gameModel; 
    private List<String> wordList; 
    private FileHandler fileHandler;

    public LevelSelectionGUI(){
        this.fileHandler = new FileHandler();
        Difficulty difficulty = Difficulty.EASY; // Default or based on user choice
        String filename = getFilenameForDifficulty(difficulty);
        this.wordList = fileHandler.loadWords(filename);

        this.gameModel = new GameModel(wordList, difficulty);

        mainFrame = new JFrame("Typespeed Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(400, 300));
        mainFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Typespeed", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); 
        mainFrame.add(titleLabel, BorderLayout.NORTH); 

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10)); 
        JButton easyButton = new JButton("Easy Level"); 
        JButton difficultButton = new JButton("Difficult Level"); 

        easyButton.addActionListener(e -> startGame(Difficulty.EASY));
        difficultButton.addActionListener(e -> startGame(Difficulty.HARD));

        bottomPanel.add(easyButton);
        bottomPanel.add(difficultButton);

        mainFrame.add(bottomPanel, BorderLayout.CENTER);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void startGame(Difficulty difficulty) {
        String filename = getFilenameForDifficulty(difficulty);
        if (fileHandler.doesFileExist(filename)) {
            List<String> selectedWordList = fileHandler.loadWords(filename);
            gameModel = new GameModel(selectedWordList, difficulty);
            if (gameGUI != null) {
                gameGUI.closeWindow(); 
            }
            gameGUI = new TypespeedGUI(gameModel, difficulty); // Ensure TypespeedGUI accepts Difficulty
            mainFrame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getFilenameForDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return "PlayEasy.txt";
            case HARD:
                return "PlayHard.txt";
            default:
                return "PlayEasy.txt"; // Default case
        }
    }
}
