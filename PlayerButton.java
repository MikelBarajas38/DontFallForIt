import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PlayerButton extends Button
{
    private String playerAnimation;
    
    public PlayerButton(String path, String playerAnimation){
        super(path);
        this.playerAnimation = playerAnimation;
    }
    
    public void onButtonClicked(){
        String name = Greenfoot.ask("Ingresa tu nombre");
        Greenfoot.setWorld(new DifficultyMenu(playerAnimation,name));
    }
}
