package typespeed.view;

import typespeed.HighScore;
import typespeed.HighScore;  // Ensure this is the correct import for your HighScoreManager class
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndGamePanel extends JPanel {
    private int finalScore;
    private JLabel highScoreLabel; 
    private ActionListener restartAction;

    public EndGamePanel(int finalScore, ActionListener restartAction) {
        this.finalScore = finalScore;
        this.restartAction = restartAction;
        initializePanel();
    }

    private void initializePanel() {
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Your score: " + finalScore, SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.NORTH); 
        HighScore highScoreManager = new HighScore(); 
        highScoreLabel = new JLabel("High Score: " + highScoreManager.getHighScore(), SwingConstants.CENTER);
        add(highScoreLabel, BorderLayout.CENTER);

        JButton restartButton = new JButton("Play Again?");
        restartButton.addActionListener(restartAction);
        add(restartButton, BorderLayout.SOUTH);
    }
}
