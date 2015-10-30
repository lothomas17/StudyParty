package cs121.studyparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    public static String room;
    public RoomList roomListObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Room testRoom = new Room("ShanaHAN", 0);
        testRoom.setOccupancy(true);

        final RoomList testObject = new RoomList();
        testObject.put("batman", "ALFRED");
        testObject.addRoom(testRoom);
        testObject.saveInBackground(new SaveCallback() {

            public void done(ParseException e) {
                if (e == null) {
                    // Saved successfully.
                    Log.d("KEYKEY", "User update saved!");
                    String ID = testObject.getObjectId();
                    Log.d("KEYKEY", "The object id (from User) is: " + ID);
                } else {
                    // The save failed.
                    Log.d("KEYKEY", "User update error: " + e);
                }
            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String objectID = testObject.getObjectId();  // for some reason objectID comes out as null
        Log.d("KEYKEY", "bs " + objectID);  // need to figure out how to get ID we see in Parse dashboard

        ParseQuery<RoomList> query = ParseQuery.getQuery(RoomList.class);
        String name = testObject.getObjectId();
        Log.d("KEYKEY", "name is " + name);
        query.getInBackground(name, new GetCallback<RoomList>() {  // don't want to have to hardcode in objectID
                    public void done(RoomList object, ParseException e) {
                        if (e == null) {
                            Log.d("Crazyshit", "might actually be working");
                            String testFool = "hey " + testObject.getString("batman");  // pulls accurate information from the cloud!
                            Room didThisWork = object.getRoomFromList(0);
                            boolean YES = didThisWork.getOccupancy();
                            String YESSIR = String.valueOf(YES);
                            Log.d("Crazyshit", YESSIR);
                            object.removeRoomFromList(testRoom);  // we successfully stored a RoomList in the cloud!!
                            Log.d("Crazyshit", testFool);

                        } else {
                            Log.d("BADBAD", e.toString());
                        }
                    }
                });


        mainListView = (ListView) findViewById(R.id.main_listview);

            roomListObject = new RoomList();
            roomListObject.initializeList();

        //display room names in roomNames in the listview
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                roomListObject.getRoomNames());

        mainListView.setAdapter(mArrayAdapter);

        mainListView.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       room = (String) parent.getItemAtPosition(position);
       ArrayList<Room> roomList = roomListObject.getRoom();

        //set the room on the next screen to the room chosen by the user
        for (int i = 0; i < roomList.size(); i++){
            Room currentRoom = roomList.get(i);
            long time = currentRoom.getBestTime();
            String toCheck = currentRoom.getRoomName() + currentRoom.convertTimetoString(time);
            if ((toCheck).equals(room)){
                roomListObject.chosenRoom = currentRoom;
            }
        }

        // Log the item's position and contents
        // to the console in Debug
        Log.d("room", position + ": " + roomList.get(position));

        //switch screens when room is picked
        startActivity(new Intent(getApplicationContext(), JoinRoomActivity.class));
    }
}
