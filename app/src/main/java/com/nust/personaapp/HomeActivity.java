package com.nust.personaapp;

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
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    // Overriding the handler for Button Clicks on the cards
    @Override
    public void onClick(View view) {
        // Play button tap sound
        clicksound.start();

        try {
            switch (view.getId()) {
                case R.id.menu_button:
                    Intent menu = new Intent(HomeActivity.this, MenuActivity.class);
                    startActivityOfIntent(menu);
                    break;
                case R.id.e_pass_button:
                    Intent epass = new Intent(HomeActivity.this, EPassActivity.class);
                    startActivityOfIntent(epass);
                    break;
                case R.id.complaint_button:
                    Intent complaint = new Intent(HomeActivity.this, ComplaintActivity.class);
                    startActivityOfIntent(complaint);
                    break;
                case R.id.request_button:
                    Intent request = new Intent(HomeActivity.this, RequestActivity.class);
                    startActivityOfIntent(request);
                    break;
                case R.id.dues_button:
                    Intent dues = new Intent(HomeActivity.this, DuesActivity.class);
                    startActivityOfIntent(dues);
                    break;
            }
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
    }

    // Overriding the handler for Bottom Navigation
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.announcements:
                Intent announcements = new Intent(HomeActivity.this, AnnouncementsActivity.class);
                startActivityOfNavBar(announcements);
                return true;
            case R.id.home:
                return true;
            case R.id.shuttle:
                Intent shuttle = new Intent(HomeActivity.this, ShuttleActivity.class);
                startActivityOfNavBar(shuttle);
                return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Ensure the home item is selected when resuming this activity
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    // Method to start an activity with a delay, if activity exists, start that, else create a new one.
    private void startActivityOfIntent(Intent newIntent) throws InterruptedException {
        newIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        Thread.sleep(400);
        startActivity(newIntent);
    }

    private void startActivityOfNavBar(Intent newIntent){
        newIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(newIntent);
        overridePendingTransition(0,0);
    }

}
