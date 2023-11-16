package typespeed.view;

public interface GameObserver{
    public void updateTimer(int time);
    public void updateAndShowScore(int score); 
    public void updateMissedWords(int missedWords);
}