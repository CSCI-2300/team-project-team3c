package typespeed.controller;

import typespeed.model.Word;
import typespeed.model.GameModel; 
import typespeed.view.GameObserver;

import java.util.ArrayList;
import java.util.List; 
import java.util.Timer; 
import java.util.TimerTask;  

public class GameController{
    
    private Timer wordTimer;
    private GameModel gameModel; 
    private GameObserver view; 

    public GameController(List<String> wordList, GameObserver view){
        this.gameModel = new GameModel(wordList); 
        this.view = view; 
    }

    public void startGame(){
        gameModel.startGame(); 
        startWordGeneration(); 
        startWordMovement(); 
    }

    private void startWordGeneration(){
        wordTimer = new Timer(); 
        wordTimer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                gameModel.generateWord(); 
            }
        }, 0, 5000);
    }

    private void startWordMovement(){
        Timer wordMovementTimer = new Timer(); 
        wordMovementTimer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                gameModel.moveWords(); 
                view.updateWordPositions(gameModel.getWords());
            }
        }, 0, 100); 
    }

    public void checkWord(String userInput){
        gameModel.checkWord(userInput); 
        view.updateAndShowScore(gameModel.getScore());
    }
}