import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.FileReader;

import greenfoot.Actor;


public class Room  
{
    
    private List<List<String>> tileMap = new ArrayList<>();
    private List<List<Integer>> sectorList = new ArrayList<>();
    private List<List<Integer>> enemyList = new ArrayList<>();
    
    private int startingPositionX;
    private int startingPositionY;
    private int endingPositionX;
    private int endingPositionY;

    
    public Room(String roomFile) {
        readRoomFromFile(roomFile);
    }
    
    private void readRoomFromFile(String roomFile) {
        
        try(BufferedReader br = new BufferedReader(new FileReader(roomFile))) {
            
            String[] startingPositionVector = br.readLine().split(",");
            startingPositionX = Integer.parseInt(startingPositionVector[0]);
            startingPositionY = Integer.parseInt(startingPositionVector[1]);
            
            String[] endingPositionVector = br.readLine().split(",");
            endingPositionX = Integer.parseInt(endingPositionVector[0]);
            endingPositionY = Integer.parseInt(endingPositionVector[1]);
            
            int sectorCount = Integer.parseInt(br.readLine());
            for(int i = 0; i < sectorCount; i++) {
                List<Integer> sectorInfo = new ArrayList<>();
                String line = br.readLine();
                for(String info : line.split(",")) {
                    sectorInfo.add(Integer.parseInt(info));
                }
                sectorList.add(sectorInfo);
            }
            
            int enemyCount = Integer.parseInt(br.readLine());
            for(int i = 0; i < enemyCount; i++) {
                List<Integer> enemyInfo = new ArrayList<>();
                String line = br.readLine();
                for(String info : line.split(",")) {
                    enemyInfo.add(Integer.parseInt(info));
                }
                enemyList.add(enemyInfo);
            }
            
            int roomSize = Integer.parseInt(br.readLine());
            for(int i = 0; i < roomSize; i++) {
                String line = br.readLine();
                String []tiles = line.split(",");
                tileMap.add(Arrays.asList(tiles));
            }
            
        } catch(java.io.IOException e) {
            System.out.println("Error reading room file");
        }

    }
    
    public int getHeight() {
        return tileMap.size();
    }
    
    public int getWidth() {
        return tileMap.get(0).size();
    }
    
    public List<List<String>> getTileMap() {
        return tileMap;
    }
    
    public List<List<Integer>> getSectorList() {
        return sectorList;
    }
    
    public List<List<Integer>> getEnemyList() {
        return enemyList;
    }
    
    public int getStartingPositionX() {
        return startingPositionX;
    }
    
    public int getStartingPositionY() {
        return startingPositionY;
    }
    
    public int getEndingPositionX() {
        return endingPositionX;
    }
    
    public int getEndingPositionY() {
        return endingPositionY;
    }

    
}
