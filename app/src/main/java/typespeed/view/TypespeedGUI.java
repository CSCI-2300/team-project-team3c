package typespeed.view; 

import javax.swing.*; 
import java.awt.*; 

public class TypespeedGUI {
    JFrame mainFrame;
    JTextArea textArea; 

    public TypespeedGUI(){
        mainFrame = new JFrame("Typespeed Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setFont(new Font("Symbola", Font.PLAIN, 100));

        mainFrame.add(textArea);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public void displayWord(String word){
        textArea.append(word + "\n");
    }

}
