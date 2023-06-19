package com.example.myapplication.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.api.model.Course;
import com.example.myapplication.databinding.ViewHolderCourseBinding;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CoursesAdapter extends ListAdapter<Course, CoursesAdapter.CourseViewHolder> {

    public CoursesAdapter() {
        super(new DiffUtil.ItemCallback<Course>() {
            @Override
            public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });


    }


    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderCourseBinding binding = ViewHolderCourseBinding.inflate(layoutInflater, parent, false);

        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        Course item = getItem(position);
        try {
            holder.bind(item);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        ViewHolderCourseBinding itemBinding;

        public CourseViewHolder(ViewHolderCourseBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Course course) throws IOException {
            // bind image to recycle view
            Picasso.get().load(course.getImageUrl()).into(itemBinding.imgCourse);

            // bind title to recycle view
            itemBinding.txtCourseTitle.setText(course.getTitle());

            // bind description to recycle view
            itemBinding.txtCourseDes.setText(course.getDescription());
        }

    }
}
