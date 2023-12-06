package typespeed.model;

import java.util.Random;

public class Word{
    private String text; 
    private int positionX; 
    private int positionY; 
    private static int SPEED = 5; 
    private static final Random rand = new Random();
    private static final int MAX_X = 800;

    private static final int MIN_Y = 250;
    private static final int MAX_Y = 500;
    public Word(String text){
        this.text = text;
        positionX = rand.nextInt(200); 
        positionY = rand.nextInt(MAX_Y - MIN_Y + 1) + MIN_Y;
    }

    public void updatePosition(){
        positionX += SPEED; 
        if (positionX > MAX_X){
            positionX = MAX_X;
        }
    }

    public boolean isOffScreen(){
        return positionX >= MAX_X;
    }

    public String getText(){
        return text;
    }

    public int getPositionX(){
        return positionX; 
    }

    public int getPositionY(){
        return positionY; 
    }

}