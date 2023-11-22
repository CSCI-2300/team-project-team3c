package typespeed.model;

public class Word{
    private String text; 
    private int positionX; 
    private int positionY; 
    private int speed; 

    public Word(String text){
        this.text = text;
        positionX = 0; 
        positionY = 0; 
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