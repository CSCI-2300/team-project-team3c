package typespeed.view;

public interface GameObserver{
    public void updateTimer(int time);
    public void updateScore(int score); 
    public void updateMissedWords(int missedWords);
    void showScore(int score);
}