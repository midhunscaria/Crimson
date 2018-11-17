package com.example.crimson.crimson;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.content.DialogInterface;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private Fragment fragment;
    goals_fragment goals_fragment;
    dash_fragment dash_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null)
        {
            fragment = new dash_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPanel, fragment).commit();
//            goals_fragment.register(dash_fragment);
        }


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this).setTitle("Exit").setMessage("Are you sure you want to exit?").setNegativeButton(android.R.string.no, null).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout()
    {
        mAuth.signOut();
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dash) {
            fragment = new dash_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPanel, fragment).commit();
        } else if (id == R.id.nav_expense) {
            fragment = new expense_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPanel, fragment).commit();
        } else if (id == R.id.nav_analysis) {
            fragment = new analysis_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPanel, fragment).commit();
        } else if (id == R.id.nav_dues) {
            fragment = new dues_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPanel, fragment).commit();
        } else if (id == R.id.nav_goals) {
            fragment = new goals_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPanel, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
