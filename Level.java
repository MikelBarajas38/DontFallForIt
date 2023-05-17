import greenfoot.World;
import greenfoot.GreenfootImage;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

public class Level  
{
    
    private static final int TILESIZE = 32;
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
        
        Iterator sectorIterator = room.getSectorList().listIterator();
        while(sectorIterator.hasNext()) {
            List<Integer> data = (List<Integer>) sectorIterator.next();
            GravitySector Sector = new GravitySector(Direction.getDirection(data.get(0)), convertXFromTiles(data.get(1)), convertYFromTiles(data.get(2)), data.get(3), data.get(4), TILESIZE);
            world.addObject(Sector, Sector.getDrawX(), Sector.getDrawY());
        }
        
        List<List<String>> tileMap = room.getTileMap();
        int offset = TILESIZE / 2;

        for(int i = 0; i < room.getHeight(); i++){

            int currentDrawY = convertYFromTiles(i) + offset;
            
            for(int j = 0; j < room.getWidth(); j++) {
                
                //TODO: tilefactory
                
                int currentDrawX = convertXFromTiles(j) + offset;
                
                if(tileMap.get(i).get(j).equals("1")) {
                    Terrain tile = new Terrain(TILESIZE);
                    world.addObject(tile, currentDrawX, currentDrawY);   
                }
                
            }

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
    
    private int convertXFromTiles(int x) {
        return TOPRIGHTX + x * TILESIZE;
    }
    
    private int convertYFromTiles(int y) {
        return TOPRIGHTY + y * TILESIZE;
    }
    
}
