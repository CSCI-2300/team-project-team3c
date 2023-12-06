package typespeed.view;

import typespeed.HighScore;
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel("Your score: " + finalScore);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        HighScore highScoreManager = new HighScore();
        highScoreLabel = new JLabel("High Score: " + highScoreManager.getHighScore());
        highScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scorePanel.add(scoreLabel);
        scorePanel.add(Box.createRigidArea(new Dimension(0, 5))); // Space between labels
        scorePanel.add(highScoreLabel);

        add(scorePanel);

        JButton restartButton = new JButton("Play Again?");
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(restartAction);

        add(Box.createRigidArea(new Dimension(0, 10))); // Space before the button
        add(restartButton);
    }
}
