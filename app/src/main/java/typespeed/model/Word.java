package typespeed.model;

import java.util.Random;

public class Word{
    private String text; 
    private int positionX; 
    private int positionY; 
    private int speed; 
    Random rand = new Random();

    public Word(String text){
        this.text = text;
        positionX = rand.nextInt(200); 
        positionY = rand.nextInt(550); 
        speed = 1;
    }

    public void updatePosition(){
        positionX += speed; 
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