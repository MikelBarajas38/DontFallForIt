import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * 
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 760, 1); 
        
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,600,380);

        Platform platform = new Platform(300,40);
        addObject(platform,434,420);

        Platform platform1 = new Platform(50,760);
        addObject(platform1, 25, 380);

        Platform platform2 = new Platform(300,40);
        addObject(platform2, 900, 580);

        Platform platform3 = new Platform(300,40);
        addObject(platform3,300,220);
        player.setLocation(435,558);
    }
}
