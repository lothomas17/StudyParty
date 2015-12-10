package cs121.studyparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    public static String room;                  //the room chosen by the user
    public static RoomList roomListObject;      //the roomList from Parse populate listview
    EditText inputSearch;                       //search bar input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ParseQuery<RoomList> query = ParseQuery.getQuery(RoomList.class);


        mainListView = (ListView) findViewById(R.id.main_listview);

        //if main activity has not been run yet, initialize the default roomlist
        if (!RoomList.firstTime) {
            roomListObject = new RoomList();
            roomListObject.initializeList();
            roomListObject.saveInBackground(new SaveCallback() {

                public void done(ParseException e) {
                    if (e == null) {
                        // Saved successfully.
                        Log.d("KEYKEY", "User update saved!");
                        String ID = roomListObject.getObjectId();
                        Log.d("KEYKEY", "The object id of roomlist (from User) is: " + ID);
                        roomListObject.setObjectId(ID);
                        try {
                            Log.d("Sleepy", "I'm sleeping now");
                            Thread.sleep(5000);
                        } catch (InterruptedException f) {
                            // TODO Auto-generated catch block
                            f.printStackTrace();
                        }

                    } else {
                        // The save failed.
                        Log.d("KEYKEY", "User update error: " + e);
                    }
                }

            });
            RoomList.firstTime = true;

           try {
                Thread.sleep(5000);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            RoomList.roomID = roomListObject.getObjectId();

        }
        else {
            //if not running screen for the first time, retrieve updated list from parse
            try {
                query.whereEqualTo("objectId", RoomList.roomID);
                roomListObject = query.getFirst();
                roomListObject.saveInBackground();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //update the names of the room chosen by user or entered by user to display proper time
            // in the listview
            roomListObject.getRoomNames().set(RoomList.chosenIndex, RoomList.chosenRoom.getRoomName()
                    + RoomList.chosenRoom.getBestTime());

            if (RoomList.enteredRoom.getNumOccupants() > 0 ) {
                roomListObject.getRoomNames().set(RoomList.enteredIndex, RoomList.enteredRoom.getRoomName()
                        + RoomList.enteredRoom.getBestTime());
            }

        }

        //display room names in roomNames in the listview
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                roomListObject.getRoomNames());

        mainListView.setAdapter(mArrayAdapter);

        mainListView.setOnItemClickListener(this);

        inputSearch = (EditText) findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
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
        // automatically handle clicks on the Home/Up button
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
        //if the chosen room and entered room have been initialized, update their names
        // since the get best time is constantly changing
        if (RoomList.chosenIndex > -1) {
            roomListObject.getRoomNames().set(RoomList.chosenIndex, RoomList.chosenRoom.getRoomName()
                    + RoomList.chosenRoom.getBestTime());
        }
        if (RoomList.enteredIndex > -1) {
            if (RoomList.enteredRoom.getNumOccupants() > 0) {
                roomListObject.getRoomNames().set(RoomList.enteredIndex, RoomList.enteredRoom.getRoomName()
                        + RoomList.enteredRoom.getBestTime());
            }
        }

       room = (String) parent.getItemAtPosition(position);
       List<Room> roomList = roomListObject.getRoom();

        //set the room on the next screen to the room chosen by the user
        for (int i = 0; i < roomList.size(); i++){
            Room currentRoom = roomListObject.getRoomFromList(i);
            String toCheck = currentRoom.getRoomName() + currentRoom.getBestTime();
            if ((toCheck).equals(room)){
                roomListObject.chosenIndex = i;
                roomListObject.chosenRoom = currentRoom;
                break;
            }
        }
        // Log the item's position and contents
        // to the console in Debug
        Log.d("room", position + ": " + roomList.get(position));

        //switch screens when room is picked
        startActivity(new Intent(getApplicationContext(), JoinRoomActivity.class));
    }
}
