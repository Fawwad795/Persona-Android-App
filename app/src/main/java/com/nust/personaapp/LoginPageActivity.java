package com.nust.personaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.*;
import android.content.Intent;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class LoginPageActivity extends AppCompatActivity implements OnClickListener, ValueEventListener{

    EditText enteredQalamId;
    EditText enteredPassword;
    Button loginButton, signUpButton;
    String userQalamId, userPassword;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        enteredQalamId = findViewById(R.id.enterQalamId);
        enteredPassword = findViewById(R.id.enterPassword);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    // Event handler onClick overridden below
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpButton:
                Intent signUpPage = new Intent(LoginPageActivity.this, SignUpPageActivity.class);
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

    public Boolean validatePassword(){;
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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student_Records");
        Query checkUser = reference.orderByChild("qalamId").equalTo(userQalamId);

        checkUser.addListenerForSingleValueEvent(this);
    }

    // Overriding the onDataChange method of the interface ValueEventListener
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists()){
            enteredQalamId.setError(null);

            // Fetch the password from the database of this particular student user
            String passwordFromDB = snapshot.child(userQalamId).child("password").getValue(String.class);

            if(passwordFromDB.equals(userPassword)){
                enteredPassword.setError(null);

                // Fetch degree
                String degreeFromDB = snapshot.child(userQalamId).child("degree").getValue(String.class);

                if(degreeFromDB.equals("Undergraduate")){
                    student = new UndergradStudent(snapshot.child(userQalamId));
                } else {
                    boolean hasDepartment = snapshot.child(userQalamId).hasChild("department");
                    boolean hasSemester = snapshot.child(userQalamId).hasChild("semester");

                    if(!hasSemester){
                        if(hasDepartment) {
                            student = new PostgradStudent(snapshot.child(userQalamId), false);
                        } else {
                            student = new PostgradStudent(snapshot.child(userQalamId), false, false);
                        }
                    }
                    else {student = new PostgradStudent(snapshot.child(userQalamId));}
                }

                Intent home = new Intent(LoginPageActivity.this, HomeActivity.class);
                home.putExtra("STUDENT_OBJECT", student);
                startActivity(home);
                finish();

            } else {
                enteredPassword.setError("Incorrect password");
                enteredPassword.requestFocus();
            }
        } else {
            enteredQalamId.setError("No such user exists");
            enteredQalamId.requestFocus();
        }
    }

    // Overriding the onCancelled method of the interface ValueEventListener
    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(LoginPageActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}