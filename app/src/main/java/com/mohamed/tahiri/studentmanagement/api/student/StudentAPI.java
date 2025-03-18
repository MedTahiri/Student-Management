package com.mohamed.tahiri.studentmanagement.api.student;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.mohamed.tahiri.studentmanagement.models.Student;
import com.mohamed.tahiri.studentmanagement.services.ApiService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class StudentAPI {
    private static final String API_URL_PROFILES = "http://10.11.0.135:8000/api/profiles/";

    private static final String API_URL_PROFILE = "http://10.11.0.135:8000/api/profile/";

    public static void getAllStudent(final StudentCallback callback) {
        // Create a new Thread to run the network request
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Perform the network request in the background thread
                String response = ApiService.makeRequest(API_URL_PROFILES, "GET",new Object());

                // Once the network call is complete, post the result to the main thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null && !response.isEmpty()) {
                            try {
                                // Parse the JSON response
                                JSONArray jsonArray = new JSONArray(response);

                                ArrayList<Student> students = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Log.e("id = ", jsonArray.getJSONObject(i).getString("id"));
                                    Student student = new Student(
                                            Integer.parseInt(jsonArray.getJSONObject(i).getString("id")),
                                            jsonArray.getJSONObject(i).getString("firstname"),
                                            jsonArray.getJSONObject(i).getString("lastname"),
                                            jsonArray.getJSONObject(i).getString("image"),
                                            jsonArray.getJSONObject(i).getString("email"),
                                            jsonArray.getJSONObject(i).getString("phone"),
                                            jsonArray.getJSONObject(i).getString("class_name"),
                                            jsonArray.getJSONObject(i).getString("remarque"),
                                            Integer.parseInt(jsonArray.getJSONObject(i).getString("note_id"))
                                    );
                                    students.add(student);
                                }

                                callback.onStudentsFetched(students);

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
        }).start(); // Start the thread
    }

    public static void createStudent(final StudentCallback callback,Student student){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Perform the network request in the background thread
                String response = ApiService.makeRequest(API_URL_PROFILE, "POST", student);

                // Once the network call is complete, post the result to the main thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null && !response.isEmpty()) {
                            try {
                                // Parse the JSON response
                                JSONArray jsonArray = new JSONArray(response);

                                ArrayList<Student> students = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Log.e("id = ", jsonArray.getJSONObject(i).getString("id"));
                                    Student student = new Student(
                                            Integer.parseInt(jsonArray.getJSONObject(i).getString("id")),
                                            jsonArray.getJSONObject(i).getString("firstname"),
                                            jsonArray.getJSONObject(i).getString("lastname"),
                                            jsonArray.getJSONObject(i).getString("image"),
                                            jsonArray.getJSONObject(i).getString("email"),
                                            jsonArray.getJSONObject(i).getString("phone"),
                                            jsonArray.getJSONObject(i).getString("class_name"),
                                            jsonArray.getJSONObject(i).getString("remarque"),
                                            Integer.parseInt(jsonArray.getJSONObject(i).getString("note_id"))
                                    );
                                    students.add(student);
                                }

                                callback.onStudentsFetched(students);

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

    public static void updateStudent(final StudentCallback callback,Student student){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Perform the network request in the background thread
                String response = ApiService.makeRequest(API_URL_PROFILE, "PUT", student);

                // Once the network call is complete, post the result to the main thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null && !response.isEmpty()) {
                            try {
                                // Parse the JSON response
                                JSONArray jsonArray = new JSONArray(response);

                                ArrayList<Student> students = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Log.e("id = ", jsonArray.getJSONObject(i).getString("id"));
                                    Student student = new Student(
                                            Integer.parseInt(jsonArray.getJSONObject(i).getString("id")),
                                            jsonArray.getJSONObject(i).getString("firstname"),
                                            jsonArray.getJSONObject(i).getString("lastname"),
                                            jsonArray.getJSONObject(i).getString("image"),
                                            jsonArray.getJSONObject(i).getString("email"),
                                            jsonArray.getJSONObject(i).getString("phone"),
                                            jsonArray.getJSONObject(i).getString("class_name"),
                                            jsonArray.getJSONObject(i).getString("remarque"),
                                            Integer.parseInt(jsonArray.getJSONObject(i).getString("note_id"))
                                    );
                                    students.add(student);
                                }

                                callback.onStudentsFetched(students);

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

    public static void deleteStudent(final StudentCallback callback){}

}
