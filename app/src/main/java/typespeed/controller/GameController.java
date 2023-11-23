package typespeed.controller;

import typespeed.model.Word;
import typespeed.model.GameModel; 
import typespeed.view.GameObserver;
import typespeed.view.TypespeedGUI;

import java.util.ArrayList;
import java.util.List; 
import java.util.Timer; 
import java.util.TimerTask;  

public class GameController{
    
    private Timer gameTimer;
    private GameModel gameModel; 
    private GameObserver view; 
    private TypespeedGUI Tview;

    public GameController(List<String> wordList, GameObserver view){
        this.gameModel = new GameModel(wordList); 
        this.view = view; 
        startGameTimer(); 
    }

    public void startGame(){
        gameModel.startGame(); 
        startWordGeneration(); 
        startWordMovement(); 
    }

    private void startGameTimer() {
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                int gameTime = gameModel.getGameTime();
                gameTime--;
                gameModel.setGameTime(gameTime);
                view.updateTimer(gameTime);
                if (gameTime <= 0) {
                    gameTimer.cancel();
                    gameModel.endGame();
                }
            }
        }, 0, 1000);
    }

    private void startWordGeneration(){
        Timer wordTimer = new Timer(); 
        wordTimer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                gameModel.generateWord(); 
            }
        }, 0, 5000); // every 5 second 
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

    public List<Word> getCurrentWords(){
        return gameModel.getCurrentWords();
    }

    public void checkWord(String userInput) {
        if (gameModel != null) {
            gameModel.checkWord(userInput);
            view.updateAndShowScore(gameModel.getScore());
            // Assuming 'refreshDisplay' is a method in TypespeedGUI that updates the UI
            if (Tview != null) {
                Tview.refreshDisplay();
            }
        } else {
            System.out.println("GameModel is not initialized.");
        }
    }
}