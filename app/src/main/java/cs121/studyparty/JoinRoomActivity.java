package cs121.studyparty;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Nava Dallal on 10/1/15. This is for the functionality of the second screen
 */
public class JoinRoomActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    TextView main_textView;
    TextView details_textView;
    Button main_button;
    Button main_button2;
    InputMethodManager inputManager;
    User checkingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        //set the title to the proper room name
        main_textView = (TextView) findViewById(R.id.main_textview);
        main_textView.setText(MainActivity.room);

        details_textView = (TextView) findViewById(R.id.details_textview);
        details_textView.setText("Occupancy: " + MainActivity.sampleRoom.getNumOccupants());

        main_button = (Button) findViewById(R.id.main_button);
        main_button.setOnClickListener(this);
        main_button.setBackgroundColor(Color.rgb(135,206,235));

        main_button2 = (Button) findViewById(R.id.main_button2);
        main_button2.setOnClickListener(this);
        main_button2.setBackgroundColor(Color.rgb(135,206,235));

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
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_button) {
            MainActivity.sampleRoom.incrementNumOccupants();
            details_textView.setText("Occupancy: " + MainActivity.sampleRoom.getNumOccupants());
            main_button.setVisibility(View.INVISIBLE);
            main_button2.setVisibility(View.VISIBLE);
        }
        if (v.getId() == R.id.main_button2) {
            MainActivity.sampleRoom.decrementNumOccupants();
            details_textView.setText("Occupancy: " + MainActivity.sampleRoom.getNumOccupants());
            main_button2.setVisibility(View.INVISIBLE);
            main_button.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Log the item's position and contents
        // to the console in Debug
    }
}
