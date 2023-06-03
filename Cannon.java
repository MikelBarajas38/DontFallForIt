import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Random;
import java.util.EnumMap;

public class Cannon extends Enemy implements StateMachine
{
    private static final int SCORE = 50;

    private int deltaShootFireball;
    private int deltaMovementFrames;
    private boolean shouldMove;
    
    private Random random = new Random();
    
    private final BaseState idleState = new IdleState();
    private final BaseState hitState = new HitState();
    private final StateManager stateManager = new StateManager(this);
    
    private final Direction shootDirection;
    
    private final AnimationManager animationManager = new AnimationManager(this, "images/sprites/cannon/");
    
    public Cannon(int x, int y, Direction d) {
        super(x, y, SCORE, true);
        stateManager.changeState(State.IDLE);
        shootDirection = d;
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
        LevelWorld world = (LevelWorld) getWorld();
        world.setScore(world.getScore() + SCORE);
    }
    
    public State getState(){
        return stateManager.getCurrentState();
    }
    
    private void handleShootDirection(){
        switch(shootDirection){
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
    
    private class IdleState implements BaseState {
        
        public void enter() {
            animationManager.changeSprite(State.IDLE);
            deltaShootFireball = 0;
            shouldMove = false;
            setVelocityY(0);
        }
        
        public State input() {
            return State.NULL;
        }
        
        
        public State process() {
            
            if(deltaShootFireball == 0){
                shouldMove = true;
                shoot();
            }else if(deltaShootFireball == 8){
                shouldMove = false;
            }else if(deltaShootFireball == 200) deltaShootFireball = -1;
            
            deltaShootFireball++;
            
            if(shouldMove) {
                animationManager.nextSprite();
            }
            
            handleShootDirection();
            return State.NULL;
        }
        
        public void exit() {
            
        }
        
        private void shoot(){
            int x=getX();
            int y=getY();
            
            switch(shootDirection){
                case UP:
                    y-=16;
                    break;
                case RIGHT:
                    x=+16;
                    break;
                case DOWN:
                    y+=16;
                    break;
                case LEFT:
                    x-=16;
                    break;
                default:
                    x+=16;
            }
            
        getWorld().addObject(new Fireball(x,y,shootDirection), x, y);
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
