import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Terrain extends Tile
{
    
    public Terrain(TileType type, List<List<String>> tileMap, int x, int y, int size) {
        super(size, type, tileMap, x, y, "images/tiles/terrain/");
    }
    
    public TilePosition getTilePosition(List<List<String>> tileMap, int x, int y) {
        boolean isTopValid = isValidTilePosition(tileMap, x, y-1) && isSameTileType(tileMap, x, y, x, y-1); 
        boolean isDownValid = isValidTilePosition(tileMap, x, y+1) && isSameTileType(tileMap, x, y, x, y+1); 
        boolean isLeftValid = isValidTilePosition(tileMap, x-1, y) && isSameTileType(tileMap, x, y, x-1, y); 
        boolean isRightValid = isValidTilePosition(tileMap, x+1, y) && isSameTileType(tileMap, x, y, x+1, y); 
        
        if(isTopValid && isDownValid && isLeftValid && isRightValid) {
            return TilePosition.CENTER_MID;
        }
        
        if(isDownValid && isLeftValid && isRightValid) {
            return TilePosition.TOP_MID;
        }
        
        if(isTopValid && isLeftValid && isRightValid) {
            return TilePosition.BOTTOM_MID;
        }
        
        if(isTopValid && isDownValid && isLeftValid) {
            return TilePosition.CENTER_RIGHT;
        }
        
        if(isTopValid && isDownValid && isRightValid) {
            return TilePosition.CENTER_LEFT;
        }
        
        if(isDownValid && isRightValid) {
            return TilePosition.TOP_LEFT;
        }
        
        if(isDownValid && isLeftValid) {
            return TilePosition.TOP_RIGHT;
        }
        
        if(isTopValid && isRightValid) {
            return TilePosition.BOTTOM_LEFT;
        }
        
        if(isTopValid && isLeftValid) {
            return TilePosition.BOTTOM_RIGHT;
        }
        
        return TilePosition.BOTTOM_LEFT;
    }
    
}
