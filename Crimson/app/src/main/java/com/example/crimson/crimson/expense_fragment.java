package com.example.crimson.crimson;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.ArrayList;

public class expense_fragment extends Fragment {

    public View parentHolder;

    public EditText amount;
    public Spinner category_spinner;
    public EditText expense_place;
    public Button add_expense_button;

    public double amount_double;
    public String category_spinner_str;
    public String expense_place_str;
    private String user_identifier;

    public DatabaseReference mDbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_expense_fragment, container, false);

        amount = (EditText)parentHolder.findViewById(R.id.expense_amount_button);
        category_spinner = (Spinner)parentHolder.findViewById(R.id.expense_category_spinner);
        expense_place = (EditText)parentHolder.findViewById(R.id.expense_place);
        add_expense_button = (Button)parentHolder.findViewById(R.id.add_expense_button);

        add_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                amount_double = Double.parseDouble(amount.getText().toString());
                category_spinner_str = category_spinner.getSelectedItem().toString();
                expense_place_str = expense_place.getText().toString();
                user_identifier = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                if(TextUtils.isEmpty(amount.getText().toString()) || TextUtils.isEmpty(category_spinner_str) || TextUtils.isEmpty(expense_place_str))
                {
                    Toast.makeText(parentHolder.getContext(), "Please Enter All Information!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Expense expense_object = new Expense.Builder().setAmount(amount_double).setCategory(category_spinner_str).setPlace(expense_place_str).setUserIdentifier(user_identifier).create();

                    Toast.makeText(parentHolder.getContext(), ""+expense_object.getCategory()+expense_object.getAmount()+expense_object.getPlace(), Toast.LENGTH_LONG).show();

                    mDbRef = FirebaseDatabase.getInstance().getReference();

                    mDbRef.child("Expenses").push().setValue(expense_object).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(parentHolder.getContext(), "Created Record", Toast.LENGTH_LONG).show();

                                amount.setText("");
                                category_spinner.setSelection(0);
                                expense_place.setText("");
                            }
                            else
                            {
                                Toast.makeText(parentHolder.getContext(), "Error in record creation!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });

        return parentHolder;
    }

}
