import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    Platform(){
        this(200,40);
    }
    
    Platform(int x,int y){
        setImage("images/platform.jpg");
        GreenfootImage image = getImage();
        image.scale(x, y);
        setImage(image);
    }
    
    public void act()
    {
        
    }
}
