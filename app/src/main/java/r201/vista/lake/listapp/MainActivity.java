package r201.vista.lake.listapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import r201.vista.lake.listapp.Fragments.ProfileFragment;
import r201.vista.lake.listapp.Fragments.ReminderFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    private Fragment profileFrag, reminderFrag, groupsFrag;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.reminders:

                    ft.add(R.id.content, reminderFrag);
                    ft.commit();

                    return true;
                case R.id.groups:

                    ft.add(R.id.content, groupsFrag);
                    ft.commit();

                    return true;
                case R.id.profile:

                    ft.add(R.id.content,profileFrag);
                    ft.commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        profileFrag = new ProfileFragment();
        reminderFrag = new ReminderFragment();
    }

}
