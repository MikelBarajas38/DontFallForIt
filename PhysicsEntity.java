import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class PhysicsEntity extends Actor
{
    
    private static final int TERMINAL_VELOCITY = 16;

    private int x;
    private int y;
    
    private int velocityX;
    private int velocityY;
    
    private int gravity;
    private int gravityX;
    private int gravityY;
    
    private Direction currentDirection;
    
    public PhysicsEntity(int x, int y) {
        this.x = x;
        this.y = y;
        velocityX = 0;
        velocityY = 0;
        gravity = 1;
        setLocation(x,y);
    }
    
    public void act()
    {
        handleGravity();
        handleMovement();
        handleCollisions();
        setMovement();
        handleState();
    }
    
    public void handleGravity() {
        GravitySector currentSector = (GravitySector)getOneObjectAtOffset(0, getImage().getWidth() + velocityX, GravitySector.class);
        currentDirection = currentSector.getDirection();
        
        switch(currentDirection) {
            case UP:
                gravityX = 0;
                gravityY = -gravity;
                setRotation(180);
                break;
            
            case RIGHT:
                gravityX = gravity;
                gravityY = 0;
                break;
            
            case DOWN:
                gravityX = 0;
                gravityY = gravity;
                setRotation(0);
                break;
                
            case LEFT:
                gravityX = -gravity;
                gravityY = 0;
                break;

        }
    
        velocityY += gravityY;
        if(velocityY > TERMINAL_VELOCITY) {
            velocityY = TERMINAL_VELOCITY;
        } else if(velocityY < -TERMINAL_VELOCITY) {
            velocityY = -TERMINAL_VELOCITY;
        }
        
        /*
        velocityX += gravityX;
        if(velocityX > TERMINAL_VELOCITY) {
            velocityX = TERMINAL_VELOCITY;
        } else if(velocityX < -TERMINAL_VELOCITY) {
            velocityX = -TERMINAL_VELOCITY;
        }
        */
        
    }
    
    public abstract void handleMovement();
    
    public abstract void handleState();
    
    public void setMovement() {
        x += velocityX;
        y += velocityY;
        setLocation(x,y);
    }
    
    public void handleCollisions() {
        
        if(terrainIsDown() || terrainIsUp()) {
            velocityY = 0;
            verticalUnIntersect();
        }
        
        if(terrainIsRight() || terrainIsLeft()) {
            velocityX = 0;
            horizontalUnIntersect();
        }

    }
    
    public void verticalUnIntersect() {
        while(terrainIsUp() || terrainIsDown()) {
            if(terrainIsUp()) {
                y += 1;
            } else {
                y -= 1;
            }
            setLocation(x,y);
        }
    }
    
    public void horizontalUnIntersect() {
        while(terrainIsLeft() || terrainIsRight()) {
            if(terrainIsLeft()) {
                x += 1;
            } else {
                x -= 1;
            }
            setLocation(x,y);
        }
    }
    
    public boolean terrainIsUp(){
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        
        if(getOneObjectAtOffset(imageWidth / -2 + 4, imageHeight / -2 + velocityY, Terrain.class) != null ||
            getOneObjectAtOffset(imageWidth / 2 - 4, imageHeight / -2 + velocityY, Terrain.class) != null) {
                return true; 
            }            
            
        return false;
    }
    
    public boolean terrainIsDown(){
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        
        if(getOneObjectAtOffset(imageWidth / -2 + 4, imageHeight / 2 + velocityY, Terrain.class) != null ||
            getOneObjectAtOffset(imageWidth / 2  - 4, imageHeight / 2 + velocityY, Terrain.class) != null) {
                return true; 
            }            
            
        return false;
    }
    
    public boolean terrainIsLeft(){
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        
        if(getOneObjectAtOffset(imageWidth /-2 + velocityX, imageHeight / 2 - 4, Terrain.class) != null ||
            getOneObjectAtOffset(imageWidth /-2 + velocityX, imageHeight / -2  + 4, Terrain.class) != null) {
                return true; 
            }            
            
        return false;
    }
    
    public boolean terrainIsRight(){
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        
        if(getOneObjectAtOffset(imageWidth / 2 + velocityX, imageHeight / 2 - 4, Terrain.class) != null ||
            getOneObjectAtOffset(imageWidth / 2 + velocityX, imageHeight / -2  + 4, Terrain.class) != null) {
                return true; 
            }            
            
        return false;
    }
    
    public boolean isGrounded() {
        if(currentDirection == Direction.DOWN && terrainIsDown()) {
            return true;
        }
        
        if(currentDirection == Direction.UP && terrainIsUp()) {
            return true;
        }
        
        return false;
    } 
    
    public boolean isFalling() {
        if(currentDirection == Direction.DOWN && velocityY >= 0) {
            return true;
        }
        
        if(currentDirection == Direction.UP && velocityY <= 0) {
            return true;
        }
        
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setGravity(int gravity) {
         this.gravity = gravity;
    }
    
    public int getGravity() {
        return gravity;
    }
    
    public int getTerminalVelocity() {
        return TERMINAL_VELOCITY;
    }
    
    public Direction getDirection() {
        return currentDirection;
    }
    
}
