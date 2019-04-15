package com.example.crimson.crimson.Memento;

import android.widget.RadioButton;

public interface Originator {
    public void restore(Memento memento);
    public Memento createMemento();
}
