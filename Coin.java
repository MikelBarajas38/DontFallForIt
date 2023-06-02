import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.EnumMap;

public class Coin extends Collectable
{
    private static final int SCORE = 5;
    
    public Coin(int x, int y, boolean isAffectedByGravity) {
        super(x, y, SCORE, isAffectedByGravity, "images/sprites/coin/", "sounds/coin/coin.wav");
    }
    
    public void destroy() {
        StateManager stateManager = getStateManager();
        if(stateManager.getCurrentState() != State.HIT) {
            stateManager.changeState(State.HIT);
            LevelWorld world = (LevelWorld) getWorld();
            world.setScore(world.getScore() + SCORE);    
        }
    }
}
