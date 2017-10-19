package r201.vista.lake.listapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import r201.vista.lake.listapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment {


    public ReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getView() == null) {
            View v = inflater.inflate(R.layout.fragment_reminder,container,false);

            return  v;
        }else {
            return getView();
        }
    }

}
