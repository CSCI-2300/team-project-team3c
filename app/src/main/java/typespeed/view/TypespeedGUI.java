package typespeed.view; 

import javax.swing.*; 
import java.awt.*; 
import java.util.ArrayList; 
import java.util.List; 

public class TypespeedGUI {
    JFrame mainFrame;
    JTextArea textArea; 
    JLabel scoreLabel, levelTypeLabel, missedLabel, timelabel; 
    JPanel bottomPanel; 

    public class Word{
        String text; 
        Color color; 
        int position; 

        public Word(String text, Color color, int posiiton) {
            this.text = text;
            this.color = color;
            this.position = position; 
        }
    }

    public TypespeedGUI(){
        mainFrame = new JFrame("Typespeed Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(800, 600));
        mainFrame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setFont(new Font("Symbola", Font.PLAIN, 20));
        textArea.setEditable(false); 

        bottomPanel = new JPanel(); 
        bottomPanel.setLayout(new FlowLayout());
        
        scoreLabel = new JLabel("Score: 0");
        levelTypeLabel = new JLabel("Level: Difficult");
        missedLabel = new JLabel("Words Missed: 0");
        timelabel = new JLabel("Time: 60 sec");

        bottomPanel.add(scoreLabel);
        bottomPanel.add(levelTypeLabel);
        bottomPanel.add(missedLabel);
        bottomPanel.add(timelabel);

        mainFrame.add(textArea, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public void displayWord(Word word){
        textArea.append(word.text + " ");
        textArea.setForeground(word.color);
    }

}
