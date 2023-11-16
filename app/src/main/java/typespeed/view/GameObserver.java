package typespeed.view;

import typespeed.model.Word; 
import java.awt.Point; 
import java.util.List;

public interface GameObserver{
    public void updateTimer(int time);
    public void updateAndShowScore(int score); 
    public void updateMissedWords(int missedWords);
    void displayWord(Word word); 
    Point getRandomPosition(); 
    void updateWordPositions(List<Word> words);
}