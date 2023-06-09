package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentCoursesBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;

public class CoursesFragment extends Fragment {

    private FragmentCoursesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
