import greenfoot.*;

public abstract class Menu extends World {
    
    public Menu() {    
        super(800, 600, 1); 
        
        prepare();
    }
    
    abstract public void prepare();
    
}
