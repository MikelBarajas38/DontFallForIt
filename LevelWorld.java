import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LevelWorld extends World
{
    
    public static final int WIDTH = 736;
    public static final int HEIGHT = 736;
    
    Level currentLevel;
        
    public LevelWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1); 
        currentLevel = new Level(this, "levels/testLevel");
    }
    
    public void nextLevel() {
        currentLevel.buildNextRoom();
    }
}
