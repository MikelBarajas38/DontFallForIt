public enum Difficulty
{
    EASY("level1"), 
    MEDIUM("level2"), 
    HARD("level3");
    
    private String path;
    
    Difficulty() {
    }
    
    Difficulty(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
}
