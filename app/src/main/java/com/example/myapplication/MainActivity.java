package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showFragment(new HomeFragment());

        // setup listeners
        binding.bottomNav.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.menuHome){
                showFragment(new HomeFragment());
            }else if(item.getItemId() == R.id.menuCourse){
                showFragment(new CoursesFragment());
            }else if(item.getItemId() == R.id.menuAccount){
                showFragment(new AccountFragment());
            }else if(item.getItemId() == R.id.menuChat){
                showFragment(new ChatFragment());
            }else {
                showFragment(new MoreFragment());
            }

            return true;
        });
    }

    private void showFragment(Fragment fragment){
        // FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // FragmentTransition
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        // Replace HomeFragment in lytFragment
        fragmentTransaction.replace(binding.lytFragment.getId(), fragment);

        // Commit transaction
        fragmentTransaction.commit();
    }


}

