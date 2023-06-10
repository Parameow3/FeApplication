package com.example.myapplication.api.service;

import com.example.myapplication.api.model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/course.json")
    Call<List<Course>> loadCourseList();

}
