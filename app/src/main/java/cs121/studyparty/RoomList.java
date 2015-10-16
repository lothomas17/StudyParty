package cs121.studyparty;

import java.util.ArrayList;

/**
 * Created by loring on 10/15/15.
 */
public class RoomList {

    private ArrayList<Room> rooms_ = new ArrayList<>();
    private String[] names;

    public ArrayList<Room> getRoom() {
        return rooms_;
    }

    public void addRoom(Room toAdd) {
        rooms_.add(toAdd);
    }
}
