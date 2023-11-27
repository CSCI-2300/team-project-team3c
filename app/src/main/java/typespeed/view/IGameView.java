package typespeed.view;

import typespeed.model.Word;
import java.util.List;


public interface IGameView{
    void updateWordPositions(List<Word> words);
    void updateAndShowScore(int score);
    void updateTimer(int time);
}