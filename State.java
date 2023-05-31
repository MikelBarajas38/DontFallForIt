public enum State  
{
    NULL, 
    IDLE("idle"), 
    WALK("walk"), 
    FALL("fall"), 
    JUMP("jump"), 
    WALLSLIDE("wallslide"), 
    SPECIAL("special"), 
    HIT("hit");
    
    private String path;
    
    State() {
    }
    
    State(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
    
}
