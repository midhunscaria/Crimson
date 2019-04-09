package com.example.crimson.crimson.Controller.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;

import com.example.crimson.crimson.Controller.Interceptor.InterceptorDriver;
import com.example.crimson.crimson.Controller.PieChartUpdate;
import com.example.crimson.crimson.Interfaces.Observer;
import com.example.crimson.crimson.Interfaces.Subject;
import com.example.crimson.crimson.Model.DAO;
import com.example.crimson.crimson.Controller.BuilderClasses.Expense;
import com.example.crimson.crimson.R;
import com.example.crimson.crimson.Utility.Util;

//Libraries for MPAndroidChart

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class expense_fragment extends Fragment implements Subject {

    public View parentHolder;

    public EditText amount;
    public Spinner category_spinner;
    public EditText expense_place;
    public Button add_expense_button;
    public PieChart chart;

    public String amount_double_str;
    public String category_spinner_str;
    public String expense_place_str;
    public float temp_amount, amount_float;
    public String category, user_id_fb, amount_str;

    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference expenseRef = mDbRef.child("Expenses");

    public Map<String, Float> expense_map = new HashMap<String, Float>();
    public List<PieEntry> pieEntries = new ArrayList<>();

    public List<Observer> observers = new ArrayList<>();

    public Handler handler = new Handler();

    public PieDataSet dataSet;
    public PieData data;
    public static boolean expense_push_task_flag;
    public static Task<Void> expense_push_task;

    private String user_identifier = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder = inflater.inflate(R.layout.fragment_expense_fragment, container, false);

        //Registering Pie Chart Update function as an observer

        PieChartUpdate pieChartObserver = new PieChartUpdate(this, user_identifier);
        this.register(pieChartObserver);

        //Creating the Concrete Framework

        InterceptorDriver interceptor = new InterceptorDriver();
        this.register(interceptor);

        //Concrete Framework is an observer of expense_fragment. An event triggers the concrete framework.

        amount = (EditText)parentHolder.findViewById(R.id.expense_amount);
        category_spinner = (Spinner)parentHolder.findViewById(R.id.expense_category_spinner);
        expense_place = (EditText)parentHolder.findViewById(R.id.expense_place);
        add_expense_button = (Button)parentHolder.findViewById(R.id.add_expense_button);
        chart = (PieChart)parentHolder.findViewById(R.id.expense_chart);

        drawPieChart(user_identifier);

        add_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                amount_double_str = amount.getText().toString();
                category_spinner_str = category_spinner.getSelectedItem().toString();
                expense_place_str = expense_place.getText().toString();

                if(TextUtils.isEmpty(amount_double_str) || !TextUtils.isDigitsOnly(amount_double_str) || TextUtils.isEmpty(category_spinner_str) || TextUtils.isEmpty(expense_place_str))
                {
                    Util.makeToast(parentHolder.getContext(), "Please Enter All Information").show();
                }
                else
                {
                    Expense expense_object = new Expense.Builder().setAmount(amount_double_str).setCategory(category_spinner_str).
                            setPlace(expense_place_str).setUserIdentifier(user_identifier).create();

                    DAO.pushExpenses(expense_object,mDbRef);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (expense_push_task_flag) {
                                Util.makeToast(parentHolder.getContext(), "Expense Record Created Successfully").show();

                                try {
                                    notifyObservers();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                amount.setText("");
                                category_spinner.setSelection(0);
                                expense_place.setText("");
                            }
                            else  {
                                Util.makeToast(parentHolder.getContext(), "Error creating expense record").show();
                            }

                        }
                    }, 2000);

                }
            }
        });

        return parentHolder;
    }

    public void drawPieChart(final String u_id)
    {
        expense_map.clear();

        expenseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    user_id_fb = ds.child("user_identifier").getValue(String.class);
                    category = ds.child("category").getValue(String.class);
                    amount_str = ds.child("amount").getValue(String.class);

                    amount_float = Float.parseFloat(amount_str);

                    if(u_id.equals(user_id_fb))
                    {
                        if(expense_map.containsKey(category))
                        {
                            temp_amount = expense_map.get(category);
                            temp_amount = amount_float + temp_amount;
                            expense_map.put(category, temp_amount);
                        }
                        else
                        {
                            expense_map.put(category, amount_float);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Handle Cancel operations here
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createMap();
            }
        },2000);

    }

    /**
     * The createMap() function gets ready the dataSet that will be used to generate the charts.
     *
     * The dataSet variable contains the data that will be displayed within the chart, with the
     * help of the chart.setData() function.
     */

    public void createMap()
    {
        pieEntries.clear();

        if(expense_map.size() > 0) {

            for (Map.Entry<String, Float> entry : expense_map.entrySet()) {
                pieEntries.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()));
            }
        }

        dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        chart.clear();
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5,10,5,2);

        chart.setData(data);
        chart.invalidate();

    }

    /**
     *Observer Pattern
     *
     * Register, Unregister, notifyObservers are methods of Subject.
     *
     * register() - Registers a new Observer with the Subject
     * unregister() - Unregisters an Observer with the Subject
     * notifyObservers() - Notifies all Observers of any change in the Subject
     */
    @Override
    public void register(Observer o)
    {
        if(!observers.contains(o))
        {
            observers.add(o);
        }
    }

    @Override
    public void unregister(Observer o)
    {
        if(observers.contains(o))
        {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() throws IOException {
        for(Observer o : observers)
        {
            o.update();
        }
    }
}