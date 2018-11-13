package com.example.edidi.firebaseloginv20;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;







public class SpinnerChoise extends AppCompatActivity  {

    private static final String KEY_NUME_PRENUME = "Nume si prenume";
    private static final String KEY_FACULTATE = "Facultate";
    private static final String KEY_SPECIALIZARE = "Specializare";
    private static final String KEY_AN = "An";
    private static final String KEY_GRUPA = "Grupa";

    private EditText editTextNume_Prenume;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_choise);


        editTextNume_Prenume = findViewById(R.id.edit_text_nume_prenume);
        Button save = findViewById(R.id.save);



        //string`uri pentru autocomplete
        String[] Facultate = getResources().getStringArray(R.array.Facultate);
        String[] Specializare = getResources().getStringArray(R.array.Specializare);
        String[] An = getResources().getStringArray(R.array.An);
        String[] Grupa = getResources().getStringArray(R.array.Grupa);




        final  AutoCompleteTextView facultate = findViewById(R.id.facultate);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, Facultate);
        facultate.setAdapter(adapter);
        //Toast.makeText(this, getString(R.string.facultate), Toast.LENGTH_SHORT).show();

        final  AutoCompleteTextView specializare = findViewById(R.id.specializare);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, Specializare);
        specializare.setAdapter(adapter1);

        final  AutoCompleteTextView an = findViewById(R.id.an);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, An);
        an.setAdapter(adapter2);

        final AutoCompleteTextView grupa = findViewById(R.id.grupa);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, Grupa);
        grupa.setAdapter(adapter3);




        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {


                String nume_presume = editTextNume_Prenume.getText().toString();
                final String input_grupa = grupa.getText().toString();
                final String input_facultate = facultate.getText().toString();
                final String input_specializare = specializare.getText().toString();
                final  String input_an = an.getText().toString();

                Map<String, Object> note = new HashMap<>();
                note.put(KEY_NUME_PRENUME, nume_presume);
                note.put(KEY_FACULTATE, input_facultate);
                note.put(KEY_SPECIALIZARE, input_specializare);
                note.put(KEY_AN, input_an);
                note.put(KEY_GRUPA, input_grupa);

                db.collection("Utilizatori").document(String.valueOf(nume_presume)).set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SpinnerChoise.this, "Note saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SpinnerChoise.this, "Error!", Toast.LENGTH_SHORT).show();

                            }
                        });


                openMainActivity();
            }
        }); //apasand pe buton


        }//on create

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    }//final





