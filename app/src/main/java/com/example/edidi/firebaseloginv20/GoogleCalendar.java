package com.example.edidi.firebaseloginv20;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GoogleCalendar extends AppCompatActivity {

    private EditText editText;
    private Button button_copy1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_calendar);

        editText = findViewById(R.id.text_linkcalendar);
        button_copy1 = findViewById(R.id.button_copy);

        //nu stiu de ce iti da crash la aplciatie...totul pare corect
        button_copy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text", editText.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(GoogleCalendar.this, "TextCopied", Toast.LENGTH_SHORT).show();
            }

        });

    }
    }



