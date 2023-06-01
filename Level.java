import greenfoot.*;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

public class Level  
{
    
    private static final int TILESIZE = 16;
    private static final int TOPRIGHTX = 3 * TILESIZE;
    private static final int TOPRIGHTY = 4 * TILESIZE;

    World world;
    List<Room> roomList;
    Iterator<Room> roomIterator;

    public Level(World world, String levelPath)
    {
        this.world = world;
        roomList = new ArrayList<>();
        fillRoomList(levelPath);
        roomIterator = roomList.iterator();
        buildNextRoom();
    }
    
    public void buildNextRoom() {
        if(roomIterator.hasNext()) {
            destroyCurrentRoom();
            Room newRoom = roomIterator.next();
            buildRoom(newRoom);
        }
    }
    
    private void destroyCurrentRoom() {
            world.removeObjects(world.getObjects(PhysicsEntity.class));
            world.removeObjects(world.getObjects(Sector.class));
            world.removeObjects(world.getObjects(Tile.class));
            world.removeObjects(world.getObjects(Goal.class));  
    }
    
    private void fillRoomList(String levelPath) {
        
        File roomFolder = new File(levelPath);
        File[] rooms = roomFolder.listFiles();
        Arrays.sort(rooms);
        
        for (File currentRoom : rooms) {
            roomList.add(new Room(levelPath + "/" + currentRoom.getName()));
        }
        
    }
    
    private void buildRoom(Room room) {
        
        Iterator sectorIterator = room.getSectorList().iterator();
        while(sectorIterator.hasNext()) {
            List<Integer> data = (List<Integer>) sectorIterator.next();
            GravitySector sector = new GravitySector(Direction.getDirection(data.get(0)), convertXFromTiles(data.get(1)), convertYFromTiles(data.get(2)), data.get(3), data.get(4), TILESIZE);
            world.addObject(sector, sector.getDrawX(), sector.getDrawY());
        }
        
        List<List<String>> tileMap = room.getTileMap();
        int offset = TILESIZE / 2;

        for(int i = 0; i < room.getHeight(); i++){

            int currentDrawY = convertYFromTiles(i) + offset;
            
            for(int j = 0; j < room.getWidth(); j++) {
                
                int currentDrawX = convertXFromTiles(j) + offset;
                
                if(!tileMap.get(i).get(j).equals("0")) {
                    TerrainType type = TerrainType.getTerrainType(tileMap.get(i).get(j));
                    
                    TilePosition position;
                    if(type.isBorder()){
                        position = getBorderPosition(tileMap, j, i);
                    } else {
                        position = getTerrainPosition(tileMap, j, i);   
                    }
                    
                    Terrain tile = new Terrain(type, position, TILESIZE);
                    world.addObject(tile, currentDrawX, currentDrawY);   
                }
                
            }

        }
        
        Iterator enemyIterator = room.getEnemyList().iterator();
        while(enemyIterator.hasNext()) {
            List<Integer> data = (List<Integer>) enemyIterator.next();
            int enemyX = convertXFromTiles(data.get(1)) - TILESIZE/2;
            int enemyY = convertXFromTiles(data.get(2)) - TILESIZE/2;
            Enemy enemy = EnemyFactory.getEnemy(EnemyType.getEnemyType(data.get(0)), enemyX, enemyY);
            world.addObject(enemy, enemyX, enemyY);
        }
        
        int goalX = convertXFromTiles(room.getEndingPositionX()) - TILESIZE/2;
        int goalY = convertYFromTiles(room.getEndingPositionY()) - TILESIZE/2;
        Goal goal = new Goal(goalX, goalY);
        world.addObject(goal, goalX, goalY);
        
        int playerX = convertXFromTiles(room.getStartingPositionX()) - TILESIZE/2;
        int playerY = convertYFromTiles(room.getStartingPositionY()) - TILESIZE/2;
        Player player = new Player(playerX, playerY);
        world.addObject(player, playerX, playerY);

    }
    
    private static int convertXFromTiles(int x) {
        return TOPRIGHTX + x * TILESIZE;
    }
    
    private static int convertYFromTiles(int y) {
        return TOPRIGHTY + y * TILESIZE;
    }
    
    public static TilePosition getBorderPosition(List<List<String>> tileMap, int x, int y) {
        boolean isTopValid = isValidTilePosition(tileMap, x, y-1) && isSameTileType(tileMap, x, y, x, y-1); 
        boolean isDownValid = isValidTilePosition(tileMap, x, y+1) && isSameTileType(tileMap, x, y, x, y+1); 
        boolean isLeftValid = isValidTilePosition(tileMap, x-1, y) && isSameTileType(tileMap, x, y, x-1, y); 
        boolean isRightValid = isValidTilePosition(tileMap, x+1, y) && isSameTileType(tileMap, x, y, x+1, y); 
        
        if(isTopValid && isDownValid) {
            if(isValidTilePosition(tileMap, x-1, y)){
                return TilePosition.CENTER_LEFT;
            } else {
                return TilePosition.CENTER_RIGHT;
            }
        }
        
        if(isLeftValid && isRightValid) {
            if(isValidTilePosition(tileMap, x, y-1)){
                return TilePosition.BOTTOM_MID;
            } else {
                return TilePosition.TOP_MID;
            }
        }
        
        if(isDownValid && isRightValid) {
            return TilePosition.TOP_LEFT;
        }
        
        if(isDownValid && isLeftValid) {
            return TilePosition.TOP_RIGHT;
        }
        
        if(isTopValid && isRightValid) {
            return TilePosition.BOTTOM_LEFT;
        }
        
        if(isTopValid && isLeftValid) {
            return TilePosition.BOTTOM_RIGHT;
        }
        
        return TilePosition.TOP_MID;
    }
    
    public static TilePosition getTerrainPosition(List<List<String>> tileMap, int x, int y) {
        boolean isTopValid = isValidTilePosition(tileMap, x, y-1) && isSameTileType(tileMap, x, y, x, y-1); 
        boolean isDownValid = isValidTilePosition(tileMap, x, y+1) && isSameTileType(tileMap, x, y, x, y+1); 
        boolean isLeftValid = isValidTilePosition(tileMap, x-1, y) && isSameTileType(tileMap, x, y, x-1, y); 
        boolean isRightValid = isValidTilePosition(tileMap, x+1, y) && isSameTileType(tileMap, x, y, x+1, y); 
        
        if(isTopValid && isDownValid && isLeftValid && isRightValid) {
            return TilePosition.CENTER_MID;
        }
        
        if(isDownValid && isLeftValid && isRightValid) {
            return TilePosition.TOP_MID;
        }
        
        if(isTopValid && isLeftValid && isRightValid) {
            return TilePosition.BOTTOM_MID;
        }
        
        if(isTopValid && isDownValid && isLeftValid) {
            return TilePosition.CENTER_RIGHT;
        }
        
        if(isTopValid && isDownValid && isRightValid) {
            return TilePosition.CENTER_LEFT;
        }
        
        if(isDownValid && isRightValid) {
            return TilePosition.TOP_LEFT;
        }
        
        if(isDownValid && isLeftValid) {
            return TilePosition.TOP_RIGHT;
        }
        
        if(isTopValid && isRightValid) {
            return TilePosition.BOTTOM_LEFT;
        }
        
        if(isTopValid && isLeftValid) {
            return TilePosition.BOTTOM_RIGHT;
        }
        
        return TilePosition.BOTTOM_LEFT;
    }
    
    private static boolean isValidTilePosition(List<List<String>> tileMap, int x, int y) {
        return (x >= 0) && (x < tileMap.get(0).size()) && (y >= 0) && (y < tileMap.size());
    }
    
    public static boolean isSameTileType(List<List<String>> tileMap, int x1, int y1, int x2, int y2){
        return tileMap.get(y1).get(x1).equals(tileMap.get(y2).get(x2));
    }
    
}
