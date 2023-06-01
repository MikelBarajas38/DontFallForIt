import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.EnumMap;

public abstract class Collectable extends PhysicsEntity implements StateMachine
{
    
    private int score;
    private boolean active = true;
    
    private boolean isAffectedByGravity;
    
    private int deathFrames = 6;
    private int currentFrame = 0;
    
    private final BaseState idleState = new IdleState();
    private final BaseState hitState = new HitState();
    private final StateManager stateManager = new StateManager(this);
    
    private final AnimationManager animationManager;
    
    public Collectable(int x, int y, int score, boolean isAffectedByGravity, String path) {
        super(x,y);
        this.score = score;
        animationManager = new AnimationManager(this, path);
        stateManager.changeState(State.IDLE);
        setDirectionX(Direction.LEFT);
        this.isAffectedByGravity = isAffectedByGravity;
    }
    
    public abstract void destroy();
    
    public int getScore() {
        return score;
    }
    
    public boolean isActive(){
        return active;
    }
    
    public void deactivate(){
        active = false;
    }
    
    public void act()
    {        
        handleState();    
        if(!isActive()) getWorld().removeObject(this);
    }
    
    public StateManager getStateManager() {
        return stateManager;
    }
    
    public EnumMap<State, BaseState> getStates() {
        EnumMap<State, BaseState> states = new EnumMap(State.class);
        states.put(State.IDLE, idleState);
        states.put(State.HIT, hitState);
        return states;
    }
    
    public void handleState() {
        stateManager.handleInput();
        stateManager.handleProcess();
    }
    
    public State getState(){
        return stateManager.getCurrentState();
    }
    
    private class IdleState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.IDLE);
        }
        
        public State input() {            
            return State.NULL;
        }
        
        
        public State process() {
            
            if(isAffectedByGravity){
                handleGravity();            
                handleCollisions();
                setMovement(); 
            }
            
            if(currentFrame == 2) {
                animationManager.nextSprite();
                currentFrame = 0;
            }
            
            currentFrame++;
            return State.NULL;
        }
        
        public void exit() {
            
        }
        
    }
    
    private class HitState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.HIT);
        }
        
        public State input() {
            return State.NULL;
        }
        
        public State process() {
            if(deathFrames >= 0){
                animationManager.nextSprite();
            } else {
                deactivate();
            }
            
            deathFrames--;
            return State.NULL;
        }
        
        public void exit() {
            
        }
        
    }
    
}
