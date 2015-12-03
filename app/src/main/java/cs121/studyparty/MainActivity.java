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

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    public static String room;
    public static RoomList roomListObject;
    EditText inputSearch;


    final static String OBJECTID = "aqfWCXmdBR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Room testRoom = new Room("ShanaHAN", 0);
        testRoom.setOccupancy(true);

        ParseQuery<RoomList> query = ParseQuery.getQuery(RoomList.class);


        mainListView = (ListView) findViewById(R.id.main_listview);

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
            try {
                query.whereEqualTo("objectId", RoomList.roomID);
                roomListObject = query.getFirst();
                roomListObject.saveInBackground();
                Log.d("check", roomListObject.getRoomNames().get(RoomList.chosenIndex));
                Log.d("time", String.valueOf(roomListObject.getRoom().get(RoomList.chosenIndex).getNumOccupants()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if ( roomListObject.getRoom().get(RoomList.chosenIndex).getNumOccupants() > 0) {
                roomListObject.getRoomNames().set(RoomList.chosenIndex, RoomList.chosenRoom.getRoomName() +
                        "\n" +
                        roomListObject.getRoom().get(RoomList.chosenIndex).getNumOccupants());
            }
            else {
                roomListObject.getRoomNames().set(RoomList.chosenIndex, RoomList.chosenRoom.getRoomName() +
                        "\nUnoccupied");
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
        List<Room> roomList = roomListObject.getRoom();
        Log.d("rooms", roomListObject.getRoomNames().get(0));

        //set the room on the next screen to the room chosen by the user
        for (int i = 0; i < roomList.size(); i++){
            Room currentRoom = roomListObject.getRoomFromList(i);
            String toCheck = currentRoom.getRoomName() + currentRoom.getBestTime();
            if ((toCheck).equals(room)){
                roomListObject.chosenIndex = i;
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

//package cs121.studyparty;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ListView;
//
//import com.parse.FindCallback;
//import com.parse.GetCallback;
//import com.parse.ParseException;
//import com.parse.ParseQuery;
//import com.parse.SaveCallback;
//
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener,
//        AdapterView.OnItemClickListener{
//
//    ListView mainListView;
//    ArrayAdapter mArrayAdapter;
//    public static String room;
//    public static RoomList roomListObject;
//    EditText inputSearch;
//
//
//    final static String OBJECTID = "aqfWCXmdBR";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
//
//        final Room testRoom = new Room("ShanaHAN", 0);
//        testRoom.setOccupancy(true);
//
//        ParseQuery<RoomList> query = ParseQuery.getQuery(RoomList.class);
//
//
//        mainListView = (ListView) findViewById(R.id.main_listview);
//
//        if (!RoomList.firstTime) {
//            roomListObject = new RoomList();
//            roomListObject.initializeList();
//            roomListObject.saveInBackground(new SaveCallback() {
//
//                public void done(ParseException e) {
//                    if (e == null) {
//                        // Saved successfully.
//                        Log.d("KEYKEY", "User update saved!");
//                        String ID = roomListObject.getObjectId();
//                        Log.d("KEYKEY", "The object id of roomlist (from User) is: " + ID);
//                        roomListObject.setObjectId(ID);
//                        try {
//                            Log.d("Sleepy", "I'm sleeping now");
//                            Thread.sleep(5000);
//                        } catch (InterruptedException f) {
//                            // TODO Auto-generated catch block
//                            ntStackTrace();
//                        }
//
//                    } else {
//                        // The save failed.
//                        Log.d("KEYKEY", "User update error: " + e);
//                    }
//                }
//
//            });
//            RoomList.firstTime = true;
//
//            try {
//                Thread.sleep(5000);                 //1000 milliseconds is one second.
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//
//            RoomList.roomID = roomListObject.getObjectId();
//
//        }
//
//    else {
//            try {
//                query.whereEqualTo("objectId", RoomList.roomID);
//                roomListObject = query.getFirst();
//                roomListObject.saveInBackground();
//                Log.d("check", roomListObject.getRoomNames().get(RoomList.chosenIndex));
//                Log.d("time", String.valueOf(roomListObject.getRoom().get(RoomList.chosenIndex).getNumOccupants()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            if ( roomListObject.getRoom().get(RoomList.chosenIndex).getNumOccupants() > 0) {
//                roomListObject.getRoomNames().set(RoomList.chosenIndex, RoomList.chosenRoom.getRoomName() +
//                        "\n" +
//                        roomListObject.getRoom().get(RoomList.chosenIndex).getNumOccupants());
//            }
//            else {
//                roomListObject.getRoomNames().set(RoomList.chosenIndex, RoomList.chosenRoom.getRoomName() +
//                        "\nUnoccupied");
//            }
//
//        }
//
//        //display room names in roomNames in the listview
//        mArrayAdapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1,
//                roomListObject.getRoomNames());
//
//        mainListView.setAdapter(mArrayAdapter);
//
//        mainListView.setOnItemClickListener(this);
//
//        inputSearch = (EditText) findViewById(R.id.inputSearch);
//
//        inputSearch.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                // When user changed the Text
//                MainActivity.this.mArrayAdapter.getFilter().filter(cs);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
//                                          int arg3) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onClick(View v) {
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//       room = (String) parent.getItemAtPosition(position);
//       List<Room> roomList = roomListObject.getRoom();
//        Log.d("rooms", roomListObject.getRoomNames().get(0));
//
//        //set the room on the next screen to the room chosen by the user
//        for (int i = 0; i < roomList.size(); i++){
//            Room currentRoom = roomListObject.getRoomFromList(i);
//            String toCheck = currentRoom.getRoomName() + currentRoom.getBestTime();
//            if ((toCheck).equals(room)){
//                roomListObject.chosenIndex = i;
//                roomListObject.chosenRoom = currentRoom;
//            }
//        }
//
//        // Log the item's position and contents
//        // to the console in Debug
//        Log.d("room", position + ": " + roomList.get(position));
//
//        //switch screens when room is picked
//        startActivity(new Intent(getApplicationContext(), JoinRoomActivity.class));
//    }
//}
