import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainMenu extends World
{
    private static final int FONT_SIZE = 50;
    
    public MainMenu()
    {    
        super(800, 600, 1);
        prepare();
    }
    
    public void prepare(){
        setBackground("images/home_page.png");
        
        Button startGame = new StartButton("images/buttons/iniciar.png");
        addObject(startGame, 615, 345);
        
        Button howToPlayButton = new HowToPlayButton("images/buttons/como_jugar.png");
        addObject(howToPlayButton,577,430);
        
        Button quitGame = new QuitGameButton("images/buttons/salir.png");
        addObject(quitGame, 626, 510);
    }
}
