public enum TileType
{
    GREEN("green"),
    CONCRETE_BORDER("concrete_border"),
    PINK("pink"),
    YELLOW("yellow"),
    WOODEN_BORDER("wooden_border");
    
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
                
            case "2":
                return PINK;
                
            case "3":
                return YELLOW;
            
            case "c":
                return CONCRETE_BORDER;
            
            case "w":
                return WOODEN_BORDER;
            
            default:
                return GREEN;    
        }
    }
}
