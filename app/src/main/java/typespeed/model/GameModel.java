package typespeed.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.HashSet;
import java.util.Set;

import typespeed.IGameModel;

public class GameModel implements IGameModel {
    private int gameTime;
    private int score;
    private List<Word> words = new ArrayList<>();
    private List<String> wordList;
    private int missedWordsCount; 
    private Set<String> activeWords = new HashSet<>();


    public GameModel(List<String> wordList) {
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
    }

    @Override
    public void generateWord() {
        if (!wordList.isEmpty()) {
            String wordText;
            do {
                int randomIndex = (int) (Math.random() * wordList.size());
                wordText = wordList.get(randomIndex);
            } while (activeWords.contains(wordText));

            Word newWord = new Word(wordText);
            if (!isOverlapping(newWord)) {
                words.add(newWord);
                activeWords.add(wordText);
            }
        }
    }

    private boolean isOverlapping(Word newWord) {
        for (Word word : words) {
            if (Math.abs(word.getPositionY() - newWord.getPositionY()) < 60) {
                return true; // Found an overlapping word
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
                iterator.remove(); // remove words that reach the end
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

