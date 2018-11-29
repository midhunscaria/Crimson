package com.example.crimson.crimson.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.example.crimson.crimson.R;
import com.example.crimson.crimson.Utility.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText resetEmail;
    private Button resetButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetEmail = (EditText)findViewById(R.id.editResetPassword);
        resetButton = (Button)findViewById(R.id.buttonResetPassword);

        mAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resetPassword();

                startActivity(new Intent(ResetPasswordActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void resetPassword(){

        String resetEmailID = resetEmail.getText().toString();

        if(TextUtils.isEmpty(resetEmailID))
        {
            Util.makeToast(ResetPasswordActivity.this, "Please provide an Email ID").show();
        }
        else
        {
            mAuth.sendPasswordResetEmail(resetEmailID).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful())
                    {
                        Util.makeToast(ResetPasswordActivity.this, "We've sent a password reset link to your Email ID").show();
                    }
                    else
                    {
                        Util.makeToast(ResetPasswordActivity.this, "Unable to send password reset email").show();
                    }
                }
            });
        }
    }
}
