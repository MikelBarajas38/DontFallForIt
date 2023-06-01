public enum EnemyType  
{
    
    MADSHROOM,
    GHOST,
    SKULL,
    CANNON,
    BIRD,
    SAW;
    
    public static EnemyType getEnemyType(int key) {
        switch(key) {
            case 1:
                return MADSHROOM;
                
            case 2:
                return GHOST;
                
            case 3:
                return SKULL;
            
            case 4:
                return CANNON;
        
            case 5:
                return BIRD;
                
            case 6:
                return SAW;
            
            default:
                return MADSHROOM;    
        }
    }
    
}
