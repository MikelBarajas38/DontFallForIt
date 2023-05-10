import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Basic moves of a player's platform game
 */
public class Player extends Actor
{
    private final int DECELERATION = 1;
    private final int MAX_VELOCITY = 5;
    private final int GRAVITY = 1;
    private final int BOUNCE = 100;
    
    private long startTime;
    //private long lastTimePlatformIsTouched;// como en cada act se verificaba
    // terminaba saltando doble jeje
    private int velocityX;
    private int velocityY;
    private int bounceResistance;
    private long timeNoKeyPressed;
    
    
    Player(){
        startTime = System.currentTimeMillis();
        setImage("images/Player.jpg");
        GreenfootImage image = getImage();
        image.scale(50, 50);
        setImage(image);
        startTime = 0;
        velocityX = 0;
        velocityY = 0;
        timeNoKeyPressed = 0;
    }
    
    public void act()
    {
        fall();
        movePlayer();
    }
    
    public void fall(){
        setLocation(getX(),getY() + velocityY);
        boolean platform = isInPlatform();
        boolean moveLeft = canMoveLeft();
        boolean moveRight = canMoveRight();
        if(isPlatformAbove()){
            velocityY = 1;
            while(isPlatformAbove()){
                setLocation(getX(),getY()+1);
            }
        }else if(touchWall() && !platform){
            setLocation(getX(),getY()+1);
            velocityY = 0;
        }else if(platform) {
            velocityY = 0;
            while(isInPlatform() ){
                setLocation(getX(),getY()-1);
            }
            setLocation(getX(),getY()+1);
        }else
        velocityY += GRAVITY;
    }
    
    public void jump(){
        if(touchWall() || isInPlatform() /*|| 
        (System.currentTimeMillis()-lastTimePlatformIsTouched)/1000 < 0.2*/){
            if(touchWall()) jumpWall();
            velocityY = -20;
        }
    }
    
    public void jumpWall(){
        if(!canMoveLeft()) bounceResistance = BOUNCE;
        if(!canMoveRight()) bounceResistance = -BOUNCE;        
    }
    
    public boolean isInPlatform(){
        boolean isInPlatform = false;
        if(getY() > getWorld().getHeight() - 50) {
            //lastTimePlatformIsTouched = System.currentTimeMillis();
            isInPlatform = true; 
        } 
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        if(getOneObjectAtOffset(imageWidth / -2, imageHeight / 2, Platform.class) != null ||
            getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Platform.class) != null)
        {
            //lastTimePlatformIsTouched = System.currentTimeMillis();
            isInPlatform = true; 
        }  
        return isInPlatform;
    }
    
    public boolean isPlatformAbove(){
        boolean isPlatformAbove = false;
         
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        if(50 > getY()) isPlatformAbove = true;
        if(getOneObjectAtOffset(imageWidth / -2, imageHeight / -2, Platform.class) != null ||
            getOneObjectAtOffset(imageWidth / 2, imageHeight / -2, Platform.class) != null)
            isPlatformAbove = true;  
        return isPlatformAbove;
    }
    
    public boolean canMoveLeft(){
        boolean canMoveLeft = true;
         
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
        if(getOneObjectAtOffset(imageWidth /-2 - 5, imageHeight / -2, Platform.class) != null ||
            getOneObjectAtOffset(imageWidth /-2 - 5, imageHeight / 2 - 1, Platform.class) != null)
            canMoveLeft = false;  
        return canMoveLeft;
    }
    
    public boolean canMoveRight(){
        boolean canMoveRight = true;
         
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();

        if(getOneObjectAtOffset(imageWidth / 2 + 5, imageHeight / -2, Platform.class) != null ||
            getOneObjectAtOffset(imageWidth / 2 + 5, imageHeight / 2 - 1, Platform.class) != null)
            canMoveRight = false;  
        return canMoveRight;
    }
    
    public boolean touchWall(){
        return !canMoveRight() || !canMoveLeft();
    }
    
    private void movePlayer(){
        if(Greenfoot.isKeyDown("up")) jump();
        int x = getX();
        int bouncing = 0;
        
        World world = getWorld();
        world.showText("Velocity: "+velocityX,world.getWidth()/2, 90);
        world.showText("Bounce??: "+bounceResistance,world.getWidth()/2, 70);
        
        if(bounceResistance < 0) bouncing = -MAX_VELOCITY - 5;
        if(bounceResistance > 0) bouncing = MAX_VELOCITY + 5;
        
        if(Greenfoot.isKeyDown("left") && canMoveLeft()) {
            velocityX = -MAX_VELOCITY + bouncing;
        }else if(Greenfoot.isKeyDown("right") && canMoveRight()) {
            velocityX = MAX_VELOCITY + bouncing;
        }else{
            if(timeNoKeyPressed != 0){
                if(velocityX < 0){
                    if(canMoveLeft()){
                        velocityX += bouncing + DECELERATION*((System.currentTimeMillis() - timeNoKeyPressed)/1000.00);
                        if (velocityX > 0 ) velocityX = 0;
                    }else velocityX = 0;
                }else if (velocityX > 0 ){
                    if(canMoveRight()){
                        velocityX -= bouncing + DECELERATION*((System.currentTimeMillis() - timeNoKeyPressed)/1000.00);
                        if (velocityX < 0) velocityX = 0;      
                    }else velocityX = 0;
                }
                velocityX += bouncing;
            }else timeNoKeyPressed = System.currentTimeMillis();
        }
        
        if(bounceResistance < 0) bounceResistance += 2 * MAX_VELOCITY;
        if(bounceResistance > 0) bounceResistance -= 2 * MAX_VELOCITY;
        //if(bounceResistance >= -5 && bounceResistance <= 5) bounceResistance = 0;
        
        x += velocityX;
        setLocation(x,getY());
    }
}
