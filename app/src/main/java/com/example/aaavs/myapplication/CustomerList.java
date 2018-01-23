package com.example.aaavs.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aaavs on 1/23/2018.
 */

public class CustomerList extends ArrayAdapter<Customer> {
    private Activity context;
    private List<Customer> customerList;



    public CustomerList(Activity context, List<Customer> customerList)  {
        super(context,R.layout.list_layout,customerList);
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewFName = listViewItem.findViewById(R.id.fName);
        TextView textViewLName = listViewItem.findViewById(R.id.lName);

        Customer customer =  customerList.get(position);
        textViewFName.setText(customer.getFirstName());
        textViewLName.setText(customer.getLastname());

        return listViewItem;
    }
}
