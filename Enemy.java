import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public abstract class Enemy extends PhysicsEntity
{
    
    private final boolean IS_KILLABLE;
    
    private boolean alive = true;
    private int score;
    
    public Enemy(int x, int y, int score, boolean killable) {
        super(x,y);
        this.score = score;
        IS_KILLABLE = killable;
    }
    
    public abstract void destroy();
    
    public abstract State getState();
    
    public int getScore() {
        return score;
    }
    
    public boolean isKillable() {
        return IS_KILLABLE;
    }
    
    public void setDead() {
        alive = false;
    }
    
    public boolean isAlive() {
        return alive;
    }
}
