import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Random;
import java.util.EnumMap;

public class Bird extends Enemy implements StateMachine
{
    private static final int MOVEMENT_SPEED = 1;
    private static final int SCORE = 200;
    private static final int JUMP_SPEED = 20;
    
    private int deltaMovementFrames;
    
    private int airFrames;
    private int baseGravity;
    private int heavyGravity;
    
    private Random random = new Random();
    
    private final BaseState idleState = new IdleState();
    private final BaseState walkState = new WalkState();
    private final BaseState hitState = new HitState();
    private final BaseState jumpState = new JumpState();
    private final BaseState fallState = new FallState();
    private final StateManager stateManager = new StateManager(this);
    
    private final AnimationManager animationManager = new AnimationManager(this, "images/sprites/bird/");
    
    public Bird(int x, int y) {
        super(x, y, SCORE, true);
        stateManager.changeState(State.IDLE);
        setDirectionX(Direction.RIGHT);
        baseGravity = getGravity();
        heavyGravity = baseGravity * 3;
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
        states.put(State.JUMP, jumpState);
        states.put(State.FALL, fallState);
        return states;
    }
    
    public void handleState() {
        stateManager.handleInput();
        stateManager.handleProcess();
    }
    
    public void destroy() {
        setDead();
        stateManager.changeState(State.HIT);
        LevelWorld world = (LevelWorld) getWorld();
        world.setScore(world.getScore() + SCORE);
    }
    
    public State getState(){
        return stateManager.getCurrentState();
    }
    
    private class IdleState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.IDLE);
            deltaMovementFrames = 0;
        }
        
        public State input() {
            return State.NULL;
        }
        
        
        public State process() {
            if(isOnWall()){
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
            
            handleGravity();
            
            if(deltaMovementFrames > 6){
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
            if(isOnWall()){
                if(getDirectionX() == Direction.LEFT){
                    setDirectionX(Direction.RIGHT);
                } else {
                    setDirectionX(Direction.LEFT);
                }
            }
                        
            handleGravity();
            
            if(getDirectionX() == Direction.RIGHT) {
                setVelocityX(-MOVEMENT_SPEED);
            } else {
                setVelocityX(+MOVEMENT_SPEED);
            }
            
            if(deltaMovementFrames > 16){
                return State.JUMP;
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
    
    private class JumpState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.JUMP);

            if(getDirectionY() == Direction.UP) {
                setVelocityY(JUMP_SPEED);
            } else {
                setVelocityY(-JUMP_SPEED);
            }
            airFrames = 0;
        }
        
        public State input() {
            return State.NULL;
        }
        
        public State process() {
            if(isOnWall()){
                if(getDirectionX() == Direction.LEFT){
                    setDirectionX(Direction.RIGHT);
                } else {
                    setDirectionX(Direction.LEFT);
                }
            }
                        
            if(isFalling()) {
                return State.FALL;
            }
            
            handleGravity();
            
            if(getDirectionX() == Direction.RIGHT) {
                setVelocityX(-MOVEMENT_SPEED);
            } else {
                setVelocityX(+MOVEMENT_SPEED);
            }
            
            airFrames++;
            
            handleCollisions();
            setMovement();
            
            animationManager.nextSprite();
            
            return State.NULL;
        }
        
        public void exit() {
            handleCollisions();
            setMovement();
        }
        
    }
    
    private class FallState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.FALL);
        }
        
        public State input() {
            return State.NULL;
        }
        
        public State process() {
            handleGravity();
            
            if(isOnWall()){
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
            
            if(isGrounded()) {
                return State.IDLE;
            }
            
            airFrames++;
            
            handleCollisions();
            setMovement();
            
            animationManager.nextSprite();
            
            return State.NULL;
        }
        
        public void exit() {
            handleCollisions();
            setMovement();
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
