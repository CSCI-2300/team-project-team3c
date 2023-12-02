package typespeed;

import java.util.List;

import typespeed.model.Word;

public interface IGameModel{
    void startGame();
    void endGame();
    void generateWord();
    void moveWords();
    void checkWord(String userInput);
    int getGameTime();
    void setGameTime(int gameTime);
    List<Word> getCurrentWords();
    int getScore();
	List<Word> getWords();
    int getMissedWordsCount(); 
}