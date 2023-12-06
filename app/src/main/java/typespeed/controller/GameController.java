package typespeed.controller;

import typespeed.model.Word;
import typespeed.IGameModel;
import typespeed.model.GameModel; 
import typespeed.view.TypespeedGUI;

import java.util.ArrayList;
import java.util.List; 
import java.util.Timer; 
import java.util.TimerTask;  

import typespeed.view.IGameView;

public class GameController{
    private final IGameModel gameModel;
    private final IGameView gameView;
    private boolean isGameRunning; 


    private Timer gameTimer;

    public GameController(IGameModel gameModel, IGameView gameView){
        this.gameModel = gameModel; 
        this.gameView = gameView; 
        this.isGameRunning = false; 
        startGameTimer(); 
    }

    public void startGame(){
        gameModel.startGame(); 
        startWordGeneration(); 
        startWordMovement(); 
        startGameTimer();
        isGameRunning = true; 
    }

    public void restartGame(){
        gameTimer.cancel(); 
        startGameTimer(); 
        gameModel.startGame();
        isGameRunning = true; 
    }

    private void startGameTimer() {
        if (gameTimer != null) {
            gameTimer.cancel();
            gameTimer.purge(); 
        }

        gameTimer = new Timer();

        gameTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (gameModel != null && gameView != null) {
                    int gameTime = gameModel.getGameTime();
                    gameTime--;
                    gameModel.setGameTime(gameTime);
                    gameView.updateTimer(gameTime);

                    if (gameTime <= 0) {
                        cancelGameTimer();
                        gameModel.endGame();
                        isGameRunning = false;
                        gameView.displayGameOver(); 
                    }
                } else {
                    cancelGameTimer(); 
                }
            }
        }, 0, 1000);
    }

    private void cancelGameTimer(){
        if(gameTimer != null){
            gameTimer.cancel(); 
            gameTimer.purge(); 
            gameTimer = null; 
        }
    }

    public boolean isGameRunning(){
        return isGameRunning; 
    }
    

    private void startWordGeneration(){
        Timer wordTimer = new Timer(); 
        wordTimer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                if (!gameModel.isGameOver()) {
                    gameModel.generateWord(); 
                } else {
                    wordTimer.cancel(); // Stop generating words when the game is over
                }
            }
        }, 0, 4000); // every 3 seconds 
    }
    

    private void startWordMovement(){
        Timer wordMovementTimer = new Timer(); 
        wordMovementTimer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                gameModel.moveWords(); 
                gameView.updateWordPositions(gameModel.getWords());
                gameView.updateMissedWords(gameModel.getMissedWordsCount());
            }
        }, 0, 100); 
    }

    public List<Word> getCurrentWords(){
        return gameModel.getCurrentWords();
    }

    public void checkWord(String userInput) {
        gameModel.checkWord(userInput);
        gameView.updateAndShowScore(gameModel.getScore());
    }
}