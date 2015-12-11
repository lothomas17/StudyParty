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
    public static int chosenIndex = -1;
    public static int enteredIndex = -1;
    public static Room enteredRoom = new Room();
    public static boolean firstTime = false;
    public static String roomID = "0Org7CnmpL";

    final Room sampleRoom = new Room("Shanahan 2475");
    Room room2 = new Room("Shanahan 2465");
    Room room3 = new Room("Shanahan 2460");
    Room room4 = new Room("Shanahan 2454");
    Room room5 = new Room("Shanahan 2450");
    Room room6 = new Room("Shanahan 1480");
    Room room7 = new Room("Shanahan B470");
    Room room8 = new Room("Shanahan B450");
    public String time = sampleRoom.getBestTime();
    public String time2 = room2.getBestTime();
    public String time3 = room3.getBestTime();
    public String time4 = room4.getBestTime();
    public String time5 = room5.getBestTime();
    public String time6 = room6.getBestTime();
    public String time7 = room7.getBestTime();
    public String time8 = room8.getBestTime();

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

    /**
     * Initializes our RoomList with values for Rooms in Shanahan and their times.
     */
    public void initializeList(){
        //add room to roomList and room name to roomNameList
        addRoom(room8);
        addRoom(room7);
        addRoom(room6);
        addRoom(room5);
        addRoom(room4);
        addRoom(room3);
        addRoom(room2);
        addRoom(sampleRoom);

        List<String> roomNames = getList("roomNames_");
        if (roomNames == null) {
            List<String> names = new ArrayList<>();
            names.add(room8.getRoomName() + time8);
            names.add(room7.getRoomName() + time7);
            names.add(room6.getRoomName() + time6);
            names.add(room5.getRoomName() + time5);
            names.add(room4.getRoomName() + time4);
            names.add(room3.getRoomName() + time3);
            names.add(room2.getRoomName() + time2);
            names.add(sampleRoom.getRoomName() +time);
            put("roomNames_", names);
        }
        else {
            roomNames.add(room8.getRoomName() + time8);
            roomNames.add(room7.getRoomName() + time7);
            roomNames.add(room6.getRoomName() + time6);
            roomNames.add(room5.getRoomName() + time5);
            roomNames.add(room4.getRoomName() + time4);
            roomNames.add(room3.getRoomName() + time3);
            roomNames.add(room2.getRoomName() + time2);
            roomNames.add(sampleRoom.getRoomName() +time);
            put("roomNames_", roomNames);
        }

    }
}
