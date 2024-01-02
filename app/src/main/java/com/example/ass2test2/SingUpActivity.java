package com.example.ass2test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SingUpActivity extends AppCompatActivity {
    private User user=null;
    private EditText edt1,edt2,edt3;
    private Button btn;

    private ArrayList<User> arrayList;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSharedPrefs();

        setContentView(R.layout.activity_sing_up);
        edt1=findViewById(R.id.edtUsernameS);
        edt2=findViewById(R.id.edtEmail);
        edt3=findViewById(R.id.edtPasswordS);
        btn=findViewById(R.id.btnSignUps);

        arrayList=new ArrayList<>();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=edt1.getText().toString();
                String email=edt2.getText().toString();
                String password=edt3.getText().toString();

                String userString = prefs.getString(DATA, "");
                if (!userString.isEmpty()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<User>>() {
                    }.getType();
                    arrayList = gson.fromJson(userString, type);
                }

                user=new User(userName,email,password);
                arrayList.add(user);

                Gson gson = new Gson();
                String new_userString = gson.toJson(arrayList);

                editor.putString(DATA, new_userString);
                editor.apply();

                Intent intent=new Intent(SingUpActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}