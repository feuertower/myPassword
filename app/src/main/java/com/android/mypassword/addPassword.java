package com.android.mypassword;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class addPassword extends Dialog implements
android.view.View.OnClickListener{

    private Context context;
    public addPassword(Context c)
    {
        super(c);
        this.context = c;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.content_add_password);

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener( this );
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener( this );

    }


    @Override
    public void onClick(View view)
    {
        switch ( view.getId() )
        {
            case R.id.addBtn:
                // add entry
                break;

            case R.id.cancelBtn:
                // cancel dialog
                break;
        }
    }




}
