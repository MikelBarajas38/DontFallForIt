import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LevelWorld extends World
{
    
    public static final int WIDTH = 736;
    public static final int HEIGHT = 736;
    
    private int previousScore = 0;
    private int score = 0;
    private int bigCoinCounter = 0;
    String scorePath;
    
    private static final int FPS = 120;
    private int timer = 1200;
    private int currentFrame = 0;
    
    Level currentLevel;
    
    private boolean isInTransition = false;
    private static final GreenfootSound transitionEffect = new GreenfootSound("sounds/transition/transition.wav");
    private static final GreenfootSound transitionEffect2 = new GreenfootSound("sounds/transition/transition2.wav");
    private static final GreenfootSound transitionEffectEnd = new GreenfootSound("sounds/transition/levelclear.wav");

    
    HUD hud = new HUD(this);
        
    public LevelWorld(String levelPath, String scorePath, String playerPath, String playerName)
    {    
        super(WIDTH, HEIGHT, 1); 
        setBackground(new GreenfootImage("images/tiles/backround/bg.png"));
        currentLevel = new Level(this, levelPath, playerPath, playerName);
        this.scorePath = scorePath;
        setActOrder(Sector.class);
        setPaintOrder(TransitionSquare.class);
        Transition transition = new Transition(this, false);
        transition.setUp();
    }
    
    public void act() {
        handleTimer();
        handleScore();
        hud.update();
    }
    
    public void handleTimer() {
        if(currentFrame == FPS) {
            timer--;
            currentFrame = 0;
        }
        currentFrame++;
    }
    
    public void handleScore() {
        currentFrame++;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    public void incrementBigCoinCounter() {
        bigCoinCounter++;
    }
    
    public int getBigCoinCounter() {
        return bigCoinCounter;
    }
    
    public int getTimer() {
        return timer;
    }
    
    public void transitionNextRoom() {
        if(!isInTransition) {
            Transition transition = new Transition(this, true);
            transition.setUp();
            isInTransition = true;
        }
    }
    
    public void transitionThisRoom() {
        if(!isInTransition) {
            Transition transition = new Transition(this, false);
            transition.setUp();
            isInTransition = true;
        }
    }
    
    public void nextRoom() {
        if(currentLevel.isLastRoom()){
            transitionEffectEnd.play();
            Greenfoot.setWorld(new LevelEndScreen(score, timer, bigCoinCounter, scorePath));
        } else {
            transitionEffect.play();
            previousScore = score;
            currentLevel.buildNextRoom();
            isInTransition = false;
        }
    }
    
    public void reloadRoom() {
        transitionEffect2.play();
        score = previousScore;
        currentLevel.rebuildRoom();
        isInTransition = false;
    }
    
}
