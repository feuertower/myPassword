package com.android.mypassword;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class passwordList extends AppCompatActivity {

    ListView lvPasswordList = null;
    private DBHandler dbHandler = new DBHandler( this );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final List<String> listElementsArrayList = new ArrayList<String>();
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, listElementsArrayList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // add new password function
               listAdapter.notifyDataSetChanged();

            }
        });

        PasswordEntry entry1 = new PasswordEntry( 1, "dominik", "feuertower",
                                            "myPassword", "no Info");
        dbHandler.addPasswordEntry( entry1 );

        lvPasswordList = findViewById( R.id.passwordList );
        lvPasswordList.setAdapter(listAdapter);

        // fill table with existing passwords
        List<PasswordEntry> passwordEntryList = dbHandler.getPasswordList();

        for( int i = 0; passwordEntryList.size() > 0; i++ )
        {
            Log.d( "reading db .. ", "" + i);
            // show displayName in List
            listElementsArrayList.add( passwordEntryList.get( i ).getDisplayName() );
            listAdapter.notifyDataSetChanged();
        }

        Log.i("1", this.getDatabasePath("passwords.db").toString());
    }

}
