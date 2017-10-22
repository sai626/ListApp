package r201.vista.lake.listapp.DialogFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import r201.vista.lake.listapp.R;

/**
 * Created by saii on 22/10/17.
 */

public class ReminderNameEditDF extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.reminder_editor_name, null);

        builder.setView(v);

        return  builder.create();
    }
}
