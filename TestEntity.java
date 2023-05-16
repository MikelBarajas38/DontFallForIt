import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestEntity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestEntity extends PhysicsEntity
{
    /**
     * Act - do whatever the TestEntity wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public TestEntity(int x, int y) {
        super(x, y);
        setImage("images/test.jpg");
        GreenfootImage image = getImage();
        image.scale(32, 32);
        setImage(image);
    }
    
    public void act()
    {
        super.act();
    }
    
    public void handleMovement() {
        if(Greenfoot.isKeyDown("left") && !terrainIsLeft()) {
            setVelocityX(-5);
        }else if(Greenfoot.isKeyDown("right") && !terrainIsRight()) {
            setVelocityX(5);
        } else{
            setVelocityX(0);
        }
    }
    
}
