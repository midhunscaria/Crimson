package com.example.crimson.crimson;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private TextView forgotPasswordText;
    private Button loginButton;
    private Button NotRegisteredButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        usernameField = (EditText)findViewById(R.id.editUsername);
        passwordField = (EditText)findViewById(R.id.editPassword);
        loginButton = (Button)findViewById(R.id.buttonLogin);
        NotRegisteredButton = (Button)findViewById(R.id.buttonNotRegistered);
        forgotPasswordText = (TextView)findViewById(R.id.textViewForgotPassword);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        NotRegisteredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ResetPasswordActivity.class));
            }
        });
    }

    private void signIn()
    {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password))
        {
            Toast.makeText(MainActivity.this, "Please enter all credentials", Toast.LENGTH_LONG).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
                    }
                    else if(task.isSuccessful()) {
                        startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                    }
                }
            });
        }
    }
}
