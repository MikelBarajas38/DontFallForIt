import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class QuitGameButton extends Button
{   
    public QuitGameButton(String path){
        super(path);
    }

    public void onButtonClicked(){
        Greenfoot.setWorld(new QuitGameScreen());
        Greenfoot.stop();
    }
}
