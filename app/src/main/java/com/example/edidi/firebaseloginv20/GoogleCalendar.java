package com.example.edidi.firebaseloginv20;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GoogleCalendar extends AppCompatActivity {

    private EditText editText;
    private Button button_copy;
    private ClipboardManager cbm;
    private ClipData cd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_calendar);

        editText = findViewById(R.id.text_linkcalendar);
        button_copy = findViewById(R.id.button_copy);

        cbm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);



        button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                cd = ClipData.newPlainText("text",text);
                cbm.setPrimaryClip(cd);
            }

        });

    }
    }



