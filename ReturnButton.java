import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ReturnButton extends Button
{
    private World world;
    public ReturnButton(String path,World world){
        super(path);
        this.world = world;
    }

    public void onButtonClicked(){
        Greenfoot.setWorld(world);
    }
}
