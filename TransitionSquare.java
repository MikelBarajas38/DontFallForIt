import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TransitionSquare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TransitionSquare extends Actor
{   
    
    private Transition transition;
    private int scaleFactor = 30;
    private int deltaScale = 20;
    
    TransitionSquare(Transition transition) {
        this.transition = transition;
        setImage(new GreenfootImage("images/effects/transition.png"));
    }
    
    public void act()
    {
        scaleFactor += deltaScale;
        getImage().scale(scaleFactor, scaleFactor); 
        
        if(scaleFactor > 1000){
            deltaScale *= -1;
            getImage().scale(800, 800); 
            scaleFactor = 800;
            transition.activate();
        } 
        
        if(getImage().getWidth() <= 30) {
            getWorld().removeObject(this);
        }
    }
}
