package com.example.crimson.crimson.Controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.crimson.crimson.Command.ExcelRadio;
import com.example.crimson.crimson.Command.ExcelRadioCommand;
import com.example.crimson.crimson.Command.PDFRadio;
import com.example.crimson.crimson.Command.PDFRadioCommand;
import com.example.crimson.crimson.Command.RemoteControl;
import com.example.crimson.crimson.Command.Undo;
import com.example.crimson.crimson.Command.UndoCommand;
import com.example.crimson.crimson.Memento.Caretaker;
import com.example.crimson.crimson.Memento.OriginatorWidget;
import com.example.crimson.crimson.PluggableAdapter.ExcelReport;
import com.example.crimson.crimson.PluggableAdapter.PDFReport;
import com.example.crimson.crimson.PluggableAdapter.PluggableAdapter;
import com.example.crimson.crimson.R;
import com.example.crimson.crimson.Utility.Util;

import java.util.EmptyStackException;

public class Report extends Fragment {

    public View parentHolder;

    public Button generate_report_button, undo_button;
    public RadioButton excel_radio;
    public RadioButton pdf_radio;
    public RadioGroup radioGroup;

    public RemoteControl remoteControl;

    public OriginatorWidget originatorWidget;
    public Caretaker caretaker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        parentHolder = inflater.inflate(R.layout.fragment_report, container, false);

        originatorWidget = new OriginatorWidget();
        caretaker = new Caretaker();

        caretaker.setWidget(originatorWidget);

        excel_radio = (RadioButton)parentHolder.findViewById(R.id.Excel_Report);
        pdf_radio = (RadioButton)parentHolder.findViewById(R.id.PDF_Report);

        radioGroup = (RadioGroup)parentHolder.findViewById(R.id.Radio_Group);

        generate_report_button = (Button)parentHolder.findViewById(R.id.generate_report);
        undo_button = (Button)parentHolder.findViewById(R.id.undo_button);

        remoteControl = new RemoteControl();

        undo_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pdf_radio.setChecked(false);
                    excel_radio.setChecked(false);

                    try
                    {
                        remoteControl.setCommand(new UndoCommand(caretaker, new Undo()));
                        remoteControl.buttonPressed();

                        Util.makeToast(parentHolder.getContext(), "Reseting to previous state: " + caretaker.getRadioValue().getText().toString()).show();
                        caretaker.getRadioValue().setChecked(true);
                    }catch (EmptyStackException e)
                    {
                        Util.makeToast(parentHolder.getContext(), "Cannot Undo!").show();
                    }

                }
        });

        generate_report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(excel_radio.isChecked())
                {
                    remoteControl.setCommand(new ExcelRadioCommand(new ExcelRadio(), caretaker, excel_radio));
                    remoteControl.buttonPressed();

                    try {
                        Util.makeToast(parentHolder.getContext(), new PluggableAdapter(new ExcelReport()).generateReport()).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(pdf_radio.isChecked())
                {

                    remoteControl.setCommand(new PDFRadioCommand(new PDFRadio(), caretaker, pdf_radio));
                    remoteControl.buttonPressed();

                    try {
                        Util.makeToast(parentHolder.getContext(), new PluggableAdapter(new PDFReport()).generateReport()).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return parentHolder;
    }

}
