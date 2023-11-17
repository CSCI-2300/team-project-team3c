package typespeed;

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
            if (file.exists()){
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    wordList.add(scanner.nextLine().trim());
                }
                scanner.close(); 
            } else {
                System.out.println("File not found!");
            }
        } catch (FileNotFoundException e){
            e.printStackTrace(); 
        }
        return wordList; 
    }
}
