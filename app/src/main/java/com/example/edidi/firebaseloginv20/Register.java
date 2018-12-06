package com.example.edidi.firebaseloginv20;

import android.content.Intent;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mRegister;
    private EditText mDisplayName;
    private EditText mPassword;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mRegister = findViewById(R.id.register_button);
        mDisplayName = findViewById(R.id.display_name);
        mPassword = findViewById(R.id.login_password);
        mEmail = findViewById(R.id.login_mail);
       final String email_gmail = "@gmail.com";
        String email_student = "@student.unitbv.ro";
        String email_personal = "@unitbv.ro"      ;


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display_name = mDisplayName.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();

                if( TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(display_name)) {
                    mPassword.setError("Enter password");
                    mEmail.setError("Enter email");
                    mDisplayName.setError("Enter  display name");
                }
                else if(email.toLowerCase().indexOf(email_gmail.toLowerCase()) == -1){
                    mEmail.setError("Enter valid gmail acocunt.");
                }
                else if( TextUtils.isEmpty(email)) {
                    mEmail.setError("Enter email");
                }
                else if(TextUtils.isEmpty(password)){
                    mPassword.setError("Enter password");
                }
                else if(TextUtils.isEmpty(display_name)){
                    mDisplayName.setError("Enter  display name");
                }
                else if(display_name.length() <5){
                    mDisplayName.setError("Nickname too short.");
                }
                else if(password.length()<6){
                    mPassword.setError("Password too short.");
                }
                else{
                    register_user(display_name,email,password);
                }


            }
        });


    }

    private void register_user(String display_name, String email,String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent mainIntent = new Intent(Register.this,Messsenger.class);
                            startActivity(mainIntent);
                            finish();

                        } else {
                            Toast.makeText(Register.this, "Registration error", Toast.LENGTH_SHORT).show();
                        }

                     
                    }
                });

    }
}
