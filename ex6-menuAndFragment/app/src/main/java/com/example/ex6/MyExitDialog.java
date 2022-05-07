package com.example.ex6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class MyExitDialog extends DialogFragment {


    /*create new instance for exitDialog and set a title.*/
    public static MyExitDialog newInstance(String title) {
        MyExitDialog myExitDialog = new MyExitDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        myExitDialog.setArguments(args);
        return myExitDialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Closing the application")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // kill the app
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        }
                )
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // close the dialog box
                                dialog.dismiss();
                            }
                        }
                )
                .create();
    }


}
