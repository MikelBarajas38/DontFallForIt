import greenfoot.*;  

import java.util.Random;
import java.util.EnumMap;

public class Saw extends Enemy implements StateMachine
{
    private static final int SCORE = 0;
    
    private Random random = new Random();
    
    private final BaseState idleState = new IdleState();
    
    private final StateManager stateManager = new StateManager(this);
    
    private final AnimationManager animationManager = new AnimationManager(this, "images/sprites/saw/");
    
    public Saw(int x, int y) {
        super(x, y, SCORE, false);
        stateManager.changeState(State.IDLE);
    }
    
    public void act()
    {        
        handleState();        
        if (isAtEdge()) getWorld().removeObject(this);
    }
    
    public Direction handleDirection(){
        if(terrainIsRight()) return Direction.LEFT;
        else return Direction.RIGHT;
    }
    
    public EnumMap<State, BaseState> getStates() {
        EnumMap<State, BaseState> states = new EnumMap(State.class);
        states.put(State.IDLE, idleState);
        return states;
    }
    
    public void handleState() {
        stateManager.handleInput();
        stateManager.handleProcess();
    }
    
    public void destroy() {
        
    }
    
    public State getState(){
        return stateManager.getCurrentState();
    }
    
    private class IdleState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.IDLE);
            setVelocityY(0);
        }
        
        public State input() {
            return State.NULL;
        }
        
        
        public State process() {
            animationManager.nextSprite();
                
            return State.NULL;
        }
        
        public void exit() {
            
        }
    }
}
