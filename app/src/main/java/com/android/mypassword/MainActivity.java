package com.android.mypassword;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        // refactor database handling: http://instinctcoder.com/android-studio-sqlite-database-multiple-tables-example/
        // -> need more tables to set user password in database
        if( pwString.equals("a"))
        {
            Intent i = new Intent( this, passwordList.class);
            startActivity( i );
            finish();
        }
        else
        {
            Toast.makeText(this, "password wrong!!", Toast.LENGTH_SHORT).show();
        }

    }
}