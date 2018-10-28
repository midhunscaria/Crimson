package com.example.crimson.crimson;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class dash_fragment extends Fragment {

    public Activity referenceActivity;
    public View parentHolder;
    public TextView bait;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        referenceActivity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_dash_fragment, container, false);
        bait = (TextView)parentHolder.findViewById(R.id.textdash);

        bait.setText(""+"VIMAALLL");
        return parentHolder;
    }

}
