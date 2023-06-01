public enum TileType
{
    GREEN("green"),
    CONCRETE_BORDER("concrete_border");
    
    private String path;
    
    TileType(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
    
    public static TileType getTileType(String key) {
        switch(key) {
            case "1":
                return GREEN;
            
            case "c":
                return CONCRETE_BORDER;
            
            default:
                return GREEN;    
        }
    }
}
