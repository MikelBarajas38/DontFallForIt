import greenfoot.*;  

public class PlayButton extends Button
{
    public PlayButton(String text, int size, Color color){
        super(text,size,color);
    }

    public void onButtonClicked(){
        Greenfoot.setWorld(new LevelWorld("levels/testLevel", "scoreboard/score0.txt"));
    }
}
