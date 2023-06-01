import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Random;
import java.util.EnumMap;

public class Ghost extends Enemy
{
    private static final int MOVEMENT_SPEED = 1;
    private static final int SCORE = 150;
    private static final long TIME_FOR_DISAPPEAR = 2000;
    private static final long TIME_FOR_APPEAR = 10000;

    private int deltaMovementFrames;
    private boolean shouldMove;
    
    private Random random = new Random();
    
    private final BaseState walkState = new WalkState();
    private final BaseState hitState = new HitState();
    private final BaseState specialState = new SpecialState();
    private final StateManager stateManager = new StateManager(this);
    
    private final AnimationManager animationManager = new AnimationManager(this, "images/sprites/ghost/");
    
    public Ghost(int x, int y) {
        super(x, y, SCORE, true);
        stateManager.changeState(State.WALK);
        setDirectionX(Direction.LEFT);
        setGravity(0);
    }
    
    public void act()
    {
        handleState();        
        if (isAtEdge()) getWorld().removeObject(this);
    }
    
    public EnumMap<State, BaseState> getStates() {
        EnumMap<State, BaseState> states = new EnumMap(State.class);
        states.put(State.WALK, walkState);
        states.put(State.SPECIAL, specialState);
        states.put(State.HIT, hitState);
        return states;
    }
    
    public void handleState() {
        stateManager.handleInput();
        stateManager.handleProcess();
    }
    
    public void destroy(){
        setDead();
        stateManager.changeState(State.HIT);
    }
    
    public String getState(){
        return stateManager.getCurrentState();
    }
    
    private class WalkState implements BaseState {
        int nextStateFrameLimit = 0;
        Random rand = new Random();
        
        public void enter() {
            animationManager.changeSprite(State.WALK);
            deltaMovementFrames = 0;
            nextStateFrameLimit = 200+rand.nextInt(300);
        }
        
        public State input() {
            return State.NULL;
        }
        
        public State process() {
            handleGravity();
            
            if(isOnWall()) {
                if(getDirectionX() == Direction.LEFT){
                    setDirectionX(Direction.RIGHT);
                } else {
                    setDirectionX(Direction.LEFT);
                }
            }
            
            if(getDirectionX() == Direction.RIGHT) {
                setVelocityX(-MOVEMENT_SPEED);
            } else {
                setVelocityX(+MOVEMENT_SPEED);
            }
            
            if(deltaMovementFrames > nextStateFrameLimit){
                return State.SPECIAL;
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
    
    private class SpecialState implements BaseState {
        int nextStateFrameLimit = 0;
        Random rand = new Random();
        
        public void enter() {
            animationManager.changeSprite(State.SPECIAL);
            deltaMovementFrames = 0;
            nextStateFrameLimit = 200+rand.nextInt(300);
        }
        
        public State input() {
            if(random.nextInt(1) == 0){
                shouldMove = true;
            }
            
            return State.NULL;
        }
        
        
        public State process() {
            handleGravity();
            if(isOnWall()) {
                if(getDirectionX() == Direction.LEFT){
                    setDirectionX(Direction.RIGHT);
                } else {
                    setDirectionX(Direction.LEFT);
                }
            }
            if(getDirectionX() == Direction.RIGHT) {
                setVelocityX(-MOVEMENT_SPEED);
            } else {
                setVelocityX(+MOVEMENT_SPEED);
            }
            
            if(deltaMovementFrames > nextStateFrameLimit){
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
}

