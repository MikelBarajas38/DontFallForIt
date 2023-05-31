public enum EnemyType  
{
    
    MADSHROOM,
    GHOST;
    
    public static EnemyType getEnemyType(int key) {
        switch(key) {
            case 1:
                return MADSHROOM;
            
            case 2:
                return GHOST;
            
            default:
                return MADSHROOM;    
        }
    }
    
}
