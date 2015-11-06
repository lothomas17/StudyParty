package cs121.studyparty;

import java.util.ArrayList;

/**
 * Created by Loring Thomas on 9/29/15. This is the basic unit for the study room app!
 */
public class Room {

    //private fields for the room class
    private boolean isOccupied_ = false;
    private String roomName_;
    private int numOccupants_;
    private ArrayList<User> occupants_ = new ArrayList<> ();

    final static long TIMEOUT = 28800000;

    Room(String name){
        setName(name);
    }

    Room(String name, int numOccupants_) {
        setName(name);
        setNumOccupants(numOccupants_);
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
    public final boolean getOccupancy() {
        return isOccupied_;
    }

    /**
     * The getter for the numOccupants field
     * @return the number of occupants associated with the room
     */
    public final int getNumOccupants() {
        return numOccupants_;
    }

    public void setNumOccupants(int number) {
        numOccupants_ = number;
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
    public final String getRoomName() {
        return roomName_;
    }

    /**
     * Getter for the array of users.
     * @return the array of users occupants_
     */
    public final ArrayList<User> getOccupants(){
        return occupants_;
    }


    /**
     * A setter for the number of occupants in a room, grabbing the data from the array
     */
    public void setOccupants() {
        numOccupants_ = occupants_.size();
    }


    /**
     * Increases occupancy
     * @return the number of occupants increased by one
     */
    public final int incrementNumOccupants() {
        return ++numOccupants_;
    }

    /**
     * Decreases occupancy
     * @return the number of occupants decreased by one
     */
    public final int decrementNumOccupants() {
        return --numOccupants_;
    }

    /**
     * A method that takes a user and adds them to the study party.
     * @param toAdd is the user that will be added to the list.
     */
    public int addUsertoParty(User toAdd) {
        int duplicateCheck = occupants_.indexOf(toAdd);
        if(duplicateCheck != -1) {
            return -1;
        }
        occupants_.add(toAdd);
        if(!isOccupied_){
            isOccupied_ = true;
        }
        return 0;
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
        if(numOccupants_ == 0) {
            setOccupancy(false);
        }

    }

    /**
     * A function that will return the time since someone checked into the study room
     * @return 0 if the room is empty, else the number of millis since check-in.
     */
    public final String getBestTime() {
        //The human readable form of the bestTime
        String toReturn;

        //checks to see how many users are in the study party.
        int numUsers = getNumOccupants();
        long[] checkInTimes = new long[numUsers];
        long bestTime = TIMEOUT;
        //returns 0 if nobody is in the study party.
        if(numUsers == 0) {
            bestTime = -1;
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

        if(bestTime <= 60000) {   //1 minute in milliseconds
            toReturn = "\n" + "Unoccupied";
            return toReturn;
        }
        else {
            //converts the time into a human readable string, beginning with a newline.
            long tempTime = bestTime / 60000;
            long minutes = tempTime % 60;
            tempTime = tempTime / 60;
            long hours = tempTime % 60;
            toReturn = "\n" + hours + ":" + minutes;
            return toReturn;
        }
    }


}
