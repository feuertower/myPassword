package com.android.mypassword;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class passwordList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvPasswordList = null;
    private DBHandler dbHandler = new DBHandler( this );

    final List<String> listElementsArrayList = new ArrayList<String>();
    ArrayAdapter<String> listAdapter;
    addPasswordDialog addDialog;
    List<PasswordEntry> passwordEntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addDialog = new addPasswordDialog( this, dbHandler );

        listAdapter = new ArrayAdapter<String>(this,
                                                android.R.layout.simple_list_item_1,
                                                listElementsArrayList);
        lvPasswordList = findViewById( R.id.passwordList );
        lvPasswordList.setOnItemClickListener(this);
        lvPasswordList.setAdapter(listAdapter);

        updateList();
    }

    public void clickHandler(View view){
        switch( view.getId() ){
            case R.id.fab:
                addDialog.show();
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*PasswordEntry entry = (PasswordEntry) parent.getItemAtPosition( position );*/
        PasswordEntry entry = passwordEntryList.get( position );
        dbHandler.removeEntry(entry);

        updateList();
    }

    void updateList() {

        listAdapter.clear();

        // fill table with existing passwords
        passwordEntryList = dbHandler.getPasswordList();

        for( int i = 0; passwordEntryList.size() > i; i++ )
        {
            Log.i( "passwordList", "reading db.. " + i);
            // show displayName in List
            listElementsArrayList.add( passwordEntryList.get( i ).getDisplayName() );
            listAdapter.notifyDataSetChanged();
        }

        Log.i("passwordList", this.getDatabasePath("passwords.db").toString());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
