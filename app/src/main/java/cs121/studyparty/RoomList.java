package cs121.studyparty;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by Loring Thomas on 10/4
 */
@ParseClassName("RoomList")
public class RoomList extends ParseObject {

    private ArrayList<Room> roomList = new ArrayList<> ();


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

    public Room getRoomFromList(int i) {
        return roomList.get(i);
    }

}

