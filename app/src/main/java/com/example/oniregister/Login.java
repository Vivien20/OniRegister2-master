package com.example.oniregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    public static final String TAG = "Login";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        loading = (ProgressBar) findViewById(R.id.loading);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(username.length() < 9){
                    Toast.makeText(Login.this, "Verifier username", Toast.LENGTH_SHORT).show();
                }else if(password.length() < 6){
                    Toast.makeText(Login.this, "Verifier Password", Toast.LENGTH_SHORT).show();
                }else{
                    loading.setVisibility(View.VISIBLE);
                    btnLogin.setVisibility(View.GONE);
                    etUsername.setVisibility(View.GONE);
                    etPassword.setVisibility(View.GONE);
                    loginUser(username, password);

                }

            }
        });
    }

    private  void loginUser(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Toast.makeText(Login.this, "Vous ne disposez pas de copmte", Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.VISIBLE);
                    etUsername.setVisibility(View.VISIBLE);
                    etPassword.setVisibility(View.VISIBLE);
                    //Log.e(TAG, "")
                }else{
                    Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                    goActivity();
                }

            }
        });
        //Log.i(TAG,"Attempting to login user" + username);

    }
    //
    private void goActivity() {
        Intent i = new Intent(Login.this, Adapter.class);
        startActivity(i);
        //
    }
}
