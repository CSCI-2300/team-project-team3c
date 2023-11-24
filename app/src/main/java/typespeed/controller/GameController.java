package typespeed.controller;

import typespeed.model.Word;
import typespeed.IGameModel;
import typespeed.model.GameModel; 
import typespeed.view.GameObserver;
import typespeed.view.TypespeedGUI;

import java.util.ArrayList;
import java.util.List; 
import java.util.Timer; 
import java.util.TimerTask;  

import typespeed.view.IGameView;

public class GameController{
    private final IGameModel gameModel;
    private final IGameView gameView;


    private Timer gameTimer;

    public GameController(IGameModel gameModel, IGameView gameView){
        this.gameModel = gameModel; 
        this.gameView = gameView; 
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
                gameView.updateTimer(gameTime);
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
                gameView.updateWordPositions(gameModel.getWords());
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