package com.mohamed.tahiri.studentmanagement;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mohamed.tahiri.studentmanagement.Adapter.NotesAdapter;
import com.mohamed.tahiri.studentmanagement.Adapter.ProfilesAdapter;
import com.mohamed.tahiri.studentmanagement.api.note.NoteAPI;
import com.mohamed.tahiri.studentmanagement.api.note.NotesCallback;
import com.mohamed.tahiri.studentmanagement.api.student.StudentAPI;
import com.mohamed.tahiri.studentmanagement.api.student.StudentsCallback;
import com.mohamed.tahiri.studentmanagement.models.Note;
import com.mohamed.tahiri.studentmanagement.models.Student;

import java.util.List;

public class NotesActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    int student_id;

    Toolbar toolbar;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));

        student_id = getIntent().getIntExtra("student_id",-1);

        refreshNotes();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, NoteActivity.class);
                intent.putExtra("student_id",student_id);
                startActivity(intent);
            }
        });

    }

    private void refreshNotes() {
        NoteAPI.getAllNote(new NotesCallback() {
            @Override
            public void onStudentsFetched(List<Note> notes) {
                NotesAdapter adapter = new NotesAdapter(notes, NotesActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(NotesActivity.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {

            }
        },student_id);
    }

    @Override
    protected void onResume() {
        refreshNotes();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}