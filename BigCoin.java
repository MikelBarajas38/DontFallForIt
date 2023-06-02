import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BigCoin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BigCoin extends Collectable
{
    private static final int SCORE = 100;
    
    public BigCoin(int x, int y) {
        super(x, y, SCORE, false, "images/sprites/bigcoin/", "sounds/coin/bigcoin.wav");
    }
    
    public void destroy() {
        StateManager stateManager = getStateManager();
        if(stateManager.getCurrentState() != State.HIT) {
            stateManager.changeState(State.HIT);
            LevelWorld world = (LevelWorld) getWorld();
            world.setScore(world.getScore() + SCORE);  
            world.incrementBigCoinCounter();
        }
    }
}
