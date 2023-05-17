import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Button extends Actor
{
    private String text;
    private Color color;
    private GreenfootImage textImage;
    
    public Button(String text, int size, Color color){
        textImage = new GreenfootImage(text, size, color, null);
        setImage(textImage);
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            // Set action
        }
    }
    
    public abstract void onButtonClicked();
}

