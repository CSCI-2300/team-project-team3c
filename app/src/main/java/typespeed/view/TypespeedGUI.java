package typespeed.view; 
import typespeed.model.Word; 
import typespeed.controller.GameController;

import javax.swing.*; 
import java.awt.*; 
import java.util.ArrayList; 
import java.util.List;
import java.util.Random; 

public class TypespeedGUI implements GameObserver{

    private JFrame mainFrame;
    private JTextArea textArea; 
    private JLabel scoreLabel, levelTypeLabel, missedLabel, timelabel; 
    private JPanel bottomPanel; 
    private CustomDrawPanel drawPanel;
    private GameController controller; 

    class CustomDrawPanel extends JPanel{
        List<Word> words = new ArrayList<>();

        public CustomDrawPanel(){
            setPreferredSize(new Dimension(800,600));
            setBackground(Color.BLACK);
        }

        public void addWord(Word word){
            words.add(word);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            for(Word word : words){
                g.setColor(word.color);
                g.drawString(word.text, word.position.x, word.position.y);
            }
        }
    }
    public class Word{
        String text; 
        Color color; 
        Point position;


        public Word(String text, Color color, Point posiiton) {
            this.text = text;
            this.color = color;
            this.position = posiiton;
        }
    }

    public TypespeedGUI(){
        controller = new GameController(this); 
        mainFrame = new JFrame("Typespeed Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(800, 600));
        mainFrame.setLayout(new BorderLayout());

        drawPanel = new CustomDrawPanel();
        mainFrame.add(drawPanel, BorderLayout.CENTER);

        bottomPanel = new JPanel(); 
        bottomPanel.setLayout(new FlowLayout());
        
        scoreLabel = new JLabel("Score: 4     ");
        levelTypeLabel = new JLabel("Level: Difficult     ");
        missedLabel = new JLabel("Words Missed: 3     ");
        timelabel = new JLabel("Time: 5 sec     ");


        bottomPanel.add(scoreLabel);
        bottomPanel.add(levelTypeLabel);
        bottomPanel.add(missedLabel);
        bottomPanel.add(timelabel);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public void displayWord(Word word){
        drawPanel.addWord(word);
    }

    public Color getColorBasedOnPosition(Point position){
        int sectionWidth = 800 / 3;

        if(position.x < sectionWidth){
            return new Color (0,255,0);
        }else if (position.x >= sectionWidth && position.x < 2 * sectionWidth){
            return new Color(255,255,0);
        }else{
            return new Color(255,0,0);
        }
    }

     public Point getRandomPosition(){
        int screenWidth = mainFrame.getWidth();
        int screenHeight = mainFrame.getHeight();
        Random random = new Random(); 
        int x  = random.nextInt(screenWidth);
        int y  = random.nextInt(screenHeight - 50) + 50;
        return new Point(x,y);
    }

    public void updateScore(int score){
        scoreLabel.setText("Score: " + score);
    }

    public void updateMissedWords(int missedWords){
        missedLabel.setText("Words Missed: " + missedWords); 
    }


}