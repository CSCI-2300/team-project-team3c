package typespeed;

import typespeed.view.TypespeedGUI;
import typespeed.controller.GameController; 
import typespeed.view.LevelSelectionGUI; 

public class App {
    public static void main(String[] args) {
        LevelSelectionGUI levelSelectionGUI = new LevelSelectionGUI(); 
        TypespeedGUI gui = new TypespeedGUI("PlayEasy"); 
        //GameController controller = new GameController();
    }
}