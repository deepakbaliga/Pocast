package com.deepakbaliga.pocast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.alexrs.prefs.lib.Prefs;

public class ActivitySession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        boolean loggedIn = Prefs.with(this).getBoolean("loggedin", false);

        if(loggedIn){

            startActivity(new Intent(this, ActivityMain.class));
            finish();

        }else{
            startActivity(new Intent(this, ActivityLogin.class));
            finish();
        }
    }
}
