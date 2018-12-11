package com.example.crimson.crimson.Controller.Fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crimson.crimson.Controller.BuilderClasses.Goals;
import com.example.crimson.crimson.Model.DAO;
import com.example.crimson.crimson.NullChecker.NodeInfo;
import com.example.crimson.crimson.R;
import com.example.crimson.crimson.Utility.Util;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class dash_fragment extends Fragment{

    public View parentHolder;

    public TextView nameLabel, ageLabel, occupationLabel, salaryLabel, subsLabel, typeLabel;
    public ListView goalsListView;

    public String nameStr, ageStr, occupationStr, salaryStr, subsStr;
    public String goalAmtStr, goalPeriodStr, goalTargetStr;
    public static String typeStr;
    public String user_id_fb;
    public String duesEmail, duesAmt, duesPeriodicName, duesPeriodicAmount;
    public String user_identifier = FirebaseAuth.getInstance().getUid();
    public String flag = null;

    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference userProfileRef = mDbRef.child("User_Details");
    public DatabaseReference duesOneTimeRef = mDbRef.child("Dues").child("One Time");
    public DatabaseReference duesPeriodicRef = mDbRef.child("Dues").child("Periodic");
    public DatabaseReference goalsRef = mDbRef.child("Goals");

    public Map<String, Float> dues_one_time_map = new HashMap<String, Float>();
    public Map<String, Float> dues_periodic_map = new HashMap<String, Float>();
    public List<String> goals = new ArrayList<String>();

    public ArrayAdapter goalListAdapter;

    public Float duesAmtFloat, duesOneTimeAmtFloat, temp_amount;

    public PieChart duesOneTimeChart, duesPeriodicChart;
    public PieData data;
    public PieDataSet dataSet;

    public List<PieEntry> pieEntries = new ArrayList<>();

    public Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_dash_fragment, container, false);

        nameLabel = (TextView)parentHolder.findViewById(R.id.dashName);
        ageLabel = (TextView)parentHolder.findViewById(R.id.dashAge);
        occupationLabel = (TextView)parentHolder.findViewById(R.id.dashOccupation);
        salaryLabel = (TextView)parentHolder.findViewById(R.id.dashSalary);
        subsLabel = (TextView)parentHolder.findViewById(R.id.dashSubs);
        typeLabel = (TextView)parentHolder.findViewById(R.id.dashSubsType);
        duesOneTimeChart = (PieChart)parentHolder.findViewById(R.id.dues_one_time_pie);
        duesPeriodicChart = (PieChart)parentHolder.findViewById(R.id.dues_periodic_pie);
        goalsListView = (ListView)parentHolder.findViewById(R.id.goal_listview_id);

        loadGoals(user_identifier);

        getUserProfileDetails(user_identifier);

        drawDuesGraph();

        return parentHolder;
    }

    public void loadGoals(final String user_identifier)
    {
        goalsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    user_id_fb = ds.child("userIdentifier").getValue(String.class);
                    if(user_id_fb.equals(user_identifier)) {
                        goalAmtStr = ds.child("goalAmount").getValue(String.class);
                        goalPeriodStr = ds.child("goalPeriod").getValue(String.class);
                        goalTargetStr = ds.child("goalTarget").getValue(String.class);

                        goals.add(goalAmtStr+goalPeriodStr+goalTargetStr);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setGoals("Not Null");
    }

    public void getUserProfileDetails(final String user_identifier)
    {
        userProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    user_id_fb = ds.child("user_identifier").getValue(String.class);

                    if(user_id_fb.equals(user_identifier))
                    {
                        nameStr = ds.child("name").getValue(String.class);
                        ageStr = ds.child("age").getValue(String.class);
                        occupationStr = ds.child("occupation").getValue(String.class);
                        salaryStr = ds.child("annualIncome").getValue(String.class);
                        subsStr = ds.child("userType").getValue(String.class);

                        if(subsStr.equals("true"))
                        {
                            typeStr = ds.child("subsType").getValue(String.class);
                        }
                        else
                        {
                            subsStr = "Free Memeber";
                            typeStr = "";
                        }

                        break;
                    }
                }

                setProfile();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setGoals(String flag)
    {
        if(!NodeInfo.checkDbRefNull(flag)) {
            goalListAdapter = new ArrayAdapter(parentHolder.getContext(), R.layout.list_item, goals);
            goalsListView.setAdapter(goalListAdapter);
        }
        else{
            Util.makeToast(parentHolder.getContext(), "You have no Goals added yet!").show();
        }
    }

    public void setProfile()
    {
        nameLabel.setText(nameStr);
        ageLabel.setText(ageStr);
        occupationLabel.setText(occupationStr);
        salaryLabel.setText(salaryStr);
        subsLabel.setText(subsStr);
        typeLabel.setText(typeStr);
    }

    public void drawDuesGraph()
    {
        drawOneTimeGraph(duesOneTimeRef);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawPeriodicGraph(duesPeriodicRef);
            }
        },1000);
    }

    public void drawOneTimeGraph(DatabaseReference duesOneTimeRef)
    {
        dues_one_time_map.clear();

        duesOneTimeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    user_id_fb = ds.child("user_identifier").getValue(String.class);
                    duesAmt = ds.child("amount").getValue(String.class);
                    duesEmail = ds.child("email_id").getValue(String.class);

                    duesOneTimeAmtFloat = Float.parseFloat(duesAmt);

                    if(user_identifier.equals(user_id_fb))
                    {
                        if(dues_one_time_map.containsKey(duesEmail))
                        {
                            temp_amount = dues_one_time_map.get(duesEmail);
                            temp_amount = duesOneTimeAmtFloat + temp_amount;
                            dues_one_time_map.put(duesEmail, temp_amount);
                        }
                        else
                        {
                            dues_one_time_map.put(duesEmail, duesOneTimeAmtFloat);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createMap(dues_one_time_map, duesOneTimeChart, "One Time Dues");
            }
        },2000);


    }

    public void drawPeriodicGraph(DatabaseReference duesPeriodicRef)
    {
        dues_periodic_map.clear();

        duesPeriodicRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    user_id_fb = ds.child("user_identifier").getValue(String.class);
                    duesPeriodicAmount = ds.child("amount").getValue(String.class);
                    duesPeriodicName = ds.child("name").getValue(String.class);

                    duesAmtFloat = Float.parseFloat(duesPeriodicAmount);

                    if(user_identifier.equals(user_id_fb))
                    {
                        if(dues_periodic_map.containsKey(duesPeriodicName))
                        {
                            temp_amount = dues_periodic_map.get(duesPeriodicName);
                            temp_amount = duesAmtFloat + temp_amount;
                            dues_periodic_map.put(duesPeriodicName, temp_amount);
                        }
                        else
                        {
                            dues_periodic_map.put(duesPeriodicName, duesAmtFloat);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createMap(dues_periodic_map, duesPeriodicChart, "Periodic Dues");
            }
        },2000);
    }

    public void createMap(Map<String, Float> map, PieChart chart, String description)
    {
        pieEntries.clear();

        for(Map.Entry<String, Float> entry :  map.entrySet())
        {
            pieEntries.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()));
        }

        dataSet = new PieDataSet(pieEntries, ""+description);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        chart.clear();
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5,10,5,2);

        chart.setData(data);
        chart.setRotationEnabled(false);
        chart.setTouchEnabled(false);
        chart.invalidate();
    }

}
