/*package typespeed.view;

import javax.swing.*;

import typespeed.controller.GameController;

public class EndGamePanel extends JPanel{
    private JLabel scoreLabel;
    private JButton restartButton, exitButton;

    public EndGamePanel(int finalscore, GameController gameController){
        scoreLabel = new JLabel("Your Score: " + finalscore);
        restartButton = new JButton ("Restart");
        exitButton = new JButton ("Exit");

        restartButton.addActionListener(e -> gameController.restartGame());
        exitButton.addActionListener(e -> gameController.exitGame());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(scoreLabel);
        this.add(restartButton);
        this.add(exitButton);
    }
}*/



