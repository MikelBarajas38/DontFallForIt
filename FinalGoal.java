import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FinalGoal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FinalGoal extends Goal
{
    FinalGoal(int x, int y, Direction direction) {
        super(x, y, direction, "images/sprites/goal/end/");
    }
    
    public void activate() {
        LevelWorld world = (LevelWorld) getWorld();
        world.transitionNextRoom();
    }
}
