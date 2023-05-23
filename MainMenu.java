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
        setBackground("images/Titulo.jpg");
        
        Button playGame = new PlayButton("Jugar",FONT_SIZE, Color.GREEN);
        addObject(playGame, 610, 345);
        
        Button levelMenu = new ShowLevelMenuButton("Elegir nivel",FONT_SIZE,Color.GREEN);
        addObject(levelMenu, 560, 400);
        
        Button howToPlayButton = new HowToPlayButton("Como jugar",FONT_SIZE,Color.GREEN);
        addObject(howToPlayButton,555,455);
        
        Button quitGame = new QuitGameButton("Salir",FONT_SIZE,Color.GREEN);
        addObject(quitGame, 620, 510);
    }
}
