import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Tile extends Actor  
{
    private int id;
    private int height;
    private int width;

    public Tile(int size, GreenfootImage sprite) {
        this.height = size;
        this.width = size;
        setImage(sprite);
    }
    
    public Tile(int height, int width)
    {
        this.height = height;
        this.width = width;
    }
    
    public int getSize() {
        return height;
    }
    
}
