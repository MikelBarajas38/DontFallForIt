import java.util.*;
import greenfoot.*;  

public class LevelEndScreen extends World
{

    Scoreboard scoreBoard;
    
    public LevelEndScreen(int score, int timer, int bigCoinCounter, String scorePath, String playerName)
    {    
        super(800, 600, 1); 
        scoreBoard = new Scoreboard(scorePath);
        setUp();
        scoreBoard.addScore(playerName, score);
        scoreBoard.close();
    }
    
    private void setUp() {
        List<Map.Entry<String, Integer>> scoreList = scoreBoard.getScoreboard();
        for(Map.Entry<String, Integer> score : scoreList) {
            System.out.println(score.getKey() + " " + score.getValue());
        }
    }
}
