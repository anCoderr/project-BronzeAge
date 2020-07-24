package com.example.lyricsfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText artistNameText, songNameText;

    TextView lyricsText;

    Button getLyricsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistNameText = findViewById(R.id.artistNameEditText);
        songNameText = findViewById(R.id.songNameEditText);

        lyricsText = findViewById(R.id.lyricsText);

        getLyricsButton = findViewById(R.id.getLyricsButton);

        getLyricsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url="https://api.lyrics.ovh/v1/" + artistNameText.getText().toString() + "/" + songNameText.getText().toString() ;
                url = url.replace(" ", "%20");

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            lyricsText.setText(response.getString("lyrics"));

                        } catch (JSONException e) {

                            e.printStackTrace();
                            lyricsText.setText("No Internet / Wrong input / Typo error , could not fetch the lyrics.");

                        }

                    }
                }, new Response.ErrorListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        lyricsText.setText("No Internet / Wrong input / Typo error , could not fetch the lyrics.");

                    }
                });

                requestQueue.add(jsonObjectRequest);

            }
        });

    }
}
