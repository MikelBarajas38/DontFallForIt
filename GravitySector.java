import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.EnumMap;

public class GravitySector extends Sector
{
    
    private Direction direction;
    
    private static EnumMap <Direction, GreenfootImage> directionColor = null;
    
    private GreenfootImage background;
    
    private int scrollY = 0;
    private int scrollSpeed = 1;
    private int scrollDirection;

    
    public GravitySector(Direction direction, int topLeftX, int topLeftY, int height, int width, int tileSize) {
        super(topLeftX, topLeftY, height, width, tileSize);
        this.direction = direction;
        
        if(directionColor == null){
            directionColor = new EnumMap<>(Direction.class);
            directionColor.put(Direction.UP, new GreenfootImage("images/sector/tiled3.jpg")); 
            directionColor.put(Direction.DOWN, new GreenfootImage("images/sector/tiled4.jpg"));
        } 
        
        if(direction == Direction.DOWN){
            scrollDirection = 1;
        } else {
            scrollDirection = -1;            
        }
        
        background = new GreenfootImage(directionColor.get(direction));
        this.setImage(new GreenfootImage(getWidth(), getHeight()));
    }
    
    public void act() {
        
        scrollY = (scrollY + scrollDirection * scrollSpeed + getHeight())%getHeight();
        getImage().drawImage(background, 0, scrollY);
        getImage().drawImage(background, 0, (scrollY - getHeight()));
        
    }
    
    public Direction getDirection() {
        return direction;
    }
}
