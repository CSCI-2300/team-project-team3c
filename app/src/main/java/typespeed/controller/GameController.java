package typespeed.controller;

import typespeed.model.Word;
import typespeed.view.GameObserver;
import typespeed.view.TypespeedGUI;

import java.awt.*;
import java.util.*;
import java.io.*; 

public class GameController{
    
    private Timer wordTimer;
    private Timer gameTimer;
    private int gameTime = 60; 
    private int score = 0; 
    private int missedWords = 0; 
    private List<String> wordList; 
    private List<Word> words = new ArrayList<>(); 
    private TypespeedGUI view; 

    public GameController(TypespeedGUI view){
        this.view = view;
        loadWords("PlayEasy.txt");
        startGame(); 
    }

    private void loadWords(String filename){
        wordList = new ArrayList<>(); 
        try{
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNextLine()){
                wordList.add(scanner.nextLine.trim());
            }
            scanner.close(); 
        } 
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
                if(gameTime <= 0 || wordList.size()){
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