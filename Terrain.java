import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Terrain extends Tile
{
    
    public Terrain(TerrainType type, TilePosition position, int size) {
        super(size, new GreenfootImage("images/tiles/terrain/" + type.getPath() + "/" + position.getPath()));
    }
    
    public Terrain(int size) {
        super(size, new GreenfootImage(size,size));
        getImage().setColor(new Color(0,0,0));
        getImage().fill();
    }
    
}
