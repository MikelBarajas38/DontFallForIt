import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class TestTile extends Tile
{
    
    private Random random = new Random();
    
    public TestTile(int size) {
        super(size,  new GreenfootImage(size,size));
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        getImage().setColor(new Color(r,g,b));
        getImage().fill();
        //getImage().setColor(new Color(0,255,255));
        //getImage().fillRect(2,2,size-4,size-4);
    }
    
}
