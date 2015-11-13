package cs121.studyparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    public static String room;
    public RoomList roomListObject;
    EditText inputSearch;

    final static String OBJECTID = "qeYYWl1Y61";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Room testRoom = new Room("ShanaHAN", 0);
        testRoom.setOccupancy(true);

        final RoomList testObject = new RoomList();


        try {
            Log.d("Sleepy", "I'm sleeping now");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String objectID = Application.getIdName();  // for some reason objectID comes out as null
        Log.d("KEYKEY", "bs " + objectID);  // need to figure out how to get ID we see in Parse dashboard

        ParseQuery<RoomList> query = ParseQuery.getQuery(RoomList.class);
        Log.d("KEYKEY", "name is " + Application.getIdName());

        query.getInBackground(OBJECTID, new GetCallback<RoomList>() {  // don't want to have to hardcode in objectID
            public void done(RoomList object, ParseException e) {
                if (e == null) {
                    if (object == null) {
                        Log.d("Broken", "Everything is broken!");
                    }
                    object.addRoom(testRoom);
                    final RoomList listToUse = ParseObject.createWithoutData(RoomList.class, "qeYYWl1Y61");
                    Log.d("Crazyshit", "I done goofed");
                    Room didThisWork = listToUse.getRoomFromList(0);
                    boolean YES = didThisWork.getOccupancy();
                    String YESSIR = String.valueOf(YES);
                    Log.d("Crazyshit", YESSIR);

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
                R.layout.mytextview,R.id.tv,
                roomListObject.getRoomNames());

        mainListView.setAdapter(mArrayAdapter);

        mainListView.setOnItemClickListener(this);

        inputSearch = (EditText) findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changes the text
                MainActivity.this.mArrayAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

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
            Room currentRoom = roomListObject.getRoomFromList(i);
            String toCheck = currentRoom.getRoomName() + currentRoom.getBestTime();
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
