package com.example.crimson.crimson.Command;

import android.widget.RadioButton;

import com.example.crimson.crimson.Memento.Caretaker;

public class PDFRadioCommand implements Command {

    public Caretaker caretaker;
    public RadioButton radioButton;
    public PDFRadio pdfRadio;

    public PDFRadioCommand(PDFRadio pdfRadio, Caretaker caretaker, RadioButton radioButton)
    {
        this.caretaker = caretaker;
        this.radioButton = radioButton;
        this.pdfRadio = pdfRadio;
    }

    @Override
    public void execute() {
       pdfRadio.saveState(this.caretaker, this.radioButton);
    }
}
