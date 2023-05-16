import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Terrain extends Tile
{
    
    public Terrain(int size) {
        super(size, new GreenfootImage(size,size));
        getImage().setColor(new Color(0,0,0));
        getImage().fill();
    }
}
