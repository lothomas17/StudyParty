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

    public void setBool(String field, Boolean value) {
        put(field, value);
        this.saveInBackground();
    }

    public void setString(String field, String value) {
        put(field, value);
        this.saveInBackground();
    }

    public void setInt(String field, Integer value) {
        put(field, value);
        this.saveInBackground();
    }

    public void setDate(String field, Date value) {
        put(field, value);
        this.saveInBackground();
    }

    public void setLong(String field, Long value) {
        put(field, value);
        this.saveInBackground();
    }


    public User() {
        setString("id_", "NO ID");
        setBool("inRoom_", false);
        long msTime = System.currentTimeMillis();
        Date instantiatedAt = new Date(msTime);
        setDate("instantiatedAt", instantiatedAt);


        setInt("timeFromLogin_", 0);
    }

    User(String userName) {

        setString("id_", userName);
        setBool("inRoom_", false);

        //sets the time of instantiation, making it easier to compute time.
        long msTime = System.currentTimeMillis();
        Date instantiatedAt = new Date(msTime);
        setDate("instantiatedAt", instantiatedAt);


        setInt("timeFromLogin_", 0);
    }

    //Setters and Getters

    public final boolean isInRoom_() {
        Boolean inRoom = getBoolean("inRoom_");
        if (inRoom == null) {
            setBool("inRoom_", false);
        }
        return getBoolean("inRoom_");

    }

    public final void joinRoom(){
        setBool("inRoom_", true);
    }

    public final void leaveRoom(){
        setBool("inRoom_", false);
    }


    /**
     * Setter for the name field on the user class
     * @param name is the name to be set.
     */

    public void setID(String name) {
        setString("id_", name);
    }

    /**
     * Getter for the name field of the user class
     * @return the name of the user
     */
    public final String getID() {
        String id = getString("id_");
        if (id == null) {
            setString("id_", "NO ID");
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
        setLong("timeFromLogin_", timeDiff);
    }

    /**
     * Getter for time field of the user class
     * @return the time since the user checked into the study room.
     */
    public final long getTime() {
        return getLong("timeFromLogin_");
    }




}
