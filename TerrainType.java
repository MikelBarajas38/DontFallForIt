public enum TerrainType  
{
    GREEN("green", false),
    CONCRETE_BORDER("concrete_border",true);
    
    private String path;
    private boolean isBorder;
    
    TerrainType(String path, boolean isBorder) {
        this.path = path;
        this.isBorder = isBorder;
    }
    
    public String getPath() {
        return path;
    }
    
    public boolean isBorder() {
        return isBorder;
    }
    
    public static TerrainType getTerrainType(String key) {
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
