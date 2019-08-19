package com.android.mypassword;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class addPassword extends Dialog implements
android.view.View.OnClickListener{

    public Context context;

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
    }


    @Override
    public void onClick(View view)
    {

    }




}
