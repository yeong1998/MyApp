package com.example.yeong.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeong.myapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText reg_name, reg_email, reg_password, reg_cpassword;
    private TextView reg_login;
    private Button reg_button;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DbHelper(this);
        reg_name = (EditText)findViewById(R.id.RegisterName);
        reg_email = (EditText)findViewById(R.id.RegisterEmail);
        reg_password = (EditText)findViewById(R.id.RegisterPassword);
        reg_cpassword = (EditText)findViewById(R.id.RegisterConfirmPassword);
        reg_login = (TextView)findViewById(R.id.RegisterLogin);
        reg_button = (Button)findViewById(R.id.RegisterButton);

        reg_login.setOnClickListener(this);
        reg_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.RegisterButton:
                register();
                break;
            case R.id.RegisterLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            default:
        }
    }

    private void register(){
        String email = reg_email.getText().toString();
        String password = reg_password.getText().toString();
        if (email.isEmpty() && password.isEmpty()){
            displayToast("Username/password field empty");
        } else {
            db.addUser(email,password);
            displayToast("User registered");
            finish();
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
