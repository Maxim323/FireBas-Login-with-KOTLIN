package com.example.edidi.firebaseloginv20;

import android.content.Intent;
import android.os.Build;
import android.os.Messenger;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ClipData.newIntent;

public class Messsenger extends AppCompatActivity {


    private  FirebaseAuth mAuth;
    private Toolbar mToolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messsenger);

        mAuth = FirebaseAuth.getInstance();
        //Toolbar
        mToolbar = findViewById(R.id.main_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MessengerChat");


    }//OnCreate


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            sendToLogin();

        }
    }

    private void sendToLogin() {
        Intent startIntent = new Intent(Messsenger.this, Login.class);
        startActivity(startIntent);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

         if(item.getItemId() == R.id.main_logout_btn){

             FirebaseAuth.getInstance().signOut();
             sendToLogin();
         }

        return true;
    }

    //Meniu dreapta sus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.main_menu, menu);
         return true;
    }
}


