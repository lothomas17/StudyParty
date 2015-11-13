package cs121.studyparty;

import android.content.Context;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by navadallal on 10/3/15.
 */

public class Application extends android.app.Application {

    public static String idName;

    public static boolean isSet;

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("KEYKEY", "Application runs first!");

        sContext = getApplicationContext();
        Parse.enableLocalDatastore(getApplicationContext());
        ParseObject.registerSubclass(RoomList.class);
        ParseObject.registerSubclass(Room.class);
        ParseObject.registerSubclass(User.class);
        Parse.initialize(this, "fixtsqaXWjGu2kiIPbpn9ssAm1mgHrFfXMyIQQNQ", "9yrXZ0JKvYjzR5ma77XTTpPQeO2nU6q6zWwMsdvW");

        final RoomList parseList = new RoomList();
        final Room testRoom = new Room();
        testRoom.setOccupancy(true);
        testRoom.setName("TESTEST");
        parseList.addRoom(testRoom);


        //parseList.put("batman", "ALFRED");
        Log.d("KEYKEY", "still running first");
        parseList.saveInBackground(new SaveCallback() {

            public void done(ParseException e) {
                if (e == null) {
                    // Saved successfully.
                    Log.d("KEYKEY", "User update saved!");
                    String ID = parseList.getObjectId();
                    Log.d("KEYKEY", "The object id (from User) is: " + ID);
                    parseList.setObjectId(ID);
                    try {
                        Log.d("Sleepy", "I'm sleeping now");
                        Thread.sleep(5000);
                    } catch (InterruptedException f) {
                        // TODO Auto-generated catch block
                        f.printStackTrace();
                    }

                    idName = parseList.getObjectId();
                    Log.d("KEYKEY", idName + " idName saves correctly");
                } else {
                    // The save failed.
                    Log.d("KEYKEY", "User update error: " + e);
                }
            }

        });

        isSet = true;




//        parseList.addRoom(testRoom);
//        parseList.initializeList();
//
//        parseList.saveInBackground();

    }

    public static String getIdName() {
        Log.d("KEYKEY", "getIDNAME called");
        return idName;
    }

    public static Context getContext() {
        return sContext;
    }

}



