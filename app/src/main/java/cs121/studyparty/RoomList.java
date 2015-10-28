package cs121.studyparty;

import java.util.ArrayList;

/**
 * Created by loring on 10/15/15.
 */
public class RoomList {

    private ArrayList<Room> rooms_ = new ArrayList<>();
    private ArrayList<String> roomNames = new ArrayList<>();
    public static Room chosenRoom;
    Room sampleRoom = new Room("Shanahan 2475");

    public ArrayList<Room> getRoom() {
        return rooms_;
    }

    public void addRoom(Room toAdd) {
        rooms_.add(toAdd);
    }

    public ArrayList<String> getRoomNames(){
        return roomNames;
    }

    public void initializeList(){
        //add room to roomList and room name to roomNameList
        addRoom(sampleRoom);
        roomNames.add(sampleRoom.getRoomName());
    }
}
