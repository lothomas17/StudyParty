/**
 * Created by Loring Thomas on 9/29/15. This is the user class, which will hold information about
 * the user that is checked into a room.
 */
package cs121.studyparty;


public class User {

    public String name_;
    public double timeFromLogin_;

    //Setters and Getters

    /**
     * Setter for the name field on the user class
     * @param name is the name to be set.
     */
    public void setName(String name) {
        name_ = name;
    }

    public String getName() {
        return name_;
    }




}
