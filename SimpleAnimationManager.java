import greenfoot.*;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.EnumMap;

public class SimpleAnimationManager  
{
    private Actor actor;
    
    private List<GreenfootImage> currentSpriteList;
    private Iterator<GreenfootImage> spriteIterator;
    
    private static final int FRAMERATE = 4;
    private int currentFrame = FRAMERATE;

    public SimpleAnimationManager(Actor actor, String spritePath)
    {
        this.actor = actor;
        setSprites(spritePath);
    }
    
    public void nextSprite() {
        if(currentFrame >= FRAMERATE) {
            if(!spriteIterator.hasNext()) {
                spriteIterator = currentSpriteList.iterator();
            } 
            
            actor.setImage(new GreenfootImage(spriteIterator.next()));

            currentFrame = 0;
        } else {
            currentFrame++;
        }
        
    }

    private void setSprites(String spritePath) {
        File spriteFolder = new File(spritePath);
        File[] imgs = spriteFolder.listFiles();
        Arrays.sort(imgs);
        
        List<GreenfootImage> spriteList = new ArrayList<>();
        
        for (File img : imgs) {
            spriteList.add(new GreenfootImage(spritePath + "/" + img.getName()));
        }
        
        currentSpriteList = spriteList;
        spriteIterator = currentSpriteList.iterator();
    }

    
}
