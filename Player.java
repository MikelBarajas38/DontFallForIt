import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends PhysicsEntity
{
    
    private static final int MOVEMENT_SPEED = 6;
    private static final int JUMP_SPEED = 12;
    private static final int MAX_BOUNCE = 8;

    private int acceleration = 1;
    
    boolean isJumping;
    private int baseGravity;
    private int heavyGravity;

    private int currentBounce;
    private long timeSinceLastKeyPress;
    
    public Player(int x, int y) {
        super(x, y);
        isJumping = false;
        baseGravity = getGravity();
        heavyGravity = baseGravity * 6;
        setImage("images/test.jpg");
        GreenfootImage image = getImage();
        image.scale(32, 32);
        setImage(image);
    }
    
    public void act()
    {
        super.act();
        getWorld().showText("bounce: " + currentBounce, getWorld().getWidth()/2, 90);
        getWorld().showText("VelocityX: " + getVelocityX(), getWorld().getWidth()/2, 50);
        getWorld().showText("VelocityY: " + getVelocityY(), getWorld().getWidth()/2, 30);
        getWorld().showText("gravity: " + getGravity(), getWorld().getWidth()/2, 70);
        getWorld().showText("direction: " + getDirection(), getWorld().getWidth()/4, 70);
        checkWinCondition();
    }
    
    public void handleMovement() {
        handleHorizontalMovement();
        handleVerticalMovement();
    }
    
    public void handleHorizontalMovement() {
        if(Greenfoot.isKeyDown("left")) {
            acceleration = 1;
            setVelocityX(getVelocityX() - acceleration - currentBounce);
        }else if(Greenfoot.isKeyDown("right")) {
            acceleration = 1;
            setVelocityX(getVelocityX() + acceleration - currentBounce);
        } else{
            acceleration = 0;
            setVelocityX(-currentBounce);
        }
        
        if(getVelocityX() < -MOVEMENT_SPEED) {
            setVelocityX(-MOVEMENT_SPEED - currentBounce);
        } else if (getVelocityX() > MOVEMENT_SPEED) {
            setVelocityX(MOVEMENT_SPEED - currentBounce);
        }
        
        if(currentBounce < 0) {
            currentBounce += 2;
        } else if (currentBounce > 0) {
            currentBounce -= 2;
        }
    }
    
    public void handleVerticalMovement() { 
        if((terrainIsLeft() || terrainIsRight()) && isFalling()) {
            if(getDirection() == Direction.UP) {
                setVelocityY(-1);
            } else {
                setVelocityY(1);
            }
        }
        
        if(isJumping && !Greenfoot.isKeyDown("up")) {
            setGravity(heavyGravity);
            isJumping = false;
        }
        
        if(!isJumping && Greenfoot.isKeyDown("up")) {
            jump();
            isJumping = true;
        }

        if(isFalling()) {
            setGravity(baseGravity);
        }
    }
    
    public void jump() {
        if(isGrounded() || terrainIsLeft() || terrainIsRight()) {
            
            if(getDirection() == Direction.UP) {
                setVelocityY(JUMP_SPEED);
            } else {
                setVelocityY(-JUMP_SPEED);
            }
            
            if(terrainIsLeft()) {
                currentBounce = -MAX_BOUNCE;
            }else if(terrainIsRight()) {
                currentBounce = MAX_BOUNCE;
            }
            
        }
    }
    
    private void checkWinCondition() {
        Goal roomGoal = (Goal) getOneIntersectingObject(Goal.class);
        if(roomGoal != null) {
            LevelWorld world = (LevelWorld) getWorld();
            world.nextLevel();
        }
    }
}
