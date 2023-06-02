/**
 * Write a description of class HUD here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HUD  
{

    LevelWorld currentWorld;
    
    HUD(LevelWorld currentWorld){
        this.currentWorld = currentWorld;
    }
    
    public void update() {
        currentWorld.showText("TIME: " + currentWorld.getTimer(), 192, 120);
        currentWorld.showText("" + currentWorld.getScore(), 600, 120);
        currentWorld.showText("" + currentWorld.getBigCoinCounter(), 600, 100);
    }

}
