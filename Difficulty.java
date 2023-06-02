public enum Difficulty
{
    EASY("testLevel"), 
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
