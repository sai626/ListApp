package r201.vista.lake.listapp.JavaObjects;

import java.util.Date;

/**
 * Created by saii on 20/10/17.
 */

public class Reminder {

    private String reminderName;
    private Date date;
    private boolean isActive;

    public Reminder(String reminderName, Date date, boolean isActive) {
        this.reminderName = reminderName;
        this.date = date;
        this.isActive = isActive;
    }

    public String getReminderName() {
        return reminderName;
    }

    public Date getDate() {
        return date;
    }

    public boolean isActive() {
        return isActive;
    }

    public byte[] getBytes(){
        String buffer = reminderName + "\n" + String.valueOf(date.getTime()) + "\n" + isActive;
        return buffer.getBytes();
    }

    public static Reminder getReminder(byte[] array){
        String str = array.toString();
        String temp[] = str.split("\n");

        String name = temp[0];
        Date date = new Date(Long.parseLong(temp[1]));
        boolean active = Boolean.getBoolean(temp[2]);

        return new Reminder(name, date, active);
    }
}
