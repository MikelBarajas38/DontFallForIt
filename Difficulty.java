public enum Difficulty
{
    EASY("level1"), 
    MEDIUM("testLevel"), 
    HARD("testLevel");
    
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
