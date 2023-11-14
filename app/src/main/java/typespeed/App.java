package typespeed;
import typespeed.view.TypespeedGUI;
import java.awt.Color; 
import java.awt.*;

public class App {
    public static void main(String[] args) {
        TypespeedGUI game = new TypespeedGUI(); 

        Point position1 = game.getRandomPosition();
        Color color1 = game.getColorBasedOnPosition(position1);

        // Hardcoded words to display for the prototype with colors and random positions
        TypespeedGUI.Word word1 = game.new Word("prototype", color1, position1);  
        game.displayWord(word1);

        Point position2 = game.getRandomPosition();
        Color color2 = game.getColorBasedOnPosition(position2);

        TypespeedGUI.Word word2 = game.new Word("game", color2, position2);  
        game.displayWord(word2);

        Point position3 = game.getRandomPosition();
        Color color3 = game.getColorBasedOnPosition(position3);

        TypespeedGUI.Word word3 = game.new Word("design", color3, position3);  
        game.displayWord(word3);
    }
}