import java.util.List;

public class TileFactory  
{
    public static Tile getTile(TileType type, List<List<String>> tileMap, int x, int y, int size) {
        switch(type) {
            case GREEN:
                return new Terrain(type, tileMap, x, y, size);
            case CONCRETE_BORDER:
                return new Border(type, tileMap, x, y, size);
                
            default:
                return new Terrain(type.GREEN, tileMap, x, y, size);  
        }
    }
}
