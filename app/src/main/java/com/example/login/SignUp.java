package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText firstName, lastName, emailId, password;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("SIGN UP");

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailId = findViewById(R.id.email1);
        password = findViewById(R.id.password);
        Button register = findViewById(R.id.register);

        Button backToLogin = findViewById(R.id.backToLogin);
        db = new DBHandler(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstNameValue = firstName.getText().toString();
                String lastNameValue = lastName.getText().toString();
                String email1Value = emailId.getText().toString();
                String password1Value = password.getText().toString();

                if (firstNameValue.equals("") || lastNameValue.equals("") || email1Value.equals("") || password1Value.equals("")) {
                    Toast.makeText(SignUp.this, "FILL REQUIRED DETAILS", Toast.LENGTH_SHORT).show();

                }else{
                    Boolean checkEmailId = db.checkEmail(email1Value);
                    if(checkEmailId == false){
                        boolean isInserted = db.Insert(email1Value, password1Value, firstNameValue, lastNameValue);
                        if (isInserted == true) {
                                Toast.makeText(SignUp.this, "REGISTERED", Toast.LENGTH_SHORT).show();
                    } else {
                                Toast.makeText(SignUp.this, "NOT REGISTERED", Toast.LENGTH_SHORT).show();



            }
                    }else{
                        Toast.makeText(SignUp.this, "ALREADY REGISTERED", Toast.LENGTH_SHORT).show();
                    }

                    }
        }});

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    public void emptyField() {
        firstName.setText("");
        lastName.setText("");
        emailId.setText("");
        password.setText("");
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }


}

