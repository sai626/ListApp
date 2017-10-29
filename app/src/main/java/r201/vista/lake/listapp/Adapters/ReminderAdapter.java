package r201.vista.lake.listapp.Adapters;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;
import r201.vista.lake.listapp.DialogFragments.ReminderNameEditDF;
import r201.vista.lake.listapp.Interfaces.OnUpdateListener;
import r201.vista.lake.listapp.JavaObjects.Reminder;
import r201.vista.lake.listapp.R;

/**
 * Created by saii on 20/10/17.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private Context context;
    private List<Reminder> reminderList;
    private int maxInt;
    private FragmentManager fragmentManager;
    private Realm realm;

    public ReminderAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.realm = Realm.getDefaultInstance();

        RealmResults<Reminder> r = realm.where(Reminder.class).findAll();
        reminderList = realm.copyFromRealm(r);
        maxInt = reminderList.size();
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
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
        Calendar time, temp;
        int pos, viewType;

        SimpleDateFormat sdf = new SimpleDateFormat("h:m a  dd MMM/yy", Locale.US);

        public ReminderViewHolder(View itemView, int viewType) {

            super(itemView);
            this.viewType = viewType;

            if (viewType == 0) {
                name = itemView.findViewById(R.id.reminderName);
                date = itemView.findViewById(R.id.reminderDate);
                isActive = itemView.findViewById(R.id.active);

                isActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        r.setActive(isChecked);

                        realm.beginTransaction();

                    }
                });

            }else  {
                add = itemView.findViewById(R.id.addReminder);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        realm.beginTransaction();
                        Reminder r = realm.createObject(Reminder.class);
                        r.setId(maxInt);
                        r.setReminderName("New Reminder");
                        r.setDate(Calendar.getInstance().getTime());
                        r.setActive(true);
                        realm.commitTransaction();

                        reminderList.add(r);
                        notifyItemInserted(reminderList.size()-1);
                        maxInt++;
                    }
                });
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

            time = Calendar.getInstance();
            time.setTime(r.getDate());
            temp = (Calendar) time.clone();
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showDatePicker();
                }
            });

        }

        private void showDatePicker(){
            DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    temp.set(year, month, dayOfMonth);
                    showTimePicker();
                }
            }, time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH));

            dialog.show();

        }

        private void showTimePicker(){
            TimePickerDialog dialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    temp.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    temp.set(Calendar.MINUTE, minute);
                    showNameEditor();
                }
            }, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), false);

            dialog.show();
        }

        private void showNameEditor() {

            ReminderNameEditDF d = new ReminderNameEditDF();
            Bundle b = new Bundle();
            b.putString("name", r.getReminderName());
            d.setArguments(b);
            d.setOnUpdateListener(new OnUpdateListener<String>() {
                @Override
                public void update(String s) {
                    r.setReminderName(s);
                    r.setDate(temp.getTime());
                    time = (Calendar) temp.clone();
                    notifyItemChanged(pos);
                }
            });
            d.show(fragmentManager,"Name");
        }

        public void bindAdd(int pos) {
            this.pos = pos;
        }

    }
}
