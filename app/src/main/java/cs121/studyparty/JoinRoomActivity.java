package cs121.studyparty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList nameList = new ArrayList();
    EditText name_editText;
    Button main_button;
    Button main_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        //set the title to the proper room name
        main_textView = (TextView) findViewById(R.id.main_textview);
        main_textView.setText(MainActivity.room);

        main_button = (Button) findViewById(R.id.main_button);
        main_button.setOnClickListener(this);

        main_button2 = (Button) findViewById(R.id.main_button2);
        main_button2.setOnClickListener(this);

        name_editText = (EditText) findViewById(R.id.name_editText);

        mainListView = (ListView) findViewById(R.id.main_listview);

        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                nameList);

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);

        mainListView.setOnItemClickListener(this);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //if the first button is clicked
        if(v.getId() == R.id.main_button) {
            // Also add that value to the list shown in the ListView
            nameList.add(name_editText.getText().toString());
            mArrayAdapter.notifyDataSetChanged();

            //switch to leave party button
            main_button.setVisibility(View.INVISIBLE);
            main_button2.setVisibility(View.VISIBLE);
        }

        //if the second button is clicked
        else if (v.getId() == R.id.main_button2){
            //remove name from list
            nameList.remove(nameList.size() - 1);
             //I realize this needs to be edited later for when the user is not the most recent addition
            mArrayAdapter.notifyDataSetChanged();

            //switch back to join party button
            main_button2.setVisibility(View.INVISIBLE);
            main_button.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Log the item's position and contents
        // to the console in Debug
        Log.d("person", position + ": " + nameList.get(position));
    }
}
