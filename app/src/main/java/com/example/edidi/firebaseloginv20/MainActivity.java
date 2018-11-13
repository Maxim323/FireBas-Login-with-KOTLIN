package com.example.edidi.firebaseloginv20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
        private Button mail_constitutional;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail_constitutional = findViewById(R.id.mail_constitutional);
        mail_constitutional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openWebView();
            }
        });

    }

    public void openWebView() {
        Intent intent = new Intent(this, EmailWebView.class);
        startActivities(new Intent[]{intent});
    }


}
