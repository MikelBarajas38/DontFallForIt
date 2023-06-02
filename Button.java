import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Button extends Actor
{
    private GreenfootImage button;
    
    public Button(String path){
        button = new GreenfootImage(path);
        setImage(button);
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            onButtonClicked();
        }
    }
    
    public abstract void onButtonClicked();
}

