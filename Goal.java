import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goal extends Actor
{
    
    public Goal(int x, int y) {
        setImage(new GreenfootImage(16,16));
        getImage().setColor(new Color(255,236,81));
        getImage().fill();
        setLocation(x,y);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
