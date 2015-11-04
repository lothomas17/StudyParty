package cs121.studyparty;

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

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(getApplicationContext());
        ParseObject.registerSubclass(RoomList.class);
        Parse.initialize(this, "fixtsqaXWjGu2kiIPbpn9ssAm1mgHrFfXMyIQQNQ", "9yrXZ0JKvYjzR5ma77XTTpPQeO2nU6q6zWwMsdvW");

        final RoomList parseList = new RoomList();
        final Room testRoom = new Room("Shan 16");

        parseList.addRoom(testRoom);

        parseList.put("batman", "ALFRED");
        parseList.saveInBackground(new SaveCallback() {

            public void done(ParseException e) {
                if (e == null) {
                    // Saved successfully.
                    Log.d("KEYKEY", "User update saved!");
                    String ID = parseList.getObjectId();
                    Log.d("KEYKEY", "The object id (from User) is: " + ID);
                    idName = parseList.getObjectId();
                } else {
                    // The save failed.
                    Log.d("KEYKEY", "User update error: " + e);
                }
            }
        });

//        parseList.addRoom(testRoom);
//        parseList.initializeList();
//
//        parseList.saveInBackground();

    }

    public static String getIdName() {
        return idName;
    }

}
