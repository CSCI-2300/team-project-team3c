package typespeed;

import java.io.File;
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.List; 
import java.util.Scanner; 
import javax.swing.JOptionPane; 

public class FileHandler {
    public List<String> loadWords(String filename){
        List<String> wordList = new ArrayList<>(); 
        try{
            File file = new File(filename);
            if (file.exists()){
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    wordList.add(scanner.nextLine().trim());
                }
                scanner.close(); 
            } else {
                JOptionPane.showMessageDialog(null, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace(); 
        }
        return wordList;
    }

    public boolean doesFileExist(String filename){
        File file = new File(filename);
        return file.exists(); 
    }
}
