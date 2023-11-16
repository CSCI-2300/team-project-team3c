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
                if(gameTime <= 0 || missedWords == wordList.size()){
                    endGame();
                }
            }
        }, 0, 1000); 
    }

    private void generateWord(){
        if(!wordList.isEmpty()){
            int randomIndex = (int)(Math.random()*wordList.size());
            String wordText = wordList.get(randomIndex); //getting word from the random index in list
            Point position = view.getRandomPosition(); 
            Word word = new Word(wordText, position, wordSpeed); 
            words.add(word);
            view.displayWord(word); 
        }
    }

    private void endGame(){
        wordTimer.cancel(); 
        gameTimer.cancel(); 
        view.showScore(score); 
        //show highest score screen
    }

    public void checkWord(String userInput){
        for(int i = 0; i < words.size(); i++){
            Word word = words.get(i); 
            if(word.getText().equalsIgnoreCase(userInput)){
                words.remove(i); 
                score++;
                view.updateScore(score);
                return; 
            } else {
                word.updatePosition(); 
                if(word.getPosition().x >= 800){
                    words.remove(i); 
                    missedWords++; 
                    view.updateMissedWords(missedWords); 
                }
            }
        }
    }
}