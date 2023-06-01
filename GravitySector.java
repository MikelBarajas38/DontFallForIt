import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.EnumMap;

public class GravitySector extends Sector
{
    
    private Direction direction;
    
    private static EnumMap <Direction, Color> directionColor = null;

    
    public GravitySector(Direction direction, int topLeftX, int topLeftY, int height, int width, int tileSize) {
        super(topLeftX, topLeftY, height, width, tileSize);
        this.direction = direction;
        
        if(directionColor == null){
            directionColor = new EnumMap<>(Direction.class);
            directionColor.put(Direction.UP, new Color(119,118,188));
            directionColor.put(Direction.DOWN, new Color(205,199,229));   
            directionColor.put(Direction.LEFT, new Color(255,236,81));   
            directionColor.put(Direction.RIGHT, new Color(255,103,77));  
        } 
        
        this.setImage(new GreenfootImage(getWidth(), getHeight()));
        
        getImage().setColor(directionColor.get(direction));
        getImage().fill();
    }
    
    public void act() {
        // Add your action code here.
    }
    
    public Direction getDirection() {
        return direction;
    }
}
