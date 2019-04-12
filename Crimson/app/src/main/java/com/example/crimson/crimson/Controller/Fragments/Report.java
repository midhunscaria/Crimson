package com.example.crimson.crimson.Controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.crimson.crimson.PluggableAdapter.ExcelReport;
import com.example.crimson.crimson.PluggableAdapter.PDFReport;
import com.example.crimson.crimson.PluggableAdapter.PluggableAdapter;
import com.example.crimson.crimson.R;
import com.example.crimson.crimson.Utility.Util;

public class Report extends Fragment {

    public View parentHolder;

    public Button generate_report_button;
    public RadioButton excel_radio;
    public RadioButton pdf_radio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        parentHolder = inflater.inflate(R.layout.fragment_report, container, false);

        excel_radio = (RadioButton)parentHolder.findViewById(R.id.Excel_Report);
        pdf_radio = (RadioButton)parentHolder.findViewById(R.id.PDF_Report);

        generate_report_button = (Button)parentHolder.findViewById(R.id.generate_report);

        generate_report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(excel_radio.isChecked())
                {
                    try {
                        Util.makeToast(parentHolder.getContext(), new PluggableAdapter(new ExcelReport()).generateReport()).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(pdf_radio.isChecked())
                {
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
