package com.example.crimson.crimson.Controller.Fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.crimson.crimson.Controller.DashboardActivity;
import com.example.crimson.crimson.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.*;

public class goals_fragmentTest
{
    @Rule
    public ActivityTestRule<DashboardActivity> mTest = new ActivityTestRule<DashboardActivity>(DashboardActivity.class);

    public DashboardActivity dashActivityTest = null;


    @Before
    public void setUp() throws Exception {

        dashActivityTest = mTest.getActivity();
    }

    @Test
    public void launchTest()
    {
        RelativeLayout container = (RelativeLayout)dashActivityTest.findViewById(R.id.goals_layout);
        assertNotNull(container);

        expense_fragment exp_frag = new expense_fragment();

        dashActivityTest.getSupportFragmentManager().beginTransaction().add(container.getId(), exp_frag).commitAllowingStateLoss();

        View view = exp_frag.getView().findViewById(R.id.goalsPeriod);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {

        mTest = null;
        dashActivityTest = null;
    }
}