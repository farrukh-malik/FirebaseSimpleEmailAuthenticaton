package com.example.user.simpleauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    EditText email, password;
    Button signup;

    String stringEmail, stringPassword;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        firebaseAuth = FirebaseAuth.getInstance();


        email = (EditText) findViewById(R.id.emailIdEditTexbox);
        password = (EditText) findViewById(R.id.passwordIdEditTextbox);



        signup = (Button) findViewById(R.id.signupBtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stringEmail = email.getText().toString().trim();
                stringPassword = password.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(stringEmail,stringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "successfully created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this,SignoutActivity.class));
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Not created! Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
