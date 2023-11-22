package typespeed.view;

import typespeed.model.Word; 
import java.util.List;
import java.awt.Point; 

public interface GameObserver{
    public void updateTimer(int time);
    public void updateAndShowScore(int score); 
    public void updateMissedWords(int missedWords);
    void displayWord(Word word); 
    Point getRandomPosition(); 
    void updateWordPositions(List<Word> words);
}