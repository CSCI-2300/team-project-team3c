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
    private JLabel scoreLabel, levelTypeLabel, missedLabel, timeLabel; 
    private JPanel bottomPanel; 
    private CustomDrawPanel drawPanel;
    private GameController controller; 
    private String difficulty; 

    private final Color leftColor = new Color(0, 255, 0); //Green
    private final Color middleColor = new Color(255, 255, 0); //Yellow
    private final Color rightColor = new Color(255, 0, 0); //Red

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

        public void setWords(List<Word> words){
            this.words = words; 
            repaint(); 
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            for(Word word : words){
                g.setColor(getColorOnPosition(word.getPosition()));
                g.drawString(word.getText(), word.getPosition().x, word.getPosition().y);
            }
        }
    }

    public TypespeedGUI(String difficulty){
        this.controller = new GameController(this, difficulty);
        controller.startGame();  
        mainFrame = new JFrame("Typespeed Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(800, 600));
        mainFrame.setLayout(new BorderLayout());

        drawPanel = new CustomDrawPanel();
        mainFrame.add(drawPanel);

        bottomPanel = new JPanel(); 
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        scoreLabel = new JLabel("Score: 0     ");
        levelTypeLabel = new JLabel("Level: " + difficulty +"     ");
        missedLabel = new JLabel("Words Missed: 0     ");
        timeLabel = new JLabel("Time: 0 sec     ");


        bottomPanel.add(scoreLabel);
        bottomPanel.add(levelTypeLabel);
        bottomPanel.add(missedLabel);
        bottomPanel.add(timeLabel);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public void displayWord(Word word){
        if(drawPanel != null){
            drawPanel.addWord(word);
        }
    }

    public Color getColorOnPosition(Point position){
        int sectionWidth = 800/3;

        if(position.x < sectionWidth){
            return leftColor;
        } else if (position.x >= sectionWidth && position.x < 2 * sectionWidth) {
            return middleColor;
        } else {
            return rightColor; 
        }
    }

    public void updateAndShowScore(int score){
        if (scoreLabel != null){
            scoreLabel.setText("Score: " + score); 
        }
    }

    public void updateMissedWords(int missedWords){
        missedLabel.setText("Words Missed: " + missedWords);
    }

    public void updateTimer(int time){
        if(timeLabel != null){
            timeLabel.setText("Time: " + time + " sec");
        }
    }

    private boolean isFrameVisible(){
        return mainFrame != null && mainFrame.isVisible(); 
    }

    public void closeWindow(){
        if(mainFrame != null){
            mainFrame.dispose(); 
        }
    }

    public Point getRandomPosition(){
        if (!isFrameVisible()){
            return new Point(0,0);
        }
        int screenWidth = mainFrame.getWidth();
        int screenHeight = mainFrame.getHeight(); 
        Random random = new Random(); 
        int x = random.nextInt(screenWidth);
        int y = random.nextInt(screenHeight - 50) + 50; 
        return new Point(x,y);
    }

    public void updateWordPositions(List<Word> words){
        drawPanel.setWords(words); 
        drawPanel.repaint();
    }
}