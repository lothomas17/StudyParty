package cs121.studyparty;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class that will be stored into Parse, and manages all of the rooms that we have.
 */
@ParseClassName("RoomList")
public class RoomList extends ParseObject{

    //private ArrayList<Room> rooms_ = new ArrayList<>();
    //private ArrayList<String> roomNames_ = new ArrayList<>();
    public static Room chosenRoom;
    //final Room sampleRoom = new Room("Shanahan 2475");



    public final List<Room> getRoom() {
        return getList("rooms_");
    }

    public void addRoom(Room toAdd) {
        List<Room> rooms = getList("rooms_");
        if (rooms == null)  {
            List<Room> newRoom = new ArrayList<>();
            newRoom.add(toAdd);
            put("rooms_", newRoom);
        }
        else {
            rooms.add(toAdd);
            put("rooms_", rooms);
        }

    }

    public final List<String> getRoomNames(){
        return getList("roomNames_");
    }

    public void removeRoomFromList(Room toRemove) {
        List<Room> rooms = getList("rooms_");
        int indexToRemove = rooms.indexOf(toRemove);
        if(indexToRemove != -1) {
            rooms.remove(indexToRemove);
            put("rooms_", rooms);
        }
        else {
            System.out.println("Room not in list");
        }
    }

    public final Room getRoomFromList(int i) {
        List<Room> rooms = getList("rooms_");
        return rooms.get(i);
    }

    public void initializeList(){
        //add room to roomList and room name to roomNameList
        final Room sampleRoom = new Room("Shanahan 2475");
        addRoom(sampleRoom);
        List<String> roomNames = getList("roomNames_");
        if (roomNames == null) {
            List<String> names = new ArrayList<>();
            names.add(sampleRoom.getRoomName() + sampleRoom.getBestTime());
            put("roomNames_", names);
        }
        else {
            roomNames.add(sampleRoom.getRoomName() + sampleRoom.getBestTime());
            put("roomNames_", roomNames);
        }

    }
}
