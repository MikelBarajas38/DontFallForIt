import java.util.Random;

public class Transition
{
    
    private LevelWorld currentLevel;
    boolean shouldChangeRoom;
    private boolean isActive = false;
    
    private Random random = new Random();
    
    Transition(LevelWorld currentLevel, boolean shouldChangeRoom) {
        this.currentLevel = currentLevel;
        this.shouldChangeRoom = shouldChangeRoom;
    }
    
    public void setUp() {
        currentLevel.addObject(new TransitionSquare(this),random.nextInt(50) + 200,random.nextInt(50) + 200);
        currentLevel.addObject(new TransitionSquare(this),random.nextInt(50) + 200,random.nextInt(50) + 500);
        currentLevel.addObject(new TransitionSquare(this),random.nextInt(50) + 500,random.nextInt(50) + 200);
        currentLevel.addObject(new TransitionSquare(this),random.nextInt(50) + 500,random.nextInt(50) + 500);
        currentLevel.addObject(new TransitionSquare(this),random.nextInt(50) + 360,random.nextInt(50) + 360);
    }
    
    public void activate() {
        
        if(!isActive) {
            if(shouldChangeRoom) {
                currentLevel.nextRoom();
            } else {
                currentLevel.reloadRoom();
            }  
            isActive = true;
        }
        
    }
    
}
