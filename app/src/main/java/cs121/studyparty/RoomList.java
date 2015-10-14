package cs121.studyparty;

import java.util.ArrayList;

/**
 * Created by Loring Thomas on 10/4
 */
public class RoomList {

    public static ArrayList<Room> roomList = new ArrayList<> ();


    //Setters and Getters

    public void addRoomToList(Room toAdd) {
        roomList.add(toAdd);
    }

    public void removeRoomFromList(Room toRemove) {
        int indexToRemove = roomList.indexOf(toRemove);
        if(indexToRemove != -1) {
            roomList.remove(indexToRemove);
        }
        else {
            System.out.println("Room is not currently in list!");
        }
    }

}
