import greenfoot.*;  

public class PlayButton extends Button
{
    public PlayButton(String text, int size, Color color){
        super("images/buttons/inicia.png");
    }

    public void onButtonClicked(){
        Greenfoot.setWorld(new LevelWorld("level/testLevel", "scoreboard/score0.txt", "images/sprites/player0/","p"));
    }
}
