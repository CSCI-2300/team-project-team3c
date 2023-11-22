package typespeed;

import typespeed.view.LevelSelectionGUI; 
import typespeed.model.GameModel; 
import typespeed.view.TypespeedGUI;
import java.util.List;  

public class App {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        String difficulty = "PlayEasy.txt";
        List<String> wordList = fileHandler.loadWords(difficulty);
        
        GameModel model = new GameModel(wordList);
        LevelSelectionGUI levelSelectionGUI = new LevelSelectionGUI(model);
    }
}