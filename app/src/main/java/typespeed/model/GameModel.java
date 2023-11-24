package typespeed.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import typespeed.IGameModel;

public class GameModel implements IGameModel {
    private int gameTime;
    private int score;
    private List<Word> words = new ArrayList<>();
    private List<String> wordList;

    public GameModel(List<String> wordList) {
        this.wordList = wordList;
    }

    @Override
    public void startGame() {
        gameTime = 60;
        score = 0;
        words.clear();
    }

    @Override
    public void endGame() {
        gameTime = 0;
        words.clear();
    }

    @Override
    public void generateWord() {
        if (!wordList.isEmpty()) {
            int randomIndex = (int) (Math.random() * wordList.size());
            String wordText = wordList.get(randomIndex);
            words.add(new Word(wordText, randomIndex));
        }
    }

    @Override
    public void moveWords() {
        Iterator<Word> iterator = words.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            word.updatePosition();
            if (word.getPositionX() >= 800) {
                iterator.remove(); // remove words that reach the end
            }
        }
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

