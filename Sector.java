import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Sector extends Actor
{
    
    private int topLeftX;
    private int topLeftY;
    private int downRightX;
    private int downRightY;
    
    public Sector(int topLeftX, int topLeftY, int height, int width, int tileSize) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        downRightX = topLeftX + width * tileSize;
        downRightY = topLeftY + height * tileSize;
    }
    
    public int getDrawX() {
        return topLeftX + getWidth() / 2;
    }
    
    public int getDrawY() {
        return topLeftY + getHeight() / 2;
    }
    
    public int getHeight() {
        return downRightY - topLeftY;
    }
    
    public int getWidth() {
        return downRightX - topLeftX;
    }
    
}
