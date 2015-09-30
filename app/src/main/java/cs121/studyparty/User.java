/**
 * Created by Loring Thomas on 9/29/15. This is the user class, which will hold information about
 * the user that is checked into a room.
 */
package cs121.studyparty;

import java.util.Date;

public class User {

    //fields for User, just a name and the time since the user logged in.
    private String name_;
    private long timeFromLogin_;
    private Date instantiatedAt_;

    User(String userName){
        setName(userName);

        //sets the time of instantiation, making it easier to computer time.
        long msTime = System.currentTimeMillis();
        instantiatedAt_ = new Date(msTime);
    }

    //Setters and Getters

    /**
     * Setter for the name field on the user class
     * @param name is the name to be set.
     */
    public void setName(String name) {
        name_ = name;
    }

    /**
     * Getter for the name field of the user class
     * @return the name of the user
     */
    public String getName() {
        return name_;
    }

    /**
     * Setter for time, following procedure from:
     * http://stackoverflow.com/questions/3352031/calculate-time-between-two-times-android
     */
    public void setTime() {
        //gets the current time, and subtracts the login time from it.
        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date(msTime);

        timeFromLogin_ = curDateTime.getTime() - instantiatedAt_.getTime();
    }

    /**
     * Getter for time field of the user class
     * @return the time since the user checked into the study room.
     */
    public double getTime() {
        return timeFromLogin_;
    }




}
