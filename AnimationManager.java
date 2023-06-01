import greenfoot.*;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.EnumMap;

public class AnimationManager  
{
    private PhysicsEntity entity;
    
    private EnumMap<State, List<GreenfootImage>> entitySprites = new EnumMap(State.class);
    private List<GreenfootImage> currentSpriteList;
    private Iterator<GreenfootImage> spriteIterator;
    
    private static final int FRAMERATE = 2;
    private int currentFrame = FRAMERATE;
    
    private Direction pastDirection;

    public AnimationManager(PhysicsEntity entity, String spritePath)
    {
        this.entity = entity;
        setSprites(spritePath);
    }
    
    public void nextSprite() {
        if(currentFrame >= FRAMERATE) {
            if(!spriteIterator.hasNext()) {
                spriteIterator = currentSpriteList.iterator();
            } 
            
            entity.setImage(new GreenfootImage(spriteIterator.next()));
            
            if(entity.getDirectionX() == Direction.LEFT) {
                entity.getImage().mirrorHorizontally();
            }
            
            if(entity.getDirectionY() == Direction.UP) {
                entity.getImage().mirrorVertically();
            }
            
            currentFrame = 0;
        } else {
            currentFrame++;
        }
    }
    
    public void changeSprite(State state) {
        currentSpriteList = entitySprites.get(state);
        spriteIterator = currentSpriteList.iterator();
    }
    
    private void setSprites(String spritePath) {
        for (State state : entity.getStates().keySet()) {

            File spriteFolder = new File(spritePath + state.getPath());
            File[] imgs = spriteFolder.listFiles();
            Arrays.sort(imgs);
            
            List<GreenfootImage> spriteList = new ArrayList<>();
            
            for (File img : imgs) {
                spriteList.add(new GreenfootImage(spritePath + state.getPath() + "/" + img.getName()));
            }
            
            entitySprites.put(state, spriteList);
        }
    }

    
}
