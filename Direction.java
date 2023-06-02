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
    
    public Direction getOpposite() {
        switch(this) {
            case DOWN:
                return UP;
            
            case LEFT:
                return RIGHT;
            
            case UP:
                return DOWN;
                
            case RIGHT:
                return LEFT;
            
            default:
                return UP;    
        }
    }
}
