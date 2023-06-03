import java.util.*;
import greenfoot.*;  

public class LevelEndScreen extends World
{
    private static final int TOTAL_POINTS_TIMER = 200;
    private static final int DECREASE_POINTS_TIMER = 2;
    private static final int TIME_LIMIT_TOTAL_POINTS = 480;
    private static final int POINTS_PER_BIG_COIN = 50;
    
    private Scoreboard scoreBoard;
    private String playerName;
    private int score;
    private int nextScoreYPosition;
    
    public LevelEndScreen(int score, int timer, int bigCoinCounter, String scorePath, String playerName)
    {    
        super(800, 600, 1); 
        prepare();
        scoreBoard = new Scoreboard(scorePath);
        this.playerName = playerName;
        this.score = getFinalScore(score,timer,bigCoinCounter);
        scoreBoard.addScore(playerName, this.score);
        scoreBoard.close();
        nextScoreYPosition = 310;
        setUpScoreboard();
    }
    
    private void prepare(){
        setBackground("images/scoreboard.png");
        
        Button returnButton = new ReturnButton ("images/buttons/atras.png",new MainMenu());
        addObject(returnButton, getWidth() / 2 - 270, 510);
        
        Button quitGame = new QuitGameButton("images/buttons/salir.png");
        addObject(quitGame, 626, 510);
    }
    
    private void setUpScoreboard() {
        getBackground().drawImage(new GreenfootImage(""+playerName+" with "+Integer.toString(score), 24,Color.BLACK, null), 400, 160);
        
        List<Map.Entry<String, Integer>> scoreList = scoreBoard.getScoreboard();
        for(Map.Entry<String, Integer> score : scoreList) {
            getBackground().drawImage(new GreenfootImage(score.getKey() + " with " + score.getValue(), 24,Color.BLACK, null), 400, nextScoreYPosition);
            System.out.println(score.getKey() + " " + score.getValue());
            nextScoreYPosition += 25;
        }
    }
    
    private int getFinalScore(int score, int timer, int bigCoinCounter){
        int finalScore = score;
        int timerScore = TOTAL_POINTS_TIMER;
        int coinsScore = bigCoinCounter * POINTS_PER_BIG_COIN;
        
        if(timer > TIME_LIMIT_TOTAL_POINTS){
            timerScore -= DECREASE_POINTS_TIMER * (timer - TIME_LIMIT_TOTAL_POINTS);
            if(timerScore < 0) timerScore = 0;
        }
        
        finalScore += timerScore + coinsScore;
        
        return finalScore;
    }
}
