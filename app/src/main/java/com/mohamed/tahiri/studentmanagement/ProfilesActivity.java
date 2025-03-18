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
import android.os.Looper;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mohamed.tahiri.studentmanagement.Adapter.ProfilesAdapter;
import com.mohamed.tahiri.studentmanagement.api.student.StudentAPI;
import com.mohamed.tahiri.studentmanagement.api.student.StudentsCallback;
import com.mohamed.tahiri.studentmanagement.models.Student;

import java.util.List;

public class ProfilesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilesActivity.this, ProfileActivity.class);
                intent.putExtra("nb_student", nbStudent);
                startActivity(intent);
            }
        });

        refreshStudents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshStudents();
    }

    private void refreshStudents() {
        StudentAPI.getAllStudent(new StudentsCallback() {
            @Override
            public void onStudentsFetched(List<Student> students) {
                ProfilesAdapter adapter = new ProfilesAdapter(students, ProfilesActivity.this);
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