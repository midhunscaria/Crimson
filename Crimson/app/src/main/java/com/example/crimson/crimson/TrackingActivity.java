package com.example.crimson.crimson;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrackingActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public FirebaseDatabase mDb;
    public FirebaseUser user;
    public DatabaseReference mDbRef;
    public FirebaseAuth.AuthStateListener mAtuthListener;

    public ArrayList<String> expense_list;
    public ArrayAdapter adapter;

    public ListView expenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

//        expenseList = (ListView)findViewById(R.id.expense_list);
//
//        mAuth = FirebaseAuth.getInstance();
//        mDb = FirebaseDatabase.getInstance();
//        mDbRef = mDb.getReference();

//        mAtuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                user = mAuth.getCurrentUser();

//                if(user != null)
//                {
//                    Toast.makeText(TrackingActivity.this, "Signed In", Toast.LENGTH_LONG).show();
//                }
//            }
//        };

//        mDbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                showData(dataSnapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    public void showData(DataSnapshot dataSnapshot)
//    {
//        expense_list = new ArrayList<String>();
//
//        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//            ExpenseInformation exp_info = new ExpenseInformation();
//            exp_info.setAmt(ds.child(user.getUid()).getValue(ExpenseInformation.class).getAmt());
//            exp_info.setCtry(ds.child(user.getUid()).getValue(ExpenseInformation.class).getCtry());
//
//            expense_list.add(exp_info.getAmt());
//            expense_list.add(exp_info.getCtry());
//
//            adapter = new ArrayAdapter(TrackingActivity.this, R.layout.activity_tracking, expense_list);
//            expenseList.setAdapter(adapter);
//
//        }
    }

}

