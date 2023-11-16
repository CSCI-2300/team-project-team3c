package typespeed.controller;

import typespeed.model.Word;
import typespeed.view.GameObserver;

import java.awt.*;
import java.util.*;

public class GameController{
    
    private Timer wordTimer;
    private Timer gameTimer;
    private int gameTime = 60; 
    private int wordSpeed = 1; 
    private Word currentWord; 
    private TypespeedGUI view; 

    public GameController(TypespeedGUI view){
        this.view = view;
        startGame(); 
    }

    private void startGame(){
        wordTimer = new Timer(); 
        wordTimer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                generateWord(); 
            }
        }, 0, 5000);

        gameTimer = new Timer(); 
        gameTimer.scheduleAtFixedRate(new TimerTask(){
            public void run() {
                gameTime--;
                view.updateTimer(gameTime);
                if(gameTime <= 0){
                    endGame();
                }
            }
        }, 0, 1000); 
    }

    private void generateWord(){
        // code to generate words at random positions at the determined speed
        // update the `currentWord' and notify the view
    }

    private void endGame(){
        wordTimer.cancel(); 
        gameTimer.cancel(); 
        // other actions 
    }

    // methods to handle user input, word movement, scoring
}