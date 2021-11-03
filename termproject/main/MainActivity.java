package org.jjcouple.termproject.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jjcouple.termproject.R;
import org.jjcouple.termproject.fragment_display.FragmentCamping;
import org.jjcouple.termproject.fragment_display.FragmentHome;
import org.jjcouple.termproject.fragment_display.FragmentMountain;
import org.jjcouple.termproject.fragment_display.FragmentSetting;

public class MainActivity extends FragmentActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentMountain fragmentMountain = new FragmentMountain();
    private FragmentCamping fragmentCamping = new FragmentCamping();
    private FragmentSetting fragmentSetting = new FragmentSetting();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch(menuItem.getItemId())
            {
                case R.id.homeItem:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.mountainItem:
                    transaction.replace(R.id.frameLayout, fragmentMountain).commitAllowingStateLoss();
                    break;
                case R.id.campingItem:
                    transaction.replace(R.id.frameLayout, fragmentCamping).commitAllowingStateLoss();
                    break;
                case R.id.settingItem:
                    transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}
