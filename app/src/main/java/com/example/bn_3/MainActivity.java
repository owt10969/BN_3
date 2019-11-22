package com.example.bn_3;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.bn_3.ui.dashboard.DashboardFragment;
import com.example.bn_3.ui.home.HomeFragment;
import com.example.bn_3.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    final HomeFragment fragment1 = new HomeFragment();
    final DashboardFragment fragment2 = new DashboardFragment();
    final NotificationsFragment fragment3 = new NotificationsFragment();
    final FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.nav_host_fragment,fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment,fragment1, "1").hide(fragment1).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.d("Main", "onNavigationItemSelected: "+fm.getFragments());
                    fm.beginTransaction().hide(fragment2).hide(fragment3).show(fragment1).commit();
                    Log.d("Homefragment1IsHidden" ,"onNavigationItemSelected: "+String.valueOf(fragment1.isHidden()));
                    Log.d("Homefragment2IsHidden" ,"onNavigationItemSelected: "+String.valueOf(fragment2.isHidden()));
                    Log.d("Homefragment3IsHidden" ,"onNavigationItemSelected: "+String.valueOf(fragment3.isHidden()));
                    return true;
                case R.id.navigation_dashboard:
                    fm.beginTransaction().hide(fragment1).hide(fragment3).show(fragment2).commit();
                    Log.d("Dashfragment1IsHidden" ,"onNavigationItemSelected: "+String.valueOf(fragment1.isHidden()));
                    Log.d("Dashfragment2IsHidden" ,"onNavigationItemSelected: "+String.valueOf(fragment2.isHidden()));
                    Log.d("Dashfragment3IsHidden" ,"onNavigationItemSelected: "+String.valueOf(fragment3.isHidden()));
                    return true;
                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(fragment2).hide(fragment1).show(fragment3).commit();
                    return true;
            }
            return false;
        }
    };

}
