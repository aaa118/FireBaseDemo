package com.example.aaavs.myapplication;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;
    public static FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    private AdView mAdView;
    EditText etUsername, etPassword;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadViews();

        mAdView = findViewById(R.id.adView);
        com.google.android.gms.ads.AdRequest adRequest1 = new com.google.android.gms.ads.AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);


        MobileAds.initialize(this, "ca-app-pub-4742420110081688~5578894897");

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("Name");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Customer post = dataSnapshot.getValue(Customer.class);
                System.out.println((post));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)   {
                    Log.i(TAG,"Signed in");
                }   else    {
                    Log.i(TAG, "Failed");
                }

            }
        };
    }

    private void loadViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btLogin1).setOnClickListener(this);
        findViewById(R.id.btSignUp1).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListner != null)   {
            mAuth.removeAuthStateListener(mAuthListner);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())  {
            case R.id.btLogin1:
                loginMethod();
                break;
            case R.id.btSignUp1:
                signUpMethod();
                break;
        }
    }

    private void signUpMethod() {
        Intent intent1 = new Intent(getApplicationContext(),SignUp.class);
        startActivity(intent1);
    }

    private void loginMethod() {
        String emailString = etUsername.getText().toString().trim();
        String passwordString = etPassword.getText().toString().trim();

        if (!emailString.equals("") && !passwordString.equals(""))   {
            mAuth.signInWithEmailAndPassword(emailString,passwordString)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())   {
                                Toast.makeText(MainActivity.this, "Failed Sign In", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Signned In", Toast.LENGTH_SHORT).show();
//                                        mDatabaseReference.setValue("Hello Firebase");
//                                Customer customer1 = new Customer("Aditya","aaa1@gmail.com1");
//                                mDatabaseReference.setValue(customer1);
                                mDatabaseReference.push();
                                Intent intent1 = new Intent(getApplication(),SignInPage.class);
                                startActivity(intent1);

                            }
                        }
                    });
        }
    }
}
