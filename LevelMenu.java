import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LevelMenu extends Menu
{
    private static final int FONT_SIZE = 50;
    
    public LevelMenu()
    {
        super();
        prepare();
    }
    public void prepare(){
        LevelWorld levelWorld = new LevelWorld();
        
        Button selectLevelButton1 = new SelectLevelButton("Nivel 1",FONT_SIZE, Color.BLUE,1,levelWorld);
        addObject(selectLevelButton1, getWidth() / 2, 200);
        
        Button selectLevelButton2 = new SelectLevelButton("Nivel 2",FONT_SIZE, Color.BLUE,2,levelWorld);
        addObject(selectLevelButton2, getWidth() / 2, 300);
        
        Button selectLevelButton3 = new SelectLevelButton("Nivel 3",FONT_SIZE, Color.BLUE,3,levelWorld);
        addObject(selectLevelButton3, getWidth() / 2, 400);
        
    }
}
