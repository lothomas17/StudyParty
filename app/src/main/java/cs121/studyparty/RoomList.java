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
    Room room2 = new Room("Shanahan 2465");
    Room room3 = new Room("Shanahan 2460");
    Room room4 = new Room("Shanahan 2454");
    Room room5 = new Room("Shanahan 2450");
    Room room6 = new Room("Shanahan 1480");
    Room room7 = new Room("Shanahan B470");
    Room room8 = new Room("Shanahan B450");
    public long time = sampleRoom.getBestTime();
    public long time2 = room2.getBestTime();
    public long time3 = room3.getBestTime();
    public long time4 = room4.getBestTime();
    public long time5 = room5.getBestTime();
    public long time6 = room6.getBestTime();
    public long time7 = room7.getBestTime();
    public long time8 = room8.getBestTime();


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
        addRoom(room8);
        addRoom(room7);
        addRoom(room6);
        addRoom(room5);
        addRoom(room4);
        addRoom(room3);
        addRoom(room2);
        addRoom(sampleRoom);
        roomNames.add(room8.getRoomName() + room8.convertTimetoString(time8));
        roomNames.add(room7.getRoomName() + room7.convertTimetoString(time7));
        roomNames.add(room6.getRoomName() + room6.convertTimetoString(time6));
        roomNames.add(room5.getRoomName() + room5.convertTimetoString(time5));
        roomNames.add(room4.getRoomName() + room4.convertTimetoString(time4));
        roomNames.add(room3.getRoomName() + room3.convertTimetoString(time3));
        roomNames.add(room2.getRoomName() + room2.convertTimetoString(time2));
        roomNames.add(sampleRoom.getRoomName() + sampleRoom.convertTimetoString(time));
    }
}
