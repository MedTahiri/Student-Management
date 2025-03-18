package com.mohamed.tahiri.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mohamed.tahiri.studentmanagement.api.note.NoteAPI;
import com.mohamed.tahiri.studentmanagement.api.note.NoteCallback;
import com.mohamed.tahiri.studentmanagement.models.createNote;

public class NoteActivity extends AppCompatActivity {

    EditText matier,score;
    Toolbar toolbar;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        matier = (EditText) findViewById(R.id.matiere);
        score = (EditText) findViewById(R.id.score);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.getNavigationIcon().setTint(getResources().getColor(android.R.color.white, getTheme()));

        id = getIntent().getIntExtra("student_id",-1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        } else if (item.getItemId()==R.id.save){
            createNote note = new createNote(
                    matier.getText().toString(),
                    score.getText().toString(),
                    Integer.parseInt(score.getText().toString())>=10
            );
            NoteAPI.createNote(new NoteCallback() {
                @Override
                public void onSuccess() {
                    Intent intent = new Intent(NoteActivity.this, NotesActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(NoteActivity.this, "error in create note", Toast.LENGTH_SHORT).show();
                }
            },note,id);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}