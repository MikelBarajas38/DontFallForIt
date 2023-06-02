public class EnemyFactory  
{
    
    public static Enemy getEnemy(EnemyType type, int x, int y, Direction direction) {
        switch(type) {
            case MADSHROOM:
                return new Madshroom(x,y);
            case GHOST:
                return new Ghost(x,y);
            case SKULL:
                return new Skull(x,y);
            case BIRD:
                return new Bird(x,y);
            case SAW:
                return new Saw(x,y);
            case CANNON:
                return new Cannon(x,y,direction.getOpposite());
            
            default:
                return new Madshroom(x,y);    
        }
    }
    
}
