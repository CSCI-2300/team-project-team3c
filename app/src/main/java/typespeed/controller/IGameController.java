package typespeed.controller;
import typespeed.model.Word;

import java.util.*;

public interface IGameController{
    void startGame();
    void startGameTimer();
    void cancelGameTimer();
    boolean isGameRunning();
    void startWordGeneration();
    void startWordMovement();
    List<Word> getCurrentWords();
    void checkWord(String userInput);
}