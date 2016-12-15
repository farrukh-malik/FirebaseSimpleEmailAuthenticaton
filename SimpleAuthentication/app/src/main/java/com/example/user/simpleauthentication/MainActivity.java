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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    EditText email, password;
    Button signin, createAccount;

    String stringEmail, stringPassword;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, SignoutActivity.class));
        }else {
            Toast.makeText(this, "Login plzz", Toast.LENGTH_SHORT).show();
        }

        email = (EditText) findViewById(R.id.emailIdEditTexbox);
        password = (EditText) findViewById(R.id.passwordIdEditTextbox);

        signin = (Button) findViewById(R.id.signinBtn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                stringEmail = email.getText().toString().trim();
                stringPassword = password.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(stringEmail,stringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Successfully Signin", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,SignoutActivity.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Not successful! Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        createAccount = (Button) findViewById(R.id.createAccountBtnId);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });
    }
}
