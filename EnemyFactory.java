public class EnemyFactory  
{
    
    public static Enemy getEnemy(EnemyType type, int x, int y) {
        switch(type) {
            case MADSHROOM:
                return new Madshroom(x,y);
            
            default:
                return new Madshroom(x,y);    
        }
    }
    
}
