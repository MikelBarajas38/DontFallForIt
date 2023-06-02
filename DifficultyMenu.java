import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DifficultyMenu extends World
{
    private String playerPath;
    private String playerName;
    
    public DifficultyMenu(String playerPath, String playerName)
    {    
        super(800, 600, 1); 
        this.playerPath = playerPath;
        prepare();
    }
    
    public void prepare(){
        setBackground("images/background.png");
        Button selectLevelButton1 = new SelectLevelButton("images/buttons/facil.png",Difficulty.EASY,playerPath,playerName);
        addObject(selectLevelButton1, getWidth() / 2, 200);
        
        Button selectLevelButton2 = new SelectLevelButton("images/buttons/medio.png",Difficulty.MEDIUM,playerPath,playerName);
        addObject(selectLevelButton2, getWidth() / 2, 300);
        
        Button selectLevelButton3 = new SelectLevelButton("images/buttons/dificil.png",Difficulty.HARD,playerPath,playerName);
        addObject(selectLevelButton3, getWidth() / 2, 400);
    }
}
