package r201.vista.lake.listapp.TestObjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import r201.vista.lake.listapp.JavaObjects.Reminder;

/**
 * Created by saii on 21/10/17.
 */

public class ReminderObjects {

    private List<Reminder> reminderList = new ArrayList<>();

    public ReminderObjects() {
        Reminder r1 = new Reminder("Test Reminder1", Calendar.getInstance().getTime(), true);
        Calendar c = Calendar.getInstance();
        c.set(1995, 3, 10, 5, 10, 50);
        Reminder r2 = new Reminder("Test2", c.getTime(), false);
        reminderList.add(r1);
        reminderList.add(r2);
    }

    public List<Reminder> getReminderList() {
        return reminderList;
    }
}
