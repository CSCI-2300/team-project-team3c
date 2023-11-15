package typespeed.controller;

import typespeed.model.Word;
import typespeed.view.GameObserver;

public class GameController implements GameObserver{
    
    private WordtoType word;
    private GameObserver view;


    public GameController(WordtoType word, GameObserver view){
        this.view = view;
        this.word = word;
    }

    public boolean 

    public boolean isGameOver(){

    }
}