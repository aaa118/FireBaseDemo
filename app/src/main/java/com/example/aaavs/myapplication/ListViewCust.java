package com.example.aaavs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListViewCust extends AppCompatActivity {

    ListView listViewCustomers;
    List<Customer> customerList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_cust);

        databaseReference = FirebaseDatabase.getInstance().getReference("INfo");

        listViewCustomers = findViewById(R.id.customerList);
        customerList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                customerList.clear();
                for (DataSnapshot custSnampshot:dataSnapshot.getChildren()) {
                    Customer customer = custSnampshot.getValue(Customer.class);
                    customerList.add(customer);
                }
                CustomerList adapter = new CustomerList(ListViewCust.this,customerList);
                listViewCustomers.setAdapter(adapter);
                Log.i("info", String.valueOf(adapter));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
