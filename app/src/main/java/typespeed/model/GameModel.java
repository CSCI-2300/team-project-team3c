package typespeed.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import java.util.HashSet;
import java.util.Set;

import typespeed.HighScore;
import typespeed.IGameModel;

public class GameModel implements IGameModel {
    private Difficulty difficulty;
    private int gameTime;
    private int score;
    private List<Word> words = new ArrayList<>();
    private List<String> wordList;
    private int missedWordsCount; 
    private Set<String> activeWords = new HashSet<>();


    public GameModel(List<String> wordList, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.wordList = wordList;
    }

    @Override
    public void startGame() {
        gameTime = 60;
        score = 0;
        words.clear();
        missedWordsCount = 0; 
    }

    @Override
    public void endGame() {
        gameTime = 0;
        words.clear();
        HighScore highScore = new HighScore();
        highScore.checkAndUpdateHighScore(this.score);
    }

    @Override
    public boolean isGameOver(){
        return gameTime <= 0;
    }


    @Override
    public void generateWord() {
        if (isGameOver()){
            return;
        }
        if (!wordList.isEmpty()) {
            String wordText;
            do {
                int randomIndex = (int) (Math.random() * wordList.size());
                wordText = wordList.get(randomIndex);
            } while (activeWords.contains(wordText));
    
            // Modify word generation based on difficulty
            Word newWord = new Word(wordText);
            adjustWordBasedOnDifficulty(newWord);
    
            if (!isOverlapping(newWord)) {
                words.add(newWord);
                activeWords.add(wordText);
            }
        } else {
            endGame(); 
        }
    }

    public void adjustWordBasedOnDifficulty(Word word) {
        switch (difficulty) {
            case EASY:
                // Adjust word properties for easy difficulty, e.g., slower movement
                word.setSpeed(10);
                break;
            case MEDIUM:
                // Adjust for medium difficulty
                word.setSpeed(10);
                break;
            case HARD:
                // Adjust for hard difficulty
                word.setSpeed(15);
                break;
            default:
                // Default behavior (if needed)
                break;
        }
    }

    public boolean isOverlapping(Word newWord) {
        for (Word word : words) {
            int verticalDistance = Math.abs(word.getPositionY() - newWord.getPositionY());
            if (verticalDistance < 60) {
                return true; 
            }
        }
        return false;
    }

    public void removeActiveWord(String wordText) {
        activeWords.remove(wordText);
    }

    @Override
    public void moveWords() {
        Iterator<Word> iterator = words.iterator();
        List<Word> wordsToRemove = new ArrayList<>();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            word.updatePosition();
            if (word.getPositionX() >= 800) {
                iterator.remove(); 
                missedWordsCount++; 
            }
        }
    
        words.removeAll(wordsToRemove);
    }

    @Override
    public void checkWord(String userInput) {
        Iterator<Word> iterator = words.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (word.getText().equalsIgnoreCase(userInput)) {
                iterator.remove();
                score++;
                return;
            }
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getMissedWordsCount() {
        return missedWordsCount;
    }

    @Override
    public List<Word> getWords() {
        return new ArrayList<>(words);
    }

    @Override
    public int getGameTime() {
        return gameTime;
    }

    @Override
    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    @Override
    public List<Word> getCurrentWords() {
        return words;
    }
}

