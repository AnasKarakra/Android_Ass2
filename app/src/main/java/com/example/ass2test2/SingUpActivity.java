package com.example.ass2test2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SingUpActivity extends AppCompatActivity {
    private User user;
    private EditText edt1,edt2,edt3;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        edt1=findViewById(R.id.edtUsernameS);
        edt2=findViewById(R.id.edtEmail);
        edt3=findViewById(R.id.edtPasswordS);
        btn=findViewById(R.id.btnSignUps);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=edt1.getText().toString();
                String password=edt3.getText().toString();
                Intent intent=new Intent(SingUpActivity.this,LogInActivity.class);
                intent.putExtra("name",userName);
                intent.putExtra("passwordSign",password);
                startActivity(intent);
            }
        });
    }
}