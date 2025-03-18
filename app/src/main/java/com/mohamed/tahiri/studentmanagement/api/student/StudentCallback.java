package com.mohamed.tahiri.studentmanagement.api.student;

import com.mohamed.tahiri.studentmanagement.models.Student;

import java.util.List;

public interface StudentCallback {
    void onStudentsFetched(List<Student> students);
    void onError(String errorMessage);
}
