import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainMenu extends Menu
{
    private static final int FONT_SIZE = 50;
    
    public MainMenu()
    {    
        super();
        prepare();
    }
    
    public void prepare(){
        setBackground("images/Titulo.jpg");
        
        Button playGame = new PlayButton("Jugar",FONT_SIZE, Color.GREEN);
        addObject(playGame, 610, 400);
        
        Button howToPlayButton = new HowToPlayButton("Como jugar",FONT_SIZE,Color.GREEN);
        addObject(howToPlayButton,555,455);
        
        Button quitGame = new QuitGameButton("Salir",FONT_SIZE,Color.GREEN);
        addObject(quitGame, 620, 510);
    }
}
