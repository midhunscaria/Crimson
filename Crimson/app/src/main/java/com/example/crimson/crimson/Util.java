package com.example.crimson.crimson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
