package com.example.yeong.myapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeong.myapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText log_email, log_password;
    private TextView log_register;
    private Button log_button;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceStates){
        super.onCreate(savedInstanceStates);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session = new Session(this);
        log_email = (EditText)findViewById(R.id.LoginEmail);
        log_password = (EditText)findViewById(R.id.LoginPassword);
        log_register = (TextView)findViewById(R.id.LoginRegister);
        log_button = (Button)findViewById(R.id.LoginButton);

        log_register.setOnClickListener(this);
        log_button.setOnClickListener(this);

        if (session.loggedin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.LoginButton:
                login();
                break;
            case R.id.LoginRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:
        }
    }

    private void login(){
        String email = log_email.getText().toString();
        String password = log_password.getText().toString();

        if (db.getUser(email, password)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong email/password", Toast.LENGTH_SHORT).show();
        }
    }
}
