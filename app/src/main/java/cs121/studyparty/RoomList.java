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
    public static int chosenIndex;
    public static int enteredIndex;
    public static Room enteredRoom = new Room();
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

    public static boolean firstTime = false;
    public static String roomID;


    public final List<Room> getRoom() {
        return getList("rooms_");
    }

    public void setChosenRoom(Room chosenRoom){
        this.chosenRoom = chosenRoom;
    }

    public Room getChosenRoom(){
        return chosenRoom;
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

    public final void editRoomName(int index, String name){
        List<String> roomNames = getList("roomNames_");
        roomNames.set(index, name);

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
