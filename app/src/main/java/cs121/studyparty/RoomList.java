package cs121.studyparty;

import java.util.ArrayList;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * This is the class that will be stored into Parse, and manages all of the rooms that we have.
 */
@ParseClassName("RoomList")
public class RoomList extends ParseObject{

    private ArrayList<Room> rooms_ = new ArrayList<>();
    private ArrayList<String> roomNames = new ArrayList<>();
    public static Room chosenRoom;
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



    public final ArrayList<Room> getRoom() {
        return rooms_;
    }

    public void addRoom(Room toAdd) {
        rooms_.add(toAdd);
    }

    public final ArrayList<String> getRoomNames(){
        return roomNames;
    }

    public void removeRoomFromList(Room toRemove) {
        int indexToRemove = rooms_.indexOf(toRemove);
        if(indexToRemove != -1) {
            rooms_.remove(indexToRemove);
        }
        else {
            System.out.println("Room not in list");
        }
    }

    public final Room getRoomFromList(int i) {
        return rooms_.get(i);
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
        roomNames.add(room8.getRoomName() + time8);
        roomNames.add(room7.getRoomName() + time7);
        roomNames.add(room6.getRoomName() + time6);
        roomNames.add(room5.getRoomName() + time5);
        roomNames.add(room4.getRoomName() + time4);
        roomNames.add(room3.getRoomName() + time3);
        roomNames.add(room2.getRoomName() + time2);
        roomNames.add(sampleRoom.getRoomName() +time);
    }
}
