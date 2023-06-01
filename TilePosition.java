import java.util.List;

public enum TilePosition  
{
    TOP_LEFT("top_left.png"),
    TOP_MID("top_mid.png"),
    TOP_RIGHT("top_right.png"),
    CENTER_LEFT("center_left.png"),
    CENTER_MID("center_mid.png"),
    CENTER_RIGHT("center_right.png"),
    BOTTOM_LEFT("bottom_left.png"),
    BOTTOM_MID("bottom_mid.png"),
    BOTTOM_RIGHT("bottom_right.png");
    
    private String path;
    
    TilePosition() {
    }
    
    TilePosition(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }

}
