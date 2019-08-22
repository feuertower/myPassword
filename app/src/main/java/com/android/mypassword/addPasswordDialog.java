package com.android.mypassword;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addPasswordDialog extends Dialog implements
android.view.View.OnClickListener{

    private Context context;
    private DBHandler db;

    public addPasswordDialog(Context c, DBHandler db){
        super(c);
        this.context = c;
        this.db = db;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.content_add_password);

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener( this );
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener( this );

    }

    @Override
    public void onClick(View view){
        switch ( view.getId() ){
            case R.id.addBtn:
                // add entry
                onAddClicked();
                break;

            case R.id.cancelBtn:
                // cancel dialog
                this.cancel();
                break;
        }
    }

    private void onAddClicked() {
        EditText displayNameEdit = findViewById(R.id.displayName);
        EditText usernameEdit = findViewById(R.id.username);
        EditText passwordEdit = findViewById(R.id.passwordEdit);
        EditText infoEdit = findViewById(R.id.info);

        String displayNameStr = displayNameEdit.getText().toString();
        String usernameStr = usernameEdit.getText().toString();
        String passwordStr = passwordEdit.getText().toString();
        String infoStr = infoEdit.getText().toString();


        PasswordEntry entry = new PasswordEntry();
        entry.setDisplayName( displayNameStr );
        entry.setUsername( usernameStr );
        entry.setPassword( passwordStr );
        entry.setInfoText( infoStr );

        boolean isUsernameSet = usernameStr != null && !usernameStr.equalsIgnoreCase("");
        boolean isPasswordSet = passwordStr != null && !passwordStr.equalsIgnoreCase("");

        if( isUsernameSet && isPasswordSet ) {
            db.addPasswordEntry(entry);
            this.dismiss();
        }
        else {
            Toast.makeText( context,
                    "Please set at least a username and password",
                    Toast.LENGTH_SHORT ).show();
        }

    }




}
