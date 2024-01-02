package com.example.ass2test2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Jokes2 extends AppCompatActivity {
    private final String url = "https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/search";
    private final String apiKey = "2331f03b7amsh37a92324b19ba4fp1266dcjsnadf1fd687db1";
    private RequestQueue queue;
    private EditText edtSearchQuery;
    private TextView txtSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes2);

        edtSearchQuery = findViewById(R.id.edtSearchQuery);
        txtSearchResult = findViewById(R.id.txtSearchResult);
        queue = Volley.newRequestQueue(this);

        Button btnSearchJokes = findViewById(R.id.btnSearchJokes);
        btnSearchJokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSearchJokes_Click(view);
            }
        });
    }

    public void btnSearchJokes_Click(View view) {
        String searchQuery = edtSearchQuery.getText().toString();
        if (searchQuery.isEmpty()) {
            Toast.makeText(this, "Enter a search query", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "?query=" + searchQuery,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = "";
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int totalResults = jsonResponse.getInt("total");
                            JSONArray jokesArray = jsonResponse.getJSONArray("result");

                            result = "Total Results: " + totalResults + "\n";

                            for (int i = 0; i < jokesArray.length(); i++) {
                                JSONObject jokeObject = jokesArray.getJSONObject(i);
                                String jokeText = jokeObject.getString("value");
                                result += "\nJoke " + (i + 1) + ": " + jokeText;
                            }

                            txtSearchResult.setText(result);

                            // Close keyboard
                            InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            input.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Jokes2.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public java.util.Map<String, String> getHeaders() {
                java.util.Map<String, String> headers = new java.util.HashMap<>();
                headers.put("X-Rapidapi-Key", apiKey);
                headers.put("X-Rapidapi-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
