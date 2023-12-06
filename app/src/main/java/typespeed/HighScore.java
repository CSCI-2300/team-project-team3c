package typespeed;

import java.io.*;

public class HighScore {
    private static final String HIGH_SCORE_FILE = "highscore.dat";
    private int highScore;

    public HighScore() {
        deserializeHighScore();
    }

    public void checkAndUpdateHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            serializeHighScore();
        }
    }

    public int getHighScore() {
        return highScore;
    }

    private void serializeHighScore() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HIGH_SCORE_FILE))) {
            oos.writeInt(highScore);
        } catch (IOException e) {
            System.err.println("Error saving high score: " + e.getMessage());
        }
    }

    private void deserializeHighScore() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HIGH_SCORE_FILE))) {
            highScore = ois.readInt();
        } catch (IOException e) {
            System.out.println("No existing high score found. Starting fresh.");
            highScore = 0;
        }
    }
}
