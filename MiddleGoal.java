import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiddleGoal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiddleGoal extends Goal
{
    MiddleGoal(int x, int y, Direction direction) {
        super(x, y, direction, "images/sprites/goal/middle/");
    }
    
    public void activate() {
        LevelWorld world = (LevelWorld) getWorld();
        world.transitionNextRoom();
    }
}
