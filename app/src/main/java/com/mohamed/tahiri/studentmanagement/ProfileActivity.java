package com.mohamed.tahiri.studentmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mohamed.tahiri.studentmanagement.Utils.ImageUtils;
import com.mohamed.tahiri.studentmanagement.api.student.StudentAPI;
import com.mohamed.tahiri.studentmanagement.api.student.StudentCallback;
import com.mohamed.tahiri.studentmanagement.models.Student;

import java.util.List;
import java.util.Objects;

import com.mohamed.tahiri.studentmanagement.R;


public class ProfileActivity extends AppCompatActivity {

    EditText firstname ,lastname, class_name, remarque,phone;
    ImageView image;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        class_name = (EditText) findViewById(R.id.class_name);
        remarque = (EditText) findViewById(R.id.remarque);
        image = (ImageView) findViewById(R.id.image);
        phone = (EditText) findViewById(R.id.phone);

        save = (Button) findViewById(R.id.save);

        Student studentIntent = (Student) getIntent().getSerializableExtra("student_data");
        int nbStudent = getIntent().getIntExtra("nb_student",0);

        if (studentIntent != null) {
            firstname.setText(studentIntent.firstname);
            lastname.setText(studentIntent.lastname);
            class_name.setText(studentIntent.class_name);
            remarque.setText(studentIntent.remarque);
            phone.setText(studentIntent.phone);
            ImageUtils.loadImageFromUrl(studentIntent.image, image);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentIntent==null){

                    Student student = new Student(
                            nbStudent+1,
                            firstname.getText().toString(),
                            lastname.getText().toString(),
                            image.toString(),
                            "email",
                            "phone",
                            class_name.getText().toString(),
                            remarque.getText().toString(),
                            0
                    );
                    StudentAPI.createStudent(new StudentCallback() {
                    @Override
                    public void onStudentsFetched(List<Student> students) {
                        if (students.size()==nbStudent){
                            Toast.makeText(ProfileActivity.this, "error student not created : students.size == "+ nbStudent, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(ProfileActivity.this, ProfilesActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(ProfileActivity.this, "error student not created", Toast.LENGTH_SHORT).show();
                    }
                },student);
                }
                else {

                    Student student = new Student(
                            studentIntent.id,
                            firstname.getText().toString(),
                            lastname.getText().toString(),
                            studentIntent.image,
                            studentIntent.email,
                            studentIntent.phone,
                            class_name.getText().toString(),
                            remarque.getText().toString(),
                            studentIntent.note_id
                    );
                    StudentAPI.updateStudent(new StudentCallback() {
                        @Override
                        public void onStudentsFetched(List<Student> students) {
                                Intent intent = new Intent(ProfileActivity.this, ProfilesActivity.class);
                                startActivity(intent);
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Toast.makeText(ProfileActivity.this, "error student not updated", Toast.LENGTH_SHORT).show();
                        }
                    },student);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_appel:
                Intent i1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone.getText().toString()));
                startActivity(i1);
                return true;

            case R.id.menu_notes:
                Intent i2 = new Intent(this, NotesActivity.class);
                startActivity(i2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}