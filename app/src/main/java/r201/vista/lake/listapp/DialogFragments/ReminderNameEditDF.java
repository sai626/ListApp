package r201.vista.lake.listapp.DialogFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import r201.vista.lake.listapp.Interfaces.OnUpdateListener;
import r201.vista.lake.listapp.R;

/**
 * Created by saii on 22/10/17.
 */

public class ReminderNameEditDF extends DialogFragment {

    private OnUpdateListener<String> onUpdateListener;
    private EditText editText;
    private Button update;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.reminder_editor_name, null);

        builder.setView(v);

        editText = v.findViewById(R.id.reminderName);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setText(getArguments().getString("name"));
        update = v.findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUpdateListener != null){
                    onUpdateListener.update(editText.getText().toString());
                    dismiss();
                }
            }
        });
        return  builder.create();
    }

    public void setOnUpdateListener(OnUpdateListener<String> onUpdateListener){
        this.onUpdateListener = onUpdateListener;
    }
}
