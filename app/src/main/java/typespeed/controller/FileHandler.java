package typespeed.controller;

import java.io.File;
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.List; 
import java.util.Scanner; 

public class FileHandler {
    public List<String> loadWords(String filename){
        List<String> wordList = new ArrayList<>(); 
        try{
            File file = new File("src/main/java/typespeed/" + filename);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                wordList.add(scanner.nextLine().trim());
            }
            scanner.close(); 
        } catch (FileNotFoundException e){
            e.printStackTrace(); 
        }
        return wordList; 
    }
}
