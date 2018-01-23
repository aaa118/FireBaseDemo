package com.example.aaavs.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static com.example.aaavs.myapplication.MainActivity.mAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loadView();
    }

    private void loadView() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btSignUp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())  {
            case R.id.btSignUp:
                signUpMethod();
                break;
        }
    }

    private void signUpMethod() {
        String emailString = etUsername.getText().toString().trim();
        String passwordString = etPassword.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(emailString,passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())    {
                    // suer is regeisted succefully
                    Toast.makeText(SignUp.this, "Registered!!!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplication(),SignInPage.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(SignUp.this, "COuld not Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
