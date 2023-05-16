//test
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    
    public static final int WIDTH = 736;
    public static final int HEIGHT = 736;
    
    Level currentLevel;
        
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1); 
        currentLevel = new Level(this, "levels/testLevel");
    }
    
    public void nextLevel() {
        currentLevel.buildNextRoom();
    }
}
