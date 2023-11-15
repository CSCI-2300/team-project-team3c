package typespeed.model;


import typespeed.view.GameObserver;
import java.util.ArrayList;
import java.util.List;



public class Word{
    private char []word;

    private int score;

    private List <GameObserver> Observers = new ArrayList<>();

    public void addObserver(GameObserver observer){
        Observers.add(observer);
    }

    public void notifyObservers(){
        for (GameObserver observer : Observers){
            if(observer != null){
                observer.update();
            }
        }
    }

    public word (String word);
        this.word = word.toCharArray();
}

    public void InitializeGameState(){
        this.guesses = 0;
    }