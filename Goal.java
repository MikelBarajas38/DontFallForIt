import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Goal extends Actor
{
    
    private final SimpleAnimationManager animationManager;
    
    public Goal(int x, int y, Direction direction, String path) {
        switch(direction){
            case UP:
                setRotation(0);
                break;
            case RIGHT:
                setRotation(90);
                break;
            case DOWN:
                setRotation(180);
                break;
            case LEFT:
                setRotation(270);
                break;
            default:
                setRotation(0);
        }
        animationManager = new SimpleAnimationManager(this, path);
    }
    
    public void act()
    {
        animationManager.nextSprite();
    }
    
    public abstract void activate();
}
