import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HowToPlayMenu extends World
{
    public HowToPlayMenu()
    {    
        super(800, 600, 1);
        prepare();
    }
    
    public void prepare(){
        setBackground("images/how_to_play.png");
        
        Button returnButton = new ReturnButton ("images/buttons/atras.png",new MainMenu());
        addObject(returnButton, getWidth() / 2 - 270, 510);
    }
}
