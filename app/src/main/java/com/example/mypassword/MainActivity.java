package com.example.mypassword;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab = null;
    ListView lvPasswordList = null;
    String[] ListElements = new String[] {
            "Dominik"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvPasswordList = (ListView) findViewById( R.id.passwordList );
        lvPasswordList.setVisibility( View.INVISIBLE );

        final List<String> listElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, listElementsArrayList);

        lvPasswordList.setAdapter(listAdapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new password function
                listElementsArrayList.add( "Annika" );
                listAdapter.notifyDataSetChanged();
            }
        });

        fab.setVisibility( View.INVISIBLE ); // set fab invisible before the app password is entered

        // show soft keyboard on app start?

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

    public void clickHandler(View view) {

        switch(view.getId()) {
            case R.id.acceptBtn:
                onAcceptBtnPressed();
                break;
        }
    }

    public void onAcceptBtnPressed() {

        EditText pwEditText = findViewById(R.id.pwEditText);
        String pwString = pwEditText.getText().toString();

        if( pwString.equals("a"))
        {
            fab.setVisibility( View.VISIBLE );
            findViewById(R.id.startLayout).setVisibility( View.INVISIBLE );

            // if soft keyboard is shown on appstart -> should be close here
            lvPasswordList.setVisibility( View.VISIBLE );


        }
        else
        {
            Toast.makeText(this, "password wrong!!", Toast.LENGTH_SHORT).show();
        }

    }
}