package com.example.crimson.crimson.Controller;

import com.example.crimson.crimson.Controller.Fragments.expense_fragment;
import com.example.crimson.crimson.Interfaces.Observer;

public class PieChartUpdate implements Observer
{

    public String user_id;
    public expense_fragment e_frag;

    /**
     *
     * Observer Pattern
     *
     * The observer here calls the drawPieChart method in the subject in order to update
     * the pie chart when a new expense is added.
     */

    public PieChartUpdate(expense_fragment e_frag, String u_id)
    {
        this.e_frag = e_frag;
        this.user_id =  u_id;
    }

    @Override
    public void update()
    {
        this.e_frag.drawPieChart(this.user_id);
    }

}
