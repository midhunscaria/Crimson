package com.example.crimson.crimson.Memento;

import android.widget.RadioButton;

public class OriginatorWidget implements Originator {

    public RadioButton button;

    public RadioButton getButton()
    {
        return this.button;
    }

    public void setButton(RadioButton button)
    {
        this.button = button;
    }

    @Override
    public Memento createMemento() {
        WidgetMemento widgetMemento = new WidgetMemento();
        widgetMemento.setState(button);
        return widgetMemento;
    }

    @Override
    public void restore(Memento memento) {
        WidgetMemento widgetMementoRestore = (WidgetMemento)memento;
        this.button = ((RadioButton)widgetMementoRestore.getState());
    }

}
