package typespeed.view;
import typespeed.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGamePanel extends JPanel{
    private int finalscore;
    private ActionListener restartAction;

    public EndGamePanel(int finalscore, ActionListener restartAction){
        this.finalscore = finalscore;
        this.restartAction = restartAction;
        initializePanel();
    }

    private void initializePanel(){
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Your score: " +finalscore, SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.CENTER);

        JButton restartButton = new JButton("Play Again?");
        restartButton.addActionListener(restartAction);
        add(restartButton, BorderLayout.SOUTH);
    }
}
