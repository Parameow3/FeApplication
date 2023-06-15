package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.api.model.Course;
import com.example.myapplication.api.service.ApiService;
import com.example.myapplication.databinding.FragmentCoursesBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.ui.adapter.CoursesAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoursesFragment extends Fragment {

    private FragmentCoursesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // load list courses from server (api)
        loadCourseListFromServer();
    }

    private void loadCourseListFromServer(){

        // create retrofit client
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create service object
        ApiService apiService = httpClient.create(ApiService.class);

        // load course list from server
        Call<List<Course>> task = apiService.loadCourseList();
        task.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful()){

                    showCourseList(response.body());

                }else {
                    Toast.makeText(getContext(), "Load Courses list failed!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getContext(), "Load Courses list failed!", Toast.LENGTH_LONG).show();
                Log.e("[CoursesFragment]", "Load Courses failed: " + t.getMessage());
            }
        });
    }

    private void showCourseList(List<Course> courseList){

        // Create Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewCourse.setLayoutManager(layoutManager);

        // Create Adapter
        CoursesAdapter adapter = new CoursesAdapter();
        adapter.submitList(courseList);
        binding.recyclerViewCourse.setAdapter(adapter);

    }
}
