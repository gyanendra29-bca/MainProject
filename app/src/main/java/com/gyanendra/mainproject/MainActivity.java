package com.gyanendra.mainproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gyanendra.mainproject.Activities.StartingActivity;
import com.gyanendra.mainproject.Fragments.CreateRideFragment;
import com.gyanendra.mainproject.Fragments.HomeFragment;
import com.gyanendra.mainproject.Fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mbottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigning the bottom navigation to navigate between the fragments
        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavigationView);
        Menu menuNav = mbottomNavigationView.getMenu();


        //setting the homeFragment as default Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new HomeFragment()).commit();
        //BottomNavigationMethod to perform bottomNavigation Action
        mbottomNavigationView.setOnNavigationItemSelectedListener(bottomnavMethod);

    }



    @Override
    protected void onStart() {
        super.onStart();

        //checks the user is loggedIn or not
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null) {
            //Redirect to the starting Activity if the user is not logged
            Intent intent = new Intent(MainActivity.this, StartingActivity.class);
            startActivity(intent);
            finish();
        }
    }


    //Performs the Bottom Navigation aciton
    private BottomNavigationView.OnNavigationItemSelectedListener bottomnavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    //Setting the fragment to null
                    Fragment fragment = null;
                    if (item.getItemId()==R.id.homeMenu) {
                        //Navigates between the activities by using the id of the menu

                        fragment = new HomeFragment();
                    } else if (item.getItemId()==R.id.createRideMenu) {
                        fragment = new CreateRideFragment();
                    } else if (item.getItemId()==R.id.profileMenu) {
                        fragment = new ProfileFragment();
                    }


                    //Replaces the fragment in the FrameLayout
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, fragment).commit();
                    return true;
                }
            };

}
