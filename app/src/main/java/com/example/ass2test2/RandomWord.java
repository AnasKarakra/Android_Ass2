package com.example.ass2test2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class RandomWord extends AppCompatActivity {
    private final String url = "https://random-word-api.p.rapidapi.com/get_word";
    private final String apiKey = "2331f03b7amsh37a92324b19ba4fp1266dcjsnadf1fd687db1";
    private RequestQueue queue;
    private TextView txtRandomWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_word);

        txtRandomWord = findViewById(R.id.txtRandomWord);
        queue = Volley.newRequestQueue(this);

        Button btnGetRandomWord = findViewById(R.id.btnGetRandomWord);
        btnGetRandomWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGetRandomWord_Click(view);
            }
        });
    }

    public void btnGetRandomWord_Click(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String randomWord = jsonResponse.getString("word");

                            txtRandomWord.setText("Random Word: " + randomWord);

                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Toast.makeText(RandomWord.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RandomWord.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // Add any additional parameters here if needed
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", apiKey);
                headers.put("X-RapidAPI-Host", "random-word-api.p.rapidapi.com");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
