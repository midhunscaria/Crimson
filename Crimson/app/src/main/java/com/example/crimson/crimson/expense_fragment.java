package com.example.crimson.crimson;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link expense_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link expense_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class expense_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_fragment, container, false);
    }

}
