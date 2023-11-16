package typespeed.model;

import typespeed.view.GameObserver;
import java.awt.Point; 



public class Word{
    private String text; 
    private Point position; 
    private int speed; 

    public Word(String text, Point position, int speed){
        this.text = text;
        this.position = position; 
        this.speed = speed; 
    }

    public void updatePosition(){
        position.x += speed; 
    }
}