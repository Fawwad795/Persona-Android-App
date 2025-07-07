package com.nust.personaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.OnBackPressedCallback;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.security.SecureRandom;

public class SignUpPageActivity extends AppCompatActivity implements OnClickListener {

    EditText signUpFirstName, signUpLastName, signUpQalamId, signUpEmail, signUpPassword;
    Spinner degreeSpinner, schoolSpinner, departmentSpinner, semesterSpinner;
    Button signUpButton;
    Student student;
    FirebaseDatabase database;
    DatabaseReference reference;
    SecureRandom random = new SecureRandom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        // Initialize Spinners
        setupSpinner(R.id.degreeSpinner, R.array.degree_array);
        setupSpinner(R.id.schoolSpinner, R.array.school_array);
        setupSpinner(R.id.departmentSpinner, R.array.department_array);
        setupSpinner(R.id.semesterSpinner, R.array.semester_array);

        // Find the spinner views
        degreeSpinner = findViewById(R.id.degreeSpinner);
        schoolSpinner = findViewById(R.id.schoolSpinner);
        departmentSpinner = findViewById(R.id.departmentSpinner);
        semesterSpinner = findViewById(R.id.semesterSpinner);

        signUpFirstName = findViewById(R.id.enterSignUpFirstName);
        signUpLastName = findViewById(R.id.enterSignUpLastName);
        signUpQalamId = findViewById(R.id.enterSignUpQalamId);
        signUpEmail = findViewById(R.id.enterSignUpEmail);
        signUpPassword = findViewById(R.id.enterSignUpPassword);
        signUpButton = findViewById(R.id.signUpButton);

        // Event handler onClick overridden below
        signUpButton.setOnClickListener(this);

        // Set up onBackPressed callback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Navigate back to LoginPageActivity
                Intent intent = new Intent(SignUpPageActivity.this, LoginPageActivity.class);
                startActivity(intent);
                finish();
            }
        };

        // Add callback to the activity's lifecycle
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    //Overriding the onClick method of the interface OnClickListener
    @Override
    public void onClick(View view) {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Student_Records");

        // Get the values from the EditText fields
        String firstName = signUpFirstName.getText().toString();
        String lastName = signUpLastName.getText().toString();
        String degree = degreeSpinner.getSelectedItem().toString();
        String school = schoolSpinner.getSelectedItem().toString();
        String department = departmentSpinner.getSelectedItem().toString();
        String semester = semesterSpinner.getSelectedItem().toString();
        String qalamId = signUpQalamId.getText().toString();
        String email = signUpEmail.getText().toString();
        String password = signUpPassword.getText().toString();

        // checking if any of the fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() || degree.equals("Select") || school.equals("Select") || qalamId.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignUpPageActivity.this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (degree.equals("Undergraduate")) {
            if (department.equals("Select") || semester.equals("Select")) {
                Toast.makeText(SignUpPageActivity.this, "UG students must have a department and semester!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                student = new UndergradStudent(firstName, lastName, degree, school, department, semester, qalamId, email, password, random.nextInt(31) + 70);
            }
        }

        if (degree.equals("Postgraduate")) {
            if (department.equals("Select") && semester.equals("Select")) {
                student = new PostgradStudent(firstName, lastName, degree, school, qalamId, email, password, random.nextInt(31) + 70);
            }
            if (!department.equals("Select") && semester.equals("Select")) {
                student = new PostgradStudent(firstName, lastName, degree, school, semester, qalamId, email, password, random.nextInt(31) + 70);
            }
            if (!department.equals("Select") && !semester.equals("Select")) {
                student = new PostgradStudent(firstName, lastName, degree, school, department, semester, qalamId, email, password, random.nextInt(31) + 70);
            }

            reference.child(qalamId).setValue(student);

            // Make a toast to show that the user has been registered
            Toast.makeText(SignUpPageActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();

            // Navigate to LoginPageActivity
            Intent loginPage = new Intent(SignUpPageActivity.this, LoginPageActivity.class);
            loginPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginPage);
            finish();
        }
    }

    private void setupSpinner(int spinnerId, int arrayId) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}