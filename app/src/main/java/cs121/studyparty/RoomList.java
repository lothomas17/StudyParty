package cs121.studyparty;

import java.util.ArrayList;

/**
 * This is the class that will be stored into Parse, and manages all of the rooms that we have.
 */
public class RoomList {

    private ArrayList<Room> rooms_ = new ArrayList<>();
    private ArrayList<String> roomNames = new ArrayList<>();
    public static Room chosenRoom;
    Room sampleRoom = new Room("Shanahan 2475");
    public long time = sampleRoom.getBestTime();


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
        roomNames.add(sampleRoom.getRoomName() + sampleRoom.convertTimetoString(time));
    }
}
