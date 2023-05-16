public enum Direction  
{
    UP, RIGHT, DOWN, LEFT;
    
    public static Direction getDirection(int key) {
        switch(key) {
            case 1:
                return UP;
            
            case 2:
                return RIGHT;
            
            case 3:
                return DOWN;
                
            case 4:
                return LEFT;
            
            default:
                    return UP;    
        }
    }
}
