import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PlayerSelectorMenu extends World
{
    
    public PlayerSelectorMenu()
    {    
        super(800, 600, 1); 
        prepare();
    }
    
    public void prepare(){
        setBackground("images/background.png");
        
        getBackground().drawImage(new GreenfootImage("Selecciona un jugador", 64,Color.GREEN, null), 40, 80);
                
        
        Button playerButton1 = new PlayerButton ("images/buttons/p1.png","images/sprites/player0/");
        addObject(playerButton1, getWidth() / 2 - 200, 350);
        
        Button playerButton2 = new PlayerButton ("images/buttons/p2.png","images/sprites/player1/");
        addObject(playerButton2, getWidth() / 2, 350);
        
        Button playerButton3 = new PlayerButton ("images/buttons/p3.png","images/sprites/player2/");
        addObject(playerButton3, getWidth() / 2 + 200, 350);
    }
}
