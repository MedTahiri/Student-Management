package com.mohamed.tahiri.studentmanagement;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mohamed.tahiri.studentmanagement.Adapter.ProfilesAdapter;
import com.mohamed.tahiri.studentmanagement.api.student.StudentAPI;
import com.mohamed.tahiri.studentmanagement.api.student.StudentCallback;
import com.mohamed.tahiri.studentmanagement.models.Student;

import java.util.ArrayList;
import java.util.List;

public class ProfilesActivity extends AppCompatActivity {

    int nbStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profiles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilesActivity.this,ProfileActivity.class);
                intent.putExtra("nb_student",nbStudent);
                startActivity(intent);
            }
        });


        StudentAPI.getAllStudent(new StudentCallback() {
            @Override
            public void onStudentsFetched(List<Student> students) {
                ProfilesAdapter adapter = new ProfilesAdapter(students,ProfilesActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProfilesActivity.this));
                recyclerView.setAdapter(adapter);
                nbStudent = adapter.getItemCount();
            }

            @Override
            public void onError(String errorMessage) {

            }
        });


    }
}