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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {
    private static final String NAME="NAME";
    private static final String PASSWORD="PASSWORD";
    private static final String FLAG ="FLAG";
    private boolean flag=false;
    private SharedPreferences pref;
    public static final String DATA = "DATA";
    private SharedPreferences.Editor editor;
    private EditText edName,edPassword;
    private CheckBox chkbox;
    private  Button btnLogin,btnSignup;
    private TextView textView;
    private ArrayList<User> arraylist;

    //===============================================================================
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setupSharedPrefs();

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
        checkPref();


        arraylist=new ArrayList<>();
        String userString = pref.getString(DATA, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        arraylist = gson.fromJson(userString, type);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edName.getText().toString();
                String  password=edPassword.getText().toString();

                boolean correct_info=false;
                for(int i=0;i<arraylist.size();i++){
                    if(arraylist.get(i).getName().equals(name) && arraylist.get(i).getPassword().equals(password)){
                        correct_info=true;
                    }
                }


                if(correct_info==false){
                    textView.setText("incorrect Info");
                }
                else{
                    textView.setText("");
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