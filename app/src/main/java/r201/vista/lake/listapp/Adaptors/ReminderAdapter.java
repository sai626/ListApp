package r201.vista.lake.listapp.Adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import r201.vista.lake.listapp.JavaObjects.Reminder;
import r201.vista.lake.listapp.R;

/**
 * Created by saii on 20/10/17.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private Context context;
    private ViewGroup parent;
    private List<Reminder> reminderList;

    public ReminderAdapter(Context context, ViewGroup parent, List<Reminder> reminderList) {
        this.context = context;
        this.parent = parent;
        this.reminderList = reminderList;
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        Log.d("viewType", String.valueOf(viewType));
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.reminder_layout, parent, false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.reminder_add_layout, parent, false);
        }
        return new ReminderViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {

        if (position<reminderList.size()) {
            holder.bindReminder(reminderList.get(position), position);
        }else {
            holder.bindAdd(position);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("RemindersCount", String.valueOf(reminderList.size() + 1));
        return reminderList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (reminderList.size()>position){
            return 0;
        }else {
            return 1;
        }
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder {

        Reminder r;
        TextView name, date;
        Switch isActive;
        ImageView add;
        int pos, viewType;

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a  MMM dd");

        public ReminderViewHolder(View itemView, int viewType) {

            super(itemView);
            this.viewType = viewType;

            if (viewType == 0) {
                name = itemView.findViewById(R.id.reminderName);
                date = itemView.findViewById(R.id.reminderDate);
                isActive = itemView.findViewById(R.id.active);
            }else  {
                add = itemView.findViewById(R.id.addReminder);
            }
        }

        public void bindReminder(Reminder r, int pos){
            this.r = r;
            this.pos = pos;

            name.setText(r.getReminderName());
            String dateString = sdf.format(r.getDate());

            SpannableString string = new SpannableString(dateString);
            string.setSpan(new RelativeSizeSpan(.7f), dateString.indexOf(' '), dateString.indexOf(' ')+ 3, 0);
            date.setText(string);
            isActive.setChecked(r.isActive());
        }

        public void bindAdd(int pos) {
            this.pos = pos;
        }
    }
}
