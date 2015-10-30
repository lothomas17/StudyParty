package cs121.studyparty;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by navadallal on 10/3/15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(getApplicationContext());
        ParseObject.registerSubclass(RoomList.class);
        Parse.initialize(this, "fixtsqaXWjGu2kiIPbpn9ssAm1mgHrFfXMyIQQNQ", "9yrXZ0JKvYjzR5ma77XTTpPQeO2nU6q6zWwMsdvW");

        

    }

}
