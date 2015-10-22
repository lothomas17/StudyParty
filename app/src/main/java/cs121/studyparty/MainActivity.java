package cs121.studyparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity{

    Button mainButton;
    Spinner staticSpinner;
    public static String room;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ParseObject testObject = new ParseObject("TestObject");
        testObject.put("fool", "nonsense");
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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String objectID = testObject.getObjectId();  // for some reason objectID comes out as null
        Log.d("KEYKEY", "bs " + objectID);  // need to figure out how to get ID we see in Parse dashboard

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
        String name = testObject.getObjectId();
        Log.d("KEYKEY", "name is " + name);
        query.getInBackground(name, new GetCallback<ParseObject>() {  // don't want to have to hardcode in objectID
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    Log.d("Crazyshit", "might actually be working");
                    String testFool = "hey " + testObject.getString("fool");  // pulls accurate information from the cloud!
                    Log.d("Crazyshit", testFool);

                } else {
                    Log.d("BADBAD", e.toString());
                }
            }
        });



        mainButton = (Button)findViewById(R.id.main_button);

        staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.location_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d("room", (String) parent.getItemAtPosition(position));
                //set room name to whatever user clicked
                room = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //switch to next screen if submit button is clicked
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), JoinRoomActivity.class));
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
}
