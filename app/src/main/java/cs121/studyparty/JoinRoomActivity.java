package cs121.studyparty;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.provider.Settings.Secure;

import java.util.List;


/**
 * Created by Nava Dallal on 10/1/15. This is for the functionality of the second screen
 */
public class JoinRoomActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener{


    TextView main_textView;
    TextView details_textView;
    Button main_button;
    Button main_button2;
    String name = Secure.getString(Application.getContext().getContentResolver(), Secure.ANDROID_ID);
    public static User toAdd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_join_room);

        //set the title to the proper room name
        main_textView = (TextView) findViewById(R.id.main_textview);
        main_textView.setText(MainActivity.room);

        details_textView = (TextView) findViewById(R.id.details_textview);
        details_textView.setText("Occupancy: " + RoomList.chosenRoom.getNumOccupants());

        //if the user has joined the room, show the leave room button
        if (RoomList.chosenRoom.getOccupancy()) {
            main_button = (Button) findViewById(R.id.main_button);
            main_button.setOnClickListener(this);
            main_button.setBackgroundColor(Color.rgb(135, 206, 235));
            main_button.setVisibility(View.INVISIBLE);

            main_button2 = (Button) findViewById(R.id.main_button2);
            main_button2.setOnClickListener(this);
            main_button2.setBackgroundColor(Color.rgb(135, 206, 235));
            main_button2.setVisibility(View.VISIBLE);
        }
        //otherwise show join room button
        else {
            main_button = (Button) findViewById(R.id.main_button);
            main_button.setOnClickListener(this);
            main_button.setBackgroundColor(Color.rgb(135, 206, 235));
            main_button.setVisibility(View.VISIBLE);
            main_button2 = (Button) findViewById(R.id.main_button2);
            main_button2.setOnClickListener(this);
            main_button2.setBackgroundColor(Color.rgb(135, 206, 235));
            main_button2.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_join_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Log.d("hi", NavUtils.getParentActivityName(this));
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        toAdd = new User(name);
        if (v.getId() == R.id.main_button) {
            //if the user has already joined a room and is now joining a new room, log them out
            // of the old room
            if ((!RoomList.enteredRoom.getRoomName().equals(RoomList.chosenRoom.getRoomName()))
                    && (RoomList.enteredRoom.getNumOccupants() > 0)){
                RoomList.enteredRoom.decrementNumOccupants();
                RoomList.enteredRoom.removeUserfromParty(User.currentUser);
                RoomList.enteredRoom.setOccupancy(false);
            }

            //set the current user to the user created from clicking join room
            User.currentUser = toAdd;
            RoomList.chosenRoom.addUsertoParty(toAdd);
            User.currentUser.joinRoom();

            //set the proper text
            RoomList.chosenRoom.incrementNumOccupants();
            RoomList.chosenRoom.setOccupancy(true);
            details_textView.setText("Occupancy: " + RoomList.chosenRoom.getNumOccupants());
            RoomList.chosenRoom.setName(RoomList.chosenRoom.getRoomName());
            String time = RoomList.chosenRoom.getBestTime();
            main_textView.setText(RoomList.chosenRoom.getRoomName() + time);

            //make leave room button visible
            main_button.setVisibility(View.INVISIBLE);
            main_button2.setVisibility(View.VISIBLE);


            //since this room has now been entered by user, set entered room to chosen room
            RoomList.enteredRoom = RoomList.chosenRoom;
            RoomList.enteredRoom.setName(RoomList.chosenRoom.getRoomName());

            List<Room> roomList = MainActivity.roomListObject.getRoom();

            //get the index of the entered room in the room list object
            for (int i = 0; i < roomList.size(); i++){
                Room currentRoom = MainActivity.roomListObject.getRoomFromList(i);
                String toCheck = currentRoom.getRoomName();

                if ((toCheck).equals(RoomList.enteredRoom.getRoomName())){
                    MainActivity.roomListObject.enteredIndex = i;
                }
            }
        }

        if (v.getId() == R.id.main_button2) {
            //if user is leaving a room, set entered room to default
            RoomList.enteredRoom = new Room("NO NAME");

            RoomList.chosenRoom.decrementNumOccupants();
            details_textView.setText("Occupancy: " + RoomList.chosenRoom.getNumOccupants());
            String time = RoomList.chosenRoom.getBestTime();
            main_textView.setText(RoomList.chosenRoom.getRoomName() + time);

            //make join room button visible
            main_button2.setVisibility(View.INVISIBLE);
            main_button.setVisibility(View.VISIBLE);

            //remove the current user from occupants array
            User.currentUser.leaveRoom();
            RoomList.chosenRoom.removeUserfromParty(User.currentUser);
        }

    }

    @Override
    //if the back button on phone (not app) is pressed
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Log the item's position and contents
        // to the console in Debug
    }


}
