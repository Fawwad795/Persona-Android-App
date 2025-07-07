package com.nust.personaapp.activities.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.*;
import android.content.Intent;
import android.widget.Toast;
import com.nust.personaapp.database.operations.StudentOperations;
import com.nust.personaapp.models.Student;
import com.nust.personaapp.activities.main.HomeActivity;
import com.nust.personaapp.R;

public class LoginActivity extends AppCompatActivity implements OnClickListener{

    EditText enteredQalamId;
    EditText enteredPassword;
    Button loginButton, signUpButton;
    String userQalamId, userPassword;
    Student student;
    private StudentOperations studentOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        enteredQalamId = findViewById(R.id.enterQalamId);
        enteredPassword = findViewById(R.id.enterPassword);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        // Initialize database operations
        studentOperations = new StudentOperations();

        signUpButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    // Event handler onClick overridden below
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpButton:
                Intent signUpPage = new Intent(LoginActivity.this, SignupActivity.class);
                signUpPage.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(signUpPage);
                break;

            case R.id.loginButton:
                if (!validateQalamId() || !validatePassword()) {
                    return;
                } else {
                    checkUser();
                }
                break;

            default:
                break;
        }
    }

    public Boolean validateQalamId(){
        if(enteredQalamId.getText().toString().isEmpty()){
            enteredQalamId.setError("Qalam ID cannot be empty");
            return false;
        } else if (enteredQalamId.getText().toString().length() != 6){
            enteredQalamId.setError("Qalam ID must be 6 digits long");
            return false;
        } else {
            enteredQalamId.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        if(enteredPassword.getText().toString().isEmpty()){
            enteredPassword.setError("Did not enter a password!");
            return false;
        } else {
            enteredPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        userQalamId = enteredQalamId.getText().toString();
        userPassword = enteredPassword.getText().toString();

        // Use the new database operations
        studentOperations.authenticateStudent(userQalamId, userPassword, new StudentOperations.AuthenticationCallback() {
            @Override
            public void onSuccess(Student student) {
                // Clear any previous errors
                enteredQalamId.setError(null);
                enteredPassword.setError(null);
                
                // Navigate to home activity
                Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                home.putExtra("STUDENT_OBJECT", student);
                startActivity(home);
                finish();
            }

            @Override
            public void onError(String errorMessage) {
                if (errorMessage.contains("password")) {
                    enteredPassword.setError("Incorrect password");
                    enteredPassword.requestFocus();
                } else if (errorMessage.contains("user")) {
                    enteredQalamId.setError("No such user exists");
                    enteredQalamId.requestFocus();
                } else {
                    Toast.makeText(LoginActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
