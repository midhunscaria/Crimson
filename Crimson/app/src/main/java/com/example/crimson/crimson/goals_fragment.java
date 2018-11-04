package com.example.crimson.crimson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class goals_fragment extends Fragment {
    public View parentHolder;
    public EditText goalTarget;
    public EditText goalAmount;
    public EditText goalPeriod;
    public Button creategoal;

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      parentHolder=inflater.inflate(R.layout.fragment_dues_fragment, container, false);
      goalTarget=(EditText)parentHolder.findViewById(R.id.goalsTarget);
      goalAmount=(EditText)parentHolder.findViewById(R.id.goalsAmount);
      goalPeriod=(EditText)parentHolder.findViewById(R.id.goalsPeriod);
      creategoal=(Button) parentHolder.findViewById(R.id.createGoals);

      creategoal.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


          }
      });

        return parentHolder;
    }
//
//