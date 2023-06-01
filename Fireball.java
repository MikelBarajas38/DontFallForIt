import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Random;
import java.util.EnumMap;

public class Fireball extends Enemy
{
    
    private static final int MOVEMENT_SPEED = 1;
    private static final int SCORE = 0;

    private int deltaMovementFrames;
    private boolean shouldMove;
    
    private Random random = new Random();
    
    private final BaseState walkState = new WalkState();
    
    private final StateManager stateManager = new StateManager(this);
    
    private final Direction direction;
    
    private final AnimationManager animationManager = new AnimationManager(this, "images/sprites/fireball/");
    
    public Fireball(int x, int y,Direction direction) {
        super(x, y, SCORE, false);
        stateManager.changeState(State.WALK);
        this.direction=direction;
    }
    
    public void act()
    {
        handleState(); 
    }
    
    public EnumMap<State, BaseState> getStates() {
        EnumMap<State, BaseState> states = new EnumMap(State.class);
        states.put(State.WALK, walkState);
        return states;
    }
    
    public void handleState() {
        stateManager.handleInput();
        stateManager.handleProcess();
    }
    
    public void destroy(){
        
    }
    
    public void disappear(){
        getWorld().removeObject(this);
    }
    
    public String getState(){
        return stateManager.getCurrentState();
    }
    
    private void handleShootDirection(){
        switch(direction){
            case UP:
                setRotation(270);
                break;
            case RIGHT:
                setRotation(0);
                break;
            case DOWN:
                setRotation(90);
                break;
            case LEFT:
                setRotation(180);
                break;
            default:
                setRotation(0);
        }
    }
    
    private class WalkState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.WALK);
        }
        
        public State input() {
            return State.NULL;
        }
        
        
        public State process() {
            if(isOnWall()) {
                disappear();
            }
            
            if(direction == Direction.RIGHT) {
                setVelocityX(MOVEMENT_SPEED);
            } else if(direction == Direction.LEFT){
                setVelocityX(-MOVEMENT_SPEED);
            } else if(direction == Direction.UP){
                setVelocityY(-MOVEMENT_SPEED);
            }else{
                setVelocityY(MOVEMENT_SPEED);
            }
            
            setMovement();
            
            animationManager.nextSprite();
            handleShootDirection();
            
            return State.NULL;
        }
        
        public void exit() {

        }
        
    }
}
