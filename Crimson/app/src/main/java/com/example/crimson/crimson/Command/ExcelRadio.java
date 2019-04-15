package com.example.crimson.crimson.Command;

import android.util.Log;
import android.widget.RadioButton;

import com.example.crimson.crimson.Memento.Caretaker;

public class ExcelRadio
{
    public void saveState(Caretaker caretaker, RadioButton radioButton)
    {
        caretaker.setRadioValue(radioButton);
        Log.i("Command Excel: ", "Saved State");
    }
}
