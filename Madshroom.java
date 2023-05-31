import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Random;
import java.util.EnumMap;

public class Madshroom extends Enemy implements StateMachine
{
    
    private static final int MOVEMENT_SPEED = 1;
    private static final int SCORE = 100;

    private int deltaMovementFrames;
    private boolean shouldMove;
    
    private Random random = new Random();
    
    private final BaseState idleState = new IdleState();
    private final BaseState walkState = new WalkState();
    private final BaseState hitState = new HitState();
    private final StateManager stateManager = new StateManager(this);
    
    private final AnimationManager animationManager = new AnimationManager(this, "images/sprites/madshroom/");
    
    public Madshroom(int x, int y) {
        super(x, y, SCORE, true);
        stateManager.changeState(State.IDLE);
        setDirectionX(Direction.LEFT);
    }
    
    public void act()
    {        
        handleState();        
        if (isAtEdge()) getWorld().removeObject(this);
    }
    
    public EnumMap<State, BaseState> getStates() {
        EnumMap<State, BaseState> states = new EnumMap(State.class);
        states.put(State.IDLE, idleState);
        states.put(State.WALK, walkState);
        states.put(State.HIT, hitState);
        return states;
    }
    
    public void handleState() {
        stateManager.handleInput();
        stateManager.handleProcess();
    }
    
    public void destroy() {
        setDead();
        stateManager.changeState(State.HIT);
    }
    
    private class IdleState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.IDLE);
            deltaMovementFrames = 0;
            shouldMove = false;
            setVelocityY(0);
        }
        
        public State input() {
            if(random.nextInt(1) == 0){
                shouldMove = true;
            }
            
            return State.NULL;
        }
        
        
        public State process() {
            
            handleGravity();
            
            if(deltaMovementFrames > 60 && shouldMove){
                return State.WALK;
            }
            
            deltaMovementFrames++;
            
            handleCollisions();
            setMovement();
            
            animationManager.nextSprite();
            
            return State.NULL;
        }
        
        public void exit() {
            
        }
        
    }
    
    private class WalkState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.WALK);
            deltaMovementFrames = 0;
        }
        
        public State input() {
            return State.NULL;
        }
        
        public State process() {
            
            handleGravity();

            
            if(getDirectionX() == Direction.RIGHT) {
                setVelocityX(-MOVEMENT_SPEED);
            } else {
                setVelocityX(+MOVEMENT_SPEED);
            }
            
            if(deltaMovementFrames > 100){
                return State.IDLE;
            }
            
            deltaMovementFrames++;
            
            handleCollisions();
            setMovement();
            
            animationManager.nextSprite();
            
            return State.NULL;
        }
        
        public void exit() {
            if(getDirectionX() == Direction.LEFT){
                setDirectionX(Direction.RIGHT);
            } else {
                setDirectionX(Direction.LEFT);
            }
        }
        
    }
    
    
    private class HitState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.HIT);
            if(getDirectionY() == Direction.UP) {
                setVelocityY(3);
            } else {
                setVelocityY(-3);
            }
            
            if(getDirectionX() == Direction.LEFT) {
                setVelocityX(3);
            } else {
                setVelocityX(-3);                
            }
            
            deltaMovementFrames = 0;
        }
        
        public State input() {
            return State.NULL;
        }
        
        public State process() {
            
            setVelocityY(getVelocityY() + 1);
            
            setRotation(getRotation() + deltaMovementFrames);
            
            setMovement();
            
            animationManager.nextSprite();
            
            deltaMovementFrames++;
            
            return State.NULL;
        }
        
        public void exit() {
            
        }
        
    }
}
