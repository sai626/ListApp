package r201.vista.lake.listapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
    private ItemTouchHelper.SimpleCallback callback = new
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int pos = viewHolder.getAdapterPosition();
            reminderAdapter.removeReminder(pos);
        }
    };
    private FloatingActionButton fab;
    private ItemTouchHelper touchHelper;

    public ReminderFragment() {
        //reminderList = new ReminderObjects().getReminderList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getView() == null) {
            View v = inflater.inflate(R.layout.fragment_reminder,container,false);

            fab = v.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reminderAdapter.removeAllReminders();
                }
            });

            reminderView = v.findViewById(R.id.reminders);
            reminderAdapter = new ReminderAdapter(getContext(), getChildFragmentManager());

            reminderView.setAdapter(reminderAdapter);
            reminderView.setLayoutManager(new LinearLayoutManager(getContext()));
            touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(reminderView);


            return  v;
        }else {
            return getView();
        }
    }

}
