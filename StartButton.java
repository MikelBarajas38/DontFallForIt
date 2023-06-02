import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StartButton extends Button
{
    public StartButton(String path){
        super(path);
    }

    public void onButtonClicked(){
        Greenfoot.setWorld(new PlayerSelectorMenu());
    }
}
