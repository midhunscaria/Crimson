package com.example.crimson.crimson;

import java.util.Map;
import android.os.Handler;
import android.widget.Toast;

public class PieChartUpdate implements Observer
{

    public String user_id;
    public expense_fragment e_frag;

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
