import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SelectLevelButton extends Button
{
    private LevelWorld actualLevelWorld;
    private int levelSelector;
    
    public SelectLevelButton(String text, int size, Color color,int levelSelector, LevelWorld world){
        super(text,size,color);
        this.levelSelector = levelSelector;
        actualLevelWorld = world;
    }

    public void onButtonClicked(){
        
        while(levelSelector>1){
            actualLevelWorld.nextRoom();
            levelSelector--;
        }
        
        Greenfoot.setWorld(actualLevelWorld);
    }
}
