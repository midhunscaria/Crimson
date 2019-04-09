package com.example.crimson.crimson.Utility;

import android.content.Context;
import android.widget.Toast;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<PieEntry> pieEntries = new ArrayList<>();

    private Util()
    {

    }

    public static Toast makeToast(Context context, String message)
    {
        return Toast.makeText(context, ""+message, Toast.LENGTH_LONG);
    }


}
