package com.example.aaavs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignInPage extends AppCompatActivity implements View.OnClickListener {
    EditText etFirstName,etLastName;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        loadView();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("INfo");


    }

    private void loadView() {
        etFirstName = findViewById(R.id.etFirstName1);
        etLastName = findViewById(R.id.etLastName1);

        findViewById(R.id.btAddInfo).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())  {
            case R.id.btAddInfo:
                addInfo();
                break;
        }
    }

    private void addInfo() {
        String fName = etFirstName.getText().toString().trim();
        String lName = etLastName.getText().toString().trim();

        if (!TextUtils.isEmpty(fName)&&!TextUtils.isEmpty(lName))   {
                String id = mDatabaseReference.push().getKey();
                Customer customer = new Customer(id,fName,lName);
                mDatabaseReference.child(id).setValue(customer);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        }   else {
            Toast.makeText(this, "Enter full info", Toast.LENGTH_SHORT).show();
        }
    }
}
