import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestSector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestSector extends Sector
{
    
    private Random random = new Random();
    
    public TestSector(int topLeftX, int topLeftY, int height, int width, int tileSize) {
        super(topLeftX, topLeftY, height, width, tileSize);
        
        this.setImage(new GreenfootImage(getWidth(), getHeight()));
        
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        getImage().setColor(new Color(r,g,b));
        getImage().fill();
    }
    
}
