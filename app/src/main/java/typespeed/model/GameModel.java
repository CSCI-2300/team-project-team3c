package typespeed.model; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Random; 
import java.util.concurrent.CopyOnWriteArrayList; 

public class GameModel {
    private int gameTime; 
    private int score; 
    private List<Word> words = new CopyOnWriteArrayList<>(); 
    private List<String> wordList; 

    public GameModel(List<String> wordList){
        this.wordList = wordList; 
    }

    public int getPositionX(Word word){
        return word.getPositionX(); 
    }

    public int getPositionY(Word word){
        return word.getPositionY(); 
    }

    public void startGame(){
        gameTime = 60; 
        score = 0; 
        words.clear(); //clear previous games words 
    }

    public void endGame(){
        gameTime = 0; 
        score = 0;
        words.clear(); 
    }

    public void generateWord(){
        if(!wordList.isEmpty()){
            int randomIndex = (int)(Math.random() * wordList.size());
            String wordText = wordList.get(randomIndex); //getting word from the random index in list
            words.add(new Word(wordText));
        }
    }

    public void moveWords(){
        List<Word> wordsToRemove = new ArrayList<>();
        for (Word word : words) {
            word.updatePosition();
            if (word.getPositionX() >= 800) {
                wordsToRemove.add(word);
                ++score;
            }
        }
    
        // Remove the words after iterating
        words.removeAll(wordsToRemove);
    }

    public void checkWord(String userInput){
        for (int i = words.size() - 1; i >= 0; i--) {
            Word word = words.get(i);
            if (word.getText().equalsIgnoreCase(userInput)) {
                words.remove(i);
                score++;
                return;
            }
        }
    }

    public int getScore(){
        return score; 
    }

    public List<Word> getWords(){
        return words;
    }

    public int getGameTime(){
        return gameTime;
    }

    public void setGameTime(int gameTime){
        this.gameTime = gameTime; 
    }

    public List<Word> getCurrentWords() {
        return this.words;
    }
    

}
