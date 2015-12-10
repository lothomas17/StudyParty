package cs121.studyparty;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class that will be stored into Parse, and manages all of the rooms that we have.
 */
@ParseClassName("RoomList")
public class RoomList extends ParseObject{

    public static Room chosenRoom = new Room();
    public static int chosenIndex = 0;
    public static int enteredIndex = -1;
    public static Room enteredRoom = new Room();
    public static boolean firstTime = false;
    public static String roomID = "0Org7CnmpL";

    /**
     * Returns a list of rooms that are stored in the roomlist
     * @return a list of rooms from Parse
     */
    public final List<Room> getRoom() {
        try {
            List<Room> rooms = fetchIfNeeded().getList("rooms_");
            return rooms;
        }
        catch (ParseException e) {
            List<Room> rooms = new ArrayList<>();
            return rooms;
        }
    }

    /**
     * A method that sets which room needs to be given to the UI
     * @param chosenRoom is the room to be passed
     */
    public void setChosenRoom(Room chosenRoom){
        this.chosenRoom = chosenRoom;
    }

    /**
     * A method that will return a specific room from the list
     * @return the ChosenRoom
     */
    public Room getChosenRoom(){
        return chosenRoom;
    }

    /**
     * This method will add a room to the RoomList
     * @param toAdd is the room to be added
     */
    public void addRoom(Room toAdd) {
        try {
            List<Room> rooms = fetchIfNeeded().getList("rooms_");
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
        catch (ParseException e) {
            Log.d("BADBAD", "couldnt get list of rooms to add a room");
        }

    }

    /**
     * A method that will return a list of names from the list of rooms
     * @return a list of room names
     */
    public final List<String> getRoomNames(){
        try {
            List<String> rooms = fetchIfNeeded().getList("roomNames_");
            return rooms;
        }
        catch (ParseException e) {
            List<String> rooms = new ArrayList<>();
            return rooms;
        }
    }
    

    /**
     * A method to remove a Room from a RoomList - probably only in emergencies
     * @param toRemove is the room to be removed from the list.
     */
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

    /**
     * Getter for a room out of the list of rooms
     * @param i is the index of the room in the list
     * @return the room object from the list
     */
    public final Room getRoomFromList(int i) {
        try {
            List<Room> rooms = fetchIfNeeded().getList("rooms_");
            return rooms.get(i);
        }
        catch (ParseException e) {
            Room room = new Room();
            room.setName("NO NAME");
            room.setNumOccupants(0);
            return room;
        }
    }

}
