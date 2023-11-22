package typespeed.model; 

import java.util.ArrayList; 
import java.util.Iterator; 
import java.util.List; 

public class GameModel {
    private int gameTime; 
    private int score; 
    private List<Word> words = new ArrayList<>(); 
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

    public void generateWord(){
        if(!wordList.isEmpty()){
            int randomIndex = (int)(Math.random() * wordList.size());
            String wordText = wordList.get(randomIndex); //getting word from the random index in list
            words.add(new Word(wordText));
        }
    }

    public void moveWords(){
        Iterator<Word> iterator = words.iterator();
        while(iterator.hasNext()){
            Word word = iterator.next(); 
            word.updatePosition(); 
            if(word.getPositionX() > 800){
                iterator.remove(); //remove words that reach end
            }
        }
    }

    public void checkWord(String userInput){
        Iterator<Word> iterator = words.iterator();
        while(iterator.hasNext()){
            Word word = iterator.next(); 
            if(word.getText().equalsIgnoreCase(userInput)){
                iterator.remove(); 
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

}
