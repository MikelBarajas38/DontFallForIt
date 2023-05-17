import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class QuitGameButton extends Button
{   
    public QuitGameButton(String text, int size, Color color){
        super(text,size,color);
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            onButtonClicked();
        }
    }
    public void onButtonClicked(){
        
    }
}
