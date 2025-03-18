package com.mohamed.tahiri.studentmanagement.api.note;

import com.mohamed.tahiri.studentmanagement.models.Note;
import com.mohamed.tahiri.studentmanagement.models.Student;

import java.util.List;

public interface NotesCallback {
    void onStudentsFetched(List<Note> notes);
    void onError(String errorMessage);
}