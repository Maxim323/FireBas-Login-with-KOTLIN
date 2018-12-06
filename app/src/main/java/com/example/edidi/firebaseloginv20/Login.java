package com.example.edidi.firebaseloginv20;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mLogin;
    private Button mRegister;
    private EditText mLoginMail;
    private EditText mLoginPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mLogin = findViewById(R.id.login_button);
        mRegister = findViewById(R.id.register_button);
         mLoginMail = findViewById(R.id.login_mail);
         mLoginPassword = findViewById(R.id.login_password);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(Login.this, Register.class);
                startActivity(startIntent);
                finish();
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = mLoginPassword.getText().toString().trim();
                final  String email = mLoginMail.getText().toString().trim();

               if( TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                   mLoginPassword.setError("Enter password");
                   mLoginMail.setError("Enter email");
               }
                else if( TextUtils.isEmpty(email)) {
                   mLoginMail.setError("Enter email");
               }
                else if(TextUtils.isEmpty(password)){
                   mLoginPassword.setError("Enter password");
                       }
                else{
                    loginUser(email, password);
                }
               }//on click
        });

    }

    private void loginUser(String password, String email) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent mainIntent = new Intent(Login.this,Messsenger.class);
                            startActivity(mainIntent);
                            finish();

                        } else {
                            Toast.makeText(Login.this, "Email or password incorrect.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }


}
