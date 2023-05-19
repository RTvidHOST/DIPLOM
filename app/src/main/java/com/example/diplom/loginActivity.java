package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    EditText username, password;
    ImageButton btn_signin;
    DBHelper dbHelper;

    public static String user5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username1);
        password = (EditText)findViewById(R.id.password1);
        btn_signin = (ImageButton)findViewById(R.id.btn_signin1);
        dbHelper = new DBHelper(this);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user5 = username.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(loginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean chekuserpass = dbHelper.checkusernamepassword(user, pass);
                    if (chekuserpass == true) {
                        Toast.makeText(loginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(loginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                    Boolean chekadminpass = dbHelper.checkadminpassword(user, pass);
                    if (chekadminpass == true) {
                        Toast.makeText(loginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(loginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}