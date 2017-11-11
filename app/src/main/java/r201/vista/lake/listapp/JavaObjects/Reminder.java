package r201.vista.lake.listapp.JavaObjects;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by saii on 20/10/17.
 */

public class Reminder extends RealmObject {

    @PrimaryKey
    private long id;
    private String reminderName;
    private Date date;
    private boolean isActive;

    public Reminder(String reminderName, Date date, boolean isActive) {
        this.reminderName = reminderName;
        this.date = date;
        this.isActive = isActive;
    }

    public Reminder() {
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
