package typespeed;

import typespeed.view.TypespeedGUI;
import typespeed.controller.GameController; 
import typespeed.view.LevelSelectionGUI; 

public class App {
    public static void main(String[] args) {
        TypespeedGUI view = new TypespeedGUI(); 
        GameController controller = new GameController(view); 
        LevelSelectionGUI view = new LevelSelectionGUI(); 
    }
}