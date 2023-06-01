import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LevelWorld extends World
{
    
    public static final int WIDTH = 736;
    public static final int HEIGHT = 736;
    
    private int previousScore = 0;
    private int score = 0;
    
    private static final int FPS = 120;
    private int timer = 1200;
    private int currentFrame = 0;
    
    Level currentLevel;
        
    public LevelWorld(String levelPath)
    {    
        super(WIDTH, HEIGHT, 1); 
        setBackground(new GreenfootImage("images/tiles/backround/bg.png"));
        currentLevel = new Level(this, levelPath);
        setActOrder(Sector.class);
    }
    
    public void act() {
        handleTimer();
        handleScore();
    }
    
    public void handleTimer() {
        if(currentFrame == FPS) {
            timer--;
            currentFrame = 0;
        }
        showText("TIME: " + timer, 192, 120);
        currentFrame++;
    }
    
    public void handleScore() {
        showText("" + score, 600, 120);
        currentFrame++;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    public void nextRoom() {
        previousScore = score;
        currentLevel.buildNextRoom();
    }
    
    public void reloadRoom() {
        score = previousScore;
        currentLevel.rebuildRoom();
    }
}
