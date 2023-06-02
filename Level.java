import greenfoot.*;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

public class Level  
{
    
    private static final int TILESIZE = 16;
    private static final int TOPRIGHTX = 7 * TILESIZE;
    private static final int TOPRIGHTY = 8 * TILESIZE;

    private World world;
    private List<Room> roomList;
    private Iterator<Room> roomIterator;
    private Room currentRoom;
    private String playerPath;
    private String playerName;

    public Level(World world, String levelPath, String playerPath, String playerName)
    {
        this.world = world;
        roomList = new ArrayList<>();
        fillRoomList(levelPath);
        roomIterator = roomList.iterator();
        this.playerPath = playerPath;
        this.playerName = playerName;
        buildNextRoom();
    }
    
    public void buildNextRoom() {
        if(roomIterator.hasNext()) {
            destroyCurrentRoom();
            currentRoom = roomIterator.next();
            buildRoom(currentRoom);
        }
    }
    
    public void rebuildRoom(){
        destroyCurrentRoom();
        buildRoom(currentRoom);
    }
    
    public boolean isLastRoom() {
        return currentRoom == roomList.get(roomList.size() - 1);
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
                    TileType type = TileType.getTileType(tileMap.get(i).get(j));
                    Tile tile = TileFactory.getTile(type, tileMap, j, i, TILESIZE);
                    world.addObject(tile, currentDrawX, currentDrawY);   
                }
                
            }

        }
        
        Iterator enemyIterator = room.getEnemyList().iterator();
        while(enemyIterator.hasNext()) {
            List<Integer> data = (List<Integer>) enemyIterator.next();
            int enemyX = convertXFromTiles(data.get(1)) - TILESIZE/2;
            int enemyY = convertXFromTiles(data.get(2)) - TILESIZE/2;
            Enemy enemy = EnemyFactory.getEnemy(EnemyType.getEnemyType(data.get(0)), enemyX, enemyY, getClosestTile(tileMap, data.get(1), data.get(2)));
            world.addObject(enemy, enemyX, enemyY);
        }
        
        Iterator coinIterator = room.getCoinList().iterator();
        while(coinIterator.hasNext()) {
            List<Integer> data = (List<Integer>) coinIterator.next();
            int coinX = convertXFromTiles(data.get(0)) - TILESIZE/2;
            int coinY = convertXFromTiles(data.get(1)) - TILESIZE/2;
            boolean gravity = data.get(2) == 1;
            Coin coin = new Coin(coinX, coinY, gravity);
            world.addObject(coin, coinX, coinY);
        }
        
        int goalX = convertXFromTiles(room.getEndingPositionX()) - 32;
        int goalY = convertYFromTiles(room.getEndingPositionY()) - 32;
        
        Goal goal;
        if(isLastRoom()){
            goal = new FinalGoal(goalX, goalY, getClosestTile(tileMap, room.getEndingPositionX(), room.getEndingPositionY()));            
        } else {
            goal = new MiddleGoal(goalX, goalY, getClosestTile(tileMap, room.getEndingPositionX(), room.getEndingPositionY()));
        }
        
        world.addObject(goal, goalX, goalY);
        
        int bigCoinX = convertXFromTiles(room.getBigCoinPositionX()) - TILESIZE/2;
        int bigCoinY = convertYFromTiles(room.getBigCoinPositionY()) - TILESIZE/2;
        BigCoin bigCoin = new BigCoin(bigCoinX, bigCoinY);
        world.addObject(bigCoin, bigCoinX, bigCoinY);
        
        int playerX = convertXFromTiles(room.getStartingPositionX()) - TILESIZE/2;
        int playerY = convertYFromTiles(room.getStartingPositionY()) - TILESIZE/2;
        Player player = new Player(playerX, playerY,playerPath,playerName);
        world.addObject(player, playerX, playerY);

    }
    
    private static int convertXFromTiles(int x) {
        return TOPRIGHTX + x * TILESIZE;
    }
    
    private static int convertYFromTiles(int y) {
        return TOPRIGHTY + y * TILESIZE;
    }
    
    private Direction getClosestTile(List<List<String>> tileMap, int x, int y){
        x--;
        y--;
        int distanceTop = getDistanceToTile(tileMap, x, y, 0, -1);
        int distanceDown = getDistanceToTile(tileMap, x, y, 0, 1);
        int distanceLeft = getDistanceToTile(tileMap, x, y, -1, 0);
        int distanceRight = getDistanceToTile(tileMap, x, y, +1, 0);
        
        int minVerticalDistance = Math.min(distanceTop, distanceDown);
        int minHorizontalDistance = Math.min(distanceLeft, distanceRight);
        
        if(minVerticalDistance <= minHorizontalDistance) {
            if(minVerticalDistance == distanceTop) {
                return Direction.DOWN;
            } else {
                return Direction.UP;
            }
        } else {
            if(minHorizontalDistance == distanceLeft) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        }
        
    }
    
    private int getDistanceToTile(List<List<String>> tileMap, int x, int y, int offsetX, int offsetY) {
        int distance = 0;
        while(tileMap.get(y).get(x).equals("0")) {
            x += offsetX;
            y += offsetY;
            distance++;
        }
        return distance;
    }
    
}
