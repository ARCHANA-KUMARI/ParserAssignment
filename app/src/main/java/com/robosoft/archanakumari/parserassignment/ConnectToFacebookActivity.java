package com.robosoft.archanakumari.parserassignment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.robosoft.archanakumari.parserassignment.fragments.LoginFacebookFragment;

public class ConnectToFacebookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_facebook);
        FragmentManager framentmanager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = framentmanager.beginTransaction();
        LoginFacebookFragment fragment = new LoginFacebookFragment();
        fragmentTransaction.add(R.id.frame, fragment);
        fragmentTransaction.commit();


    }

}
