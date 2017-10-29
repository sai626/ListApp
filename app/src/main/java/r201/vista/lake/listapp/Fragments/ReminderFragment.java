package r201.vista.lake.listapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import r201.vista.lake.listapp.Adapters.ReminderAdapter;
import r201.vista.lake.listapp.JavaObjects.Reminder;
import r201.vista.lake.listapp.R;
import r201.vista.lake.listapp.TestObjects.ReminderObjects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment {

    private RecyclerView reminderView;
    private ReminderAdapter reminderAdapter;

    public ReminderFragment() {
        //reminderList = new ReminderObjects().getReminderList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getView() == null) {
            View v = inflater.inflate(R.layout.fragment_reminder,container,false);

            reminderView = v.findViewById(R.id.reminders);
            reminderAdapter = new ReminderAdapter(getContext(), getChildFragmentManager());

            reminderView.setAdapter(reminderAdapter);
            reminderView.setLayoutManager(new LinearLayoutManager(getContext()));

            return  v;
        }else {
            return getView();
        }
    }

}
