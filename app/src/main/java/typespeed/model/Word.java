package typespeed.model;

import java.util.Random;

public class Word{
    private String text; 
    private int positionX; 
    private int positionY; 
    private int speed; 
    private static final Random rand = new Random();
    private static final int MAX_X = 800;

    public Word(String text, int speed){
        this.text = text;
        this.speed = speed;
        positionX = rand.nextInt(200); 
        positionY = rand.nextInt(550) + 50; 
    }

    public void updatePosition(){
        positionX += speed; 
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