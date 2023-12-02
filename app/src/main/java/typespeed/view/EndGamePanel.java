package typespeed.view;

import javax.swing.*;

import typespeed.controller.GameController;

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
            setLayout(new BorderLayout());
            System.out.println("TOOL_TIP_TEXT_KEY");
            
            JLabel scoreLabel = new JLabel("Your score: " +finalscore, SwingConstants.CENTER);
            add(scoreLabel, BorderLayout.CENTER);

            JButton restartButton = new JButton("Restart?");
            restartButton.addActionListener(restartAction);
            add(restartButton, BorderLayout.SOUTH);
        }
    }
