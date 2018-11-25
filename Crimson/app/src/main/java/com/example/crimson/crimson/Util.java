package com.example.crimson.crimson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Util {

    public static void navigateFragment(Fragment fragment, boolean addTOBackStack, AppCompatActivity activity, String Tag){

      if (addTOBackStack){
          activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPanel, fragment, Tag).addToBackStack(Tag).commit();
      }else {
          activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPanel, fragment).commit();
      }

    }

    public static Toast makeToast(Context context, String message)
    {
        return Toast.makeText(context, ""+message, Toast.LENGTH_LONG);
    }

}
