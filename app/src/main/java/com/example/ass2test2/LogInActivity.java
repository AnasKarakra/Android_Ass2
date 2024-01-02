package com.example.ass2test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {
    private static final String NAME="NAME";
    private static final String PASSWORD="PASSWORD";
    private static final String FLAG ="FLAG";
    private boolean flag=false;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText edName,edPassword;
    private CheckBox chkbox;
    private  Button btnLogin,btnSignup;
    private TextView textView;

    //===============================================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        edName=findViewById(R.id.edtUsername);
        edPassword=findViewById(R.id.edtPassword);
        chkbox=findViewById(R.id.ckRememberMe);
        btnLogin=findViewById(R.id.btnLogIn);
        btnSignup=findViewById(R.id.btnSignUp);
        textView=findViewById(R.id.txt);
        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("passwordSign");
        textView.setText("you can use this \n Username : "+name+"\n password : "+password);


        setupSharedPrefs();
        checkPref();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edName.getText().toString();
                String  password=edPassword.getText().toString();


                if(chkbox.isChecked()){
                    if(!flag){
                        editor.putString(NAME,name);
                        editor.putString(PASSWORD,password);
                        editor.putBoolean(FLAG,true);
                        editor.commit();
                    }
                }
                else {
                    // Clear data from SharedPreferences if checkbox is not checked
                    editor.remove(NAME);
                    editor.remove(PASSWORD);
                    editor.remove(FLAG);
                    editor.apply();
                }
                Intent intent =new Intent(LogInActivity.this,HomePage.class);
                startActivity(intent);

            }

        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogInActivity.this,SingUpActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
    private void checkPref(){
        flag=pref.getBoolean(FLAG,false);
        if(flag){
            String name = pref.getString(NAME, "");
            String password = pref.getString(PASSWORD, "");
            edName.setText(name);
            edPassword.setText(password);
            chkbox.setChecked(true);
        }
    }
    private void setupSharedPrefs() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
    }

}