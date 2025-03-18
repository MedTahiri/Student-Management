package com.mohamed.tahiri.studentmanagement.services;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import kotlinx.coroutines.internal.LockFreeLinkedListKt;

public class ApiService {

    public static String makeRequest(String apiUrl, String method,Object data) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);

            if (method.equals("POST")){
                String jsonData = new Gson().toJson(data);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(jsonData.getBytes("UTF-8"));
                outputStream.close();
            }

            if (method.equals("PUT")){
                String jsonData = new Gson().toJson(data);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(jsonData.getBytes("UTF-8"));
                outputStream.close();
            }

            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();
            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
