package com.mohamed.tahiri.studentmanagement.api.note;

import android.os.Handler;
import android.os.Looper;

import com.mohamed.tahiri.studentmanagement.api.student.StudentCallback;
import com.mohamed.tahiri.studentmanagement.models.Note;
import com.mohamed.tahiri.studentmanagement.models.Student;
import com.mohamed.tahiri.studentmanagement.models.createNote;
import com.mohamed.tahiri.studentmanagement.models.createStudent;
import com.mohamed.tahiri.studentmanagement.services.ApiService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class NoteAPI {
    private static final String API_URL_NOTES = "http://medtahiri.pythonanywhere.com/api/notes/";

    private static final String API_URL_NOTE = "http://medtahiri.pythonanywhere.com/api/note/";

    public static void getAllNote(final NotesCallback callback, int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = ApiService.makeRequest(API_URL_NOTES+"?id="+id, "GET",new Object());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null && !response.isEmpty()) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);

                                ArrayList<Note> notes = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Note note = new Note(
                                            Integer.parseInt(jsonArray.getJSONObject(i).getString("id")),
                                            Integer.parseInt(jsonArray.getJSONObject(i).getString("student_id")),
                                            jsonArray.getJSONObject(i).getString("matiere"),
                                            jsonArray.getJSONObject(i).getString("score"),
                                            Boolean.parseBoolean(jsonArray.getJSONObject(i).getString("status"))
                                    );
                                    notes.add(note);
                                }

                                callback.onStudentsFetched(notes);

                            } catch (JSONException e) {
                                callback.onError("Error parsing the response.");
                                e.printStackTrace();
                            }
                        } else {
                            callback.onError("Empty or null response from the server.");
                        }
                    }
                });
            }
        }).start();
    }

    public static void createNote(final NoteCallback callback, createNote note,int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = ApiService.makeRequest(API_URL_NOTE+"?id="+id, "POST", note);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null && !response.isEmpty()) {
                            callback.onSuccess();
                        } else {
                            callback.onError("Empty or null response from the server.");
                        }
                    }
                });
            }
        }).start();
    }

}
