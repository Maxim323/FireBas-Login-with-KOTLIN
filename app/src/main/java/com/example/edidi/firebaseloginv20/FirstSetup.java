package com.example.edidi.firebaseloginv20;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;


public class FirstSetup extends AppCompatActivity {

    private static final String KEY_NUME_PRENUME = "Nume si prenume";
    private static final String KEY_FACULTATE = "Facultate";
    private static final String KEY_SPECIALIZARE = "Specializare";
    private static final String KEY_AN = "An";
    private static final String KEY_GRUPA = "Grupa";

    private EditText editTextaAn;
    private EditText editTextNume_Prenume;
    private EditText editTextFacultate;
    private EditText editTextSpecializare;
    private EditText editTextGrupa;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_setup);


        editTextNume_Prenume = findViewById(R.id.edit_text_nume_prenume);
        editTextGrupa = findViewById(R.id.edit_text_grupa);
        editTextaAn = findViewById(R.id.edit_text_an);
        editTextFacultate = findViewById(R.id.edit_text_facultate);
        editTextSpecializare = findViewById(R.id.edit_text_specializare);

    }//main function



    public void saveNote(View view) {
        String name_presume = editTextNume_Prenume.getText().toString();
        String facultate = editTextFacultate.getText().toString();
        String specializare = editTextSpecializare.getText().toString();
        String an = editTextaAn.getText().toString();
        String grupa = editTextGrupa.getText().toString();


        Map<String, Object> note = new HashMap<>();
        note.put(KEY_FACULTATE, facultate);
        note.put(KEY_SPECIALIZARE, specializare);
        note.put(KEY_GRUPA, grupa);
        note.put(KEY_AN, an);
        note.put(KEY_NUME_PRENUME, name_presume);


        db.collection("Utilizatori").document(String.valueOf(name_presume)).set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(FirstSetup.this, "Note saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirstSetup.this, "Error!", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}



