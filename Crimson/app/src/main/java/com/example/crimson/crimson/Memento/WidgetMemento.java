package com.example.crimson.crimson.Memento;

import android.widget.RadioButton;

public class WidgetMemento implements Memento {

    RadioButton button;

    public Object getState()
    {
        return this.button;
    }

    public void setState(Object button)
    {
        this.button = (RadioButton)button;
    }
}
