package typespeed.view;

import typespeed.model.Word;
import typespeed.IGameModel;
import typespeed.controller.GameController;
import typespeed.view.IGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.List;
import java.util.ArrayList; 
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class TypespeedGUI extends JFrame implements IGameView{

    private JFrame mainFrame;
    private GameController controller; 
    private CustomDrawPanel drawPanel;
    private IGameModel gameModel;  

    private JTextArea inputArea;
    private JLabel scoreLabel, levelTypeLabel, missedLabel, timeLabel; 
    private JPanel bottomPanel; 
    
    private final Color leftColor = new Color(0, 255, 0); //Green
    private final Color middleColor = new Color(255, 255, 0); //Yellow
    private final Color rightColor = new Color(255, 0, 0); //Red


    class CustomDrawPanel extends JPanel{
        private List<Word> words = new CopyOnWriteArrayList<>();


        public CustomDrawPanel(){
            setPreferredSize(new Dimension(800,600));
            setBackground(Color.BLACK);
        }

        public void setWords(List<Word> words){
            this.words = words; 
            repaint(); 
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            for(Word word : this.words){
                g.setColor(getColorOnPosition(word));
                g.drawString(word.getText(), word.getPositionX(), word.getPositionY());
            }
        }
    }

    public TypespeedGUI(IGameModel gameModel, String difficulty){
        this.gameModel = gameModel; 
        this.controller = new GameController(gameModel, this);

        initializeComponents(difficulty);
        controller.startGame();
    }


    private void initializeComponents(String difficulty){
        inputArea = new JTextArea();
        inputArea.requestFocusInWindow();
        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    String typedWord = inputArea.getText().trim();
                    controller.checkWord(typedWord);
                    inputArea.setText("");
                }
            }
        });

        mainFrame = new JFrame("Typespeed Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(800, 600));

        drawPanel = new CustomDrawPanel();
        drawPanel.setBackground(Color.BLACK);
        mainFrame.add(drawPanel, BorderLayout.CENTER);

        bottomPanel = new JPanel(); 
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        
        levelTypeLabel = new JLabel("Level: " + difficulty);
        missedLabel = new JLabel("Words Missed: 0");
        scoreLabel = new JLabel("Score: 0");
        timeLabel = new JLabel("Time: 0 sec");

        bottomPanel.add(levelTypeLabel);
        bottomPanel.add(missedLabel);
        bottomPanel.add(scoreLabel);
        bottomPanel.add(timeLabel);

        inputArea.setEnabled(true);
        inputArea.setEditable(true);

        mainFrame.add(inputArea, BorderLayout.NORTH);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

        @Override
        public void updateWordPositions(List<Word> words){
            drawPanel.setWords(words);
        }

    public Color getColorOnPosition(Word word){
        int positionX = word.getPositionX(); 
        int sectionWidth = 800/3;

        if(positionX < sectionWidth){
            return leftColor;
        } else if (positionX >= sectionWidth && positionX < 2 * sectionWidth) {
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
        if(missedLabel != null){
            missedLabel.setText("Words Missed: " + missedWords);
        }
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

    public void displayGameOver() {
        EndGamePanel endGamePanel = new EndGamePanel(gameModel.getScore(), e -> restartGame());
        mainFrame.setContentPane(endGamePanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    

    private void restartGame() {
        System.out.println("restartGame Method called");
        controller.restartGame();
        resetView();
    }

    private void resetView() {
        scoreLabel.setText("Score: 0");
        timeLabel.setText("Time: 60 sec"); 
        missedLabel.setText("Words Missed: 0");

        inputArea.setText("");
        drawPanel.setWords(new ArrayList<>());
        drawPanel.repaint();
       // inputArea.requestFocusInWindow();

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


public void refreshDisplay(){
    List<Word> words = controller.getCurrentWords(); 
    drawPanel.setWords(words);
    drawPanel.repaint();
}
}


