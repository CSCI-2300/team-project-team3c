package typespeed.controller;

import typespeed.model.Word;
import typespeed.view.GameObserver;
import typespeed.view.TypespeedGUI;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.List; 
import java.util.Scanner; 
import java.util.Timer; 
import java.util.TimerTask;  
import java.util.ArrayList;

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
            File file = new File("src/main/java/typespeed/" + filename);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                wordList.add(scanner.nextLine().trim());
            }
            scanner.close(); 
        } catch (FileNotFoundException e){
            e.printStackTrace(); 
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
            int randomIndex = (int)(Math.random() * wordList.size());
            String wordText = wordList.get(randomIndex); //getting word from the random index in list
            Point position = view.getRandomPosition(); 
            int wordSpeed = 1; 
            Word word = new Word(wordText, position, wordSpeed); 
            words.add(word);
            view.displayWord(word); 
        }
    }

    private void endGame(){
        wordTimer.cancel(); 
        gameTimer.cancel(); 
        view.updateAndShowScore(score); 
        //show highest score screen
    }

    public void checkWord(String userInput){
        for(int i = 0; i < words.size(); i++){
            Word word = words.get(i); 
            if(word.getText().equalsIgnoreCase(userInput)){
                words.remove(i); 
                score++;
                view.updateAndShowScore(score);
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