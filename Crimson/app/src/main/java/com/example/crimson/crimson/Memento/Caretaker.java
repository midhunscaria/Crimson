package com.example.crimson.crimson.Memento;

import android.widget.RadioButton;

import java.util.Stack;

public class Caretaker {
    private Stack mementoStack, originatorStack;
    private OriginatorWidget originatorWidget;

    public Caretaker()
    {
        mementoStack = new Stack();
        originatorStack = new Stack();
    }

    public void setWidget(OriginatorWidget originatorWidget)
    {
        this.originatorWidget = originatorWidget;
    }

    public void undoOperation()
    {
        Originator originator = (Originator)originatorStack.pop();
        originator.restore((Memento)mementoStack.pop());
    }

    public void setRadioValue(RadioButton button)
    {
        mementoStack.push(originatorWidget.createMemento());
        originatorStack.push(originatorWidget);

        originatorWidget.setButton(button);
    }

    public RadioButton getRadioValue()
    {
        return originatorWidget.getButton();
    }
}
