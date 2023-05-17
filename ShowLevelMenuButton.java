import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShowLevelMenuButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShowLevelMenuButton extends Button
{
    public ShowLevelMenuButton(String text, int size, Color color){
        super(text,size,color);
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            onButtonClicked();
        }
    }
    public void onButtonClicked(){
        Greenfoot.setWorld(new LevelMenu());
    }
}
