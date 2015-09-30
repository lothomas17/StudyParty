package cs121.studyparty;

/**
 * Created by Loring Thomas on 9/29/15. This is the basic unit for the study room app!
 */
public class Room {

    //private fields for the room class
    private boolean isOccupied_;
    private String roomName_;
    private User[] occupants_;
    private String location_;

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

    /**
     * The setter for the location field on the class.
     * @param location is the location of the room (Building / Floor)
     */
    public void setLocation(String location) {
        location_ = location;
    }

    /**
     * The getter for the location field on the class
     * @return the location of the room. (Building / Floor)
     */
    public String getLocation() {
        return location_;
    }


}
