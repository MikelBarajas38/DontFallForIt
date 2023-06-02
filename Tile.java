import greenfoot.*;  
import java.util.List;

public abstract class Tile extends Actor  
{
    private int id;
    private int height;
    private int width;

    public Tile(int size, TileType type, List<List<String>> tileMap, int x, int y, String path) {
        this.height = size;
        this.width = size;
        TilePosition position = getTilePosition(tileMap, x, y);
        GreenfootImage sprite = new GreenfootImage(path + type.getPath() + "/" + position.getPath());
        setImage(sprite);
    }
    
    public int getSize() {
        return height;
    }
    
    public abstract TilePosition getTilePosition(List<List<String>> tileMap, int x, int y);
    
    public static boolean isValidTilePosition(List<List<String>> tileMap, int x, int y) {
        return (x >= 0) && (x < tileMap.get(0).size()) && (y >= 0) && (y < tileMap.size());
    }
    
    public static boolean isSameTileType(List<List<String>> tileMap, int x1, int y1, int x2, int y2){
        return tileMap.get(y1).get(x1).equals(tileMap.get(y2).get(x2));
    }
    
}
