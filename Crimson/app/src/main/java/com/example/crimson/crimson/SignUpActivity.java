package com.example.crimson.crimson;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText usernameField;
    private EditText passwordField;
    private EditText confrimPasswordField;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        usernameField = (EditText)findViewById(R.id.editSignUpUsername);
        passwordField = (EditText)findViewById(R.id.editSignUpPassword);
        confrimPasswordField = (EditText)findViewById(R.id.editSignUpConfirmPassword);
        signUpButton = (Button)findViewById(R.id.buttonSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp(){

        final String username = usernameField.getText().toString();
        final String password = passwordField.getText().toString();
        final String confirm_password = confrimPasswordField.getText().toString();

        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(confirm_password)) {

            Toast.makeText(SignUpActivity.this, "Please Enter All Credentials", Toast.LENGTH_LONG).show();

        }
        else if(!TextUtils.equals(password,confirm_password)){
            Toast.makeText(SignUpActivity.this, "Passwords Do Not Match", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(SignUpActivity.this, "Sign Up Unsuccessful! Try again!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
