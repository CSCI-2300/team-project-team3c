package typespeed.model;

public enum Difficulty {
    EASY, MEDIUM, HARD;

    public static Difficulty fromString(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "playeasy.txt":
                return EASY;
            case "playhard.txt":
                return HARD;
            default:
                return MEDIUM; // Default or throw an exception
        }
    }
}