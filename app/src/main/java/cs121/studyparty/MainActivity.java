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
import android.widget.Button;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList<String> roomNameList = new ArrayList();
    ArrayList<Room> roomList = new ArrayList();
    public static String room;
    public static Room sampleRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("lamas", "test");
        testObject.saveInBackground();

        mainListView = (ListView) findViewById(R.id.main_listview);

        //add room to roomList and room name to roomNameList
        sampleRoom = new Room("Shanahan 2475", 0);
        roomList.add(sampleRoom);
        roomNameList.add(sampleRoom.getRoomName());

        //display room names in roomNameList in the listview
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                roomNameList);

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
        //set the room on the next screen to the room chosen by the user
        for (int i = 0; i < roomList.size(); i++){
            Room currentRoom = roomList.get(i);
            if (currentRoom.getRoomName().equals(room)){
                sampleRoom = currentRoom;
            }
        }
        // Log the item's position and contents
        // to the console in Debug
        Log.d("room", position + ": " + roomList.get(position));

        //switch screens when room is picked
        startActivity(new Intent(getApplicationContext(), JoinRoomActivity.class));
    }
}
