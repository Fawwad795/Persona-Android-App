package com.nust.personaapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.media.MediaPlayer;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationBarView.*;
import com.nust.personaapp.models.Student;
import com.nust.personaapp.R;
import com.nust.personaapp.activities.features.*;

public class HomeActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener {

    TextView studentName, studentEmail, studentQalamId, studentSchool, studentAttendance, studentDepartment;
    Button[] Buttons = new Button[5];
    NavigationBarView bottomNavigationView;
    MediaPlayer clicksound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Student student = (Student) getIntent().getSerializableExtra("STUDENT_OBJECT");

        ProgressBar progressBar = findViewById(R.id.attendanceProgressBar);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, student.getPercentMonthlyAttendance());
        progressAnimator.setDuration(3500);
        progressAnimator.start();

        // Find the TextViews and set the retrieved data
        studentName = findViewById(R.id.studentName);
        studentEmail = findViewById(R.id.studentEmail);
        studentQalamId = findViewById(R.id.studentQalamId);
        studentSchool = findViewById(R.id.studentSchool);
        studentAttendance = findViewById(R.id.studentAttendance);
        studentDepartment = findViewById(R.id.studentDepartment);

        studentName.setText(student.getFirstName() + " " + student.getLastName());
        studentEmail.setText(student.getEmail());
        studentQalamId.setText("Qalam ID: " + student.getQalamId());
        studentSchool.setText("School: " + student.getSchool());
        studentAttendance.setText("Attendance: " + student.getPercentMonthlyAttendance() + "%");
        studentDepartment.setText("Department: " + student.getDepartment());

        //Instantiating Buttons
        Buttons[0] = findViewById(R.id.menu_button);
        Buttons[1] = findViewById(R.id.e_pass_button);
        Buttons[2] = findViewById(R.id.complaint_button);
        Buttons[3] = findViewById(R.id.request_button);
        Buttons[4] = findViewById(R.id.dues_button);

        // Setting onClickListeners for Buttons
        for (Button button : Buttons) {
            button.setOnClickListener(this);
        }

        // Initialize MediaPlayer with the click sound
        clicksound = MediaPlayer.create(this, R.raw.clicksound);

        // Instantiating Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Setting onClickListeners for Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    // Overriding the handler for Button Clicks on the cards
    @Override
    public void onClick(View view) {
        try {
            if (clicksound != null) {
                clicksound.start();
            }

            Intent newIntent = null;
            switch (view.getId()) {
                case R.id.menu_button:
                    newIntent = new Intent(HomeActivity.this, MenuActivity.class);
                    break;
                case R.id.e_pass_button:
                    newIntent = new Intent(HomeActivity.this, EPassActivity.class);
                    break;
                case R.id.complaint_button:
                    newIntent = new Intent(HomeActivity.this, ComplaintsActivity.class);
                    break;
                case R.id.request_button:
                    newIntent = new Intent(HomeActivity.this, RequestsActivity.class);
                    break;
                case R.id.dues_button:
                    newIntent = new Intent(HomeActivity.this, DuesActivity.class);
                    break;
                default:
                    break;
            }

            if (newIntent != null) {
                startActivityOfIntent(newIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Overriding the handler for Bottom Navigation
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent newIntent = null;
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            // Already on home, do nothing
            return true;
        } else if (itemId == R.id.announcements) {
            newIntent = new Intent(HomeActivity.this, AnnouncementsActivity.class);
        } else if (itemId == R.id.shuttle) {
            newIntent = new Intent(HomeActivity.this, ShuttleActivity.class);
        }

        if (newIntent != null) {
            startActivityOfNavBar(newIntent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Re-initialize MediaPlayer if it was released
        if (clicksound == null) {
            clicksound = MediaPlayer.create(this, R.raw.clicksound);
        }
    }

    // Method to start an activity with a delay, if activity exists, start that, else create a new one.
    private void startActivityOfIntent(Intent newIntent) throws InterruptedException {
        Thread.sleep(300);
        startActivity(newIntent);
    }

    private void startActivityOfNavBar(Intent newIntent){
        startActivity(newIntent);
    }
}
