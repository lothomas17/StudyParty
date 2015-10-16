package cs121.studyparty;

import java.util.ArrayList;

/**
 * Created by Loring Thomas on 9/29/15. This is the basic unit for the study room app!
 */
public class Room {

    //private fields for the room class
    private boolean isOccupied_ = false;
    private String roomName_;
    private ArrayList<User> occupants_ = new ArrayList<> ();
    private int numOccupants_ = 0;

    Room(String name) {
        setName(name);
    }

    //Setters and Getters for the Room Class

    /**
     * The setter for the isOccupied field
     * @param occupied determines if the room is occupied or not.
     */
    public void setOccupancy(boolean occupied) {
        isOccupied_ = occupied;
    }

    /**
     * The getter for the isOccupied field
     * @return the boolean for whether the room is occupied or not.
     */
    public boolean getOccupancy() {
        return isOccupied_;
    }

    /**
     * The setter for the roomName field
     * @param name is the name of the room
     */
    public void setName(String name) {
        roomName_ = name;
    }

    /**
     * The getter for the roomName field
     * @return the room name associated with the room
     */
    public String getRoomName() {
        return roomName_;
    }

    public ArrayList<User> getOccupants(){
        return occupants_;
    }

    public void setOccupants() {
        numOccupants_ = occupants_.size();
    }

    public int getNumOccupants() {
        return numOccupants_;
    }

    /**
     * A method that takes a user and adds them to the study party.
     * @param toAdd is the user that will be added to the list.
     */
    public void addUsertoParty(User toAdd) {
        occupants_.add(toAdd);
        if(!isOccupied_){
            isOccupied_ = true;
        }
    }

    /**
     * Removes the user passed in from the study party. If they are not a member, then a warning
     * is printed out
     * @param toRemove is the User to be removed.
     */
    public void removeUserfromParty(User toRemove) {
        //uses the built in indexOf method from ArrayList to find the right index
        int indexToRemove = occupants_.indexOf(toRemove);
        if(indexToRemove != -1) {
            occupants_.remove(indexToRemove);
        }
        else {
            //just prints out a warning if the user is not in the study party.
            System.out.println("The user specified is not in the study party.");
        }
        if(occupants_.size() == 0) {
            isOccupied_ = false;
        }

    }

    /**
     * A function that will return the longest time since someone checked into the study room
     * @return 0 if the room is empty, else the number of millis since check-in.
     */
    public long getBestTime() {
        //checks to see how many users are in the study party.
        int numUsers = occupants_.size();
        long[] checkInTimes = new long[numUsers];
        long bestTime = 999999999;
        //returns 0 if nobody is in the study party.
        if(numUsers == 0) {
            return -1;
        }
        //builds the array of times that each user checked in at.
        for(int i = 0; i < numUsers; ++i) {
            User userToCheck = occupants_.get(i);
            userToCheck.setTime();
            checkInTimes[i] = userToCheck.getTime();
        }
        //finds the maximum of the array.
        for(int j = 0; j < numUsers; ++j) {
            if(checkInTimes[j] < bestTime) {
                bestTime = checkInTimes[j];
            }
        }

        return bestTime;
    }

}
