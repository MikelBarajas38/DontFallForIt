import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SelectLevelButton extends Button
{
    private LevelWorld actualLevelWorld;
    private Difficulty difficulty;
    
    private String playerPath;
    private String playerName;
    
    public SelectLevelButton(String path,Difficulty difficulty,String playerPath, String playerName){
        super(path);
        this.playerPath = playerPath;
        this.difficulty = difficulty;
        this.playerName = playerName;
    }

    public void onButtonClicked(){
        actualLevelWorld = new LevelWorld("levels/"+difficulty.getPath(),"scoreboard/score0.txt",playerPath,playerName);
        Greenfoot.setWorld(actualLevelWorld);
    }
}
