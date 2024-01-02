package com.example.ass2test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstAppActivites extends AppCompatActivity {
    private Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_app_activites);
        btn1=findViewById(R.id.btnSearchByCat);
        btn2=findViewById(R.id.btnRandomjoke);
        btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(FirstAppActivites.this,Jokes2.class);
            startActivity(intent);
        }
    });
        btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(FirstAppActivites.this,Jokes1.class);
            startActivity(intent);
        }
    });
    }
}