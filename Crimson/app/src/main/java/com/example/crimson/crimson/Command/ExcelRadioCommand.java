package com.example.crimson.crimson.Command;

import android.widget.RadioButton;

import com.example.crimson.crimson.Memento.Caretaker;

public class ExcelRadioCommand implements Command{

    public Caretaker caretaker;
    public RadioButton radioButton;

    public ExcelRadio excelRadio;

    public ExcelRadioCommand(ExcelRadio excelRadio, Caretaker caretaker, RadioButton radioButton)
    {
        this.caretaker = caretaker;
        this.radioButton = radioButton;
        this.excelRadio = excelRadio;
    }

    @Override
    public void execute() {
       excelRadio.saveState(this.caretaker, this.radioButton);
    }
}
