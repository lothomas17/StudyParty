/**
 * Created by Loring Thomas on 9/29/15. This is the user class, which will hold information about
 * the user that is checked into a room.
 */
package cs121.studyparty;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("User")
public class User extends ParseObject{

    //fields for User, just a name and the time since the user logged in.
    //private String id_;
    //private boolean inRoom_ = false;
    //private long timeFromLogin_;
    //private Date instantiatedAt;

    public User() {
        //put("id_", "NO ID");
        //put("inRoom_", false);
        //long msTime = System.currentTimeMillis();
        //Date instantiatedAt = new Date(msTime);
        //put("instantiatedAt", instantiatedAt);


        //put("timeFromLogin_", 0);
    }

    User(String userName){

        put("id_", userName);
        put("inRoom_", false);

        //sets the time of instantiation, making it easier to compute time.
        long msTime = System.currentTimeMillis();
        Date instantiatedAt = new Date(msTime);
        put("instantiatedAt", instantiatedAt);


        put("timeFromLogin_", 0);
    }

    //Setters and Getters

    public final boolean isInRoom_() {
        Boolean inRoom = getBoolean("inRoom_");
        if (inRoom == null) {
            put("inRoom_", false);
        }
        return getBoolean("inRoom_");

    }

    public final void joinRoom(){
        put("inRoom_", true);
    }

    public final void leaveRoom(){
        put("inRoom_", false);
    }

    /**
     * Setter for the name field on the user class
     * @param name is the name to be set.
     */
    public void setID(String name) {
        put("id_", name);
    }

    /**
     * Getter for the name field of the user class
     * @return the name of the user
     */
    public final String getID() {
        String id = getString("id_");
        if (id == null) {
            put("id_", "NO ID");
        }
        return getString("id_");
    }

    /**
     * Setter for time, following procedure from:
     * http://stackoverflow.com/questions/3352031/calculate-time-between-two-times-android
     */
    public void setTime() {
        //gets the current time, and subtracts the login time from it.
        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date(msTime);
        Date createdDate = getDate("instantiatedAt");
        if (createdDate == null) {
            createdDate = new Date(msTime);
        }
        long createdTime = createdDate.getTime();
        long timeDiff = (curDateTime.getTime() - createdTime);
        put("timeFromLogin_", timeDiff);
    }

    /**
     * Getter for time field of the user class
     * @return the time since the user checked into the study room.
     */
    public final long getTime() {
        return getLong("timeFromLogin_");
    }




}
