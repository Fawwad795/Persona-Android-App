package com.nust.personaapp.activities.main;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.animation.ObjectAnimator;
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
import android.os.Handler;
import android.os.Looper;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationBarView.*;
import com.nust.personaapp.models.Student;
import com.nust.personaapp.R;
import com.nust.personaapp.activities.features.*;

public class HomeActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener {

    TextView studentName, studentEmail, studentQalamId, studentSchool, studentAttendance, studentDepartment;
    Button[] Buttons = new Button[5];
    CardView[] cardViews = new CardView[6]; // Including profile card
    NavigationBarView bottomNavigationView;
    MediaPlayer clicksound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Student student = (Student) getIntent().getSerializableExtra("STUDENT_OBJECT");

        // Initialize views
        initializeViews();
        
        // Set student data
        setStudentData(student);
        
        // Setup progress bar animation
        setupProgressBarAnimation(student);
        
        // Setup button listeners
        setupButtonListeners();
        
        // Setup entrance animations
        setupEntranceAnimations();

        // Initialize MediaPlayer with the click sound
        clicksound = MediaPlayer.create(this, R.raw.clicksound);

        // Setup Bottom Navigation
        setupBottomNavigation();
    }
    
    private void initializeViews() {
        // Find the TextViews and set the retrieved data
        studentName = findViewById(R.id.studentName);
        studentEmail = findViewById(R.id.studentEmail);
        studentQalamId = findViewById(R.id.studentQalamId);
        studentSchool = findViewById(R.id.studentSchool);
        studentAttendance = findViewById(R.id.studentAttendance);
        studentDepartment = findViewById(R.id.studentDepartment);

        // Initialize Buttons
        Buttons[0] = findViewById(R.id.menu_button);
        Buttons[1] = findViewById(R.id.e_pass_button);
        Buttons[2] = findViewById(R.id.complaint_button);
        Buttons[3] = findViewById(R.id.request_button);
        Buttons[4] = findViewById(R.id.dues_button);
        
        // Initialize CardViews for animations
        cardViews[0] = findViewById(R.id.profileCard);
        cardViews[1] = findViewById(R.id.cardView1);
        cardViews[2] = findViewById(R.id.cardView2);
        cardViews[3] = findViewById(R.id.cardView3);
        cardViews[4] = findViewById(R.id.cardView4);
        cardViews[5] = findViewById(R.id.cardView5);
    }
    
    private void setStudentData(Student student) {
        studentName.setText(student.getFirstName() + " " + student.getLastName());
        studentEmail.setText(student.getEmail());
        studentQalamId.setText("Qalam ID: " + student.getQalamId());
        studentSchool.setText("School: " + student.getSchool());
        studentAttendance.setText("Attendance: " + student.getPercentMonthlyAttendance() + "%");
        studentDepartment.setText("Department: " + student.getDepartment());
    }
    
    private void setupProgressBarAnimation(Student student) {
        ProgressBar progressBar = findViewById(R.id.attendanceProgressBar);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, student.getPercentMonthlyAttendance());
        progressAnimator.setDuration(2000);
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        
        // Delay the animation slightly for better visual effect
        new Handler(Looper.getMainLooper()).postDelayed(() -> progressAnimator.start(), 500);
    }
    
    private void setupButtonListeners() {
        // Setting onClickListeners for Buttons
        for (Button button : Buttons) {
            button.setOnClickListener(this);
        }
    }
    
    private void setupEntranceAnimations() {
        // Animate cards with staggered entrance
        for (int i = 0; i < cardViews.length; i++) {
            if (cardViews[i] != null) {
                cardViews[i].setAlpha(0f);
                cardViews[i].setTranslationY(50f);
                cardViews[i].setScaleX(0.95f);
                cardViews[i].setScaleY(0.95f);
                
                final int index = i;
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    cardViews[index].animate()
                        .alpha(1f)
                        .translationY(0f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(600)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
                }, i * 100L); // Stagger by 100ms
            }
        }
    }
    
    private void setupBottomNavigation() {
        // Instantiating Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        
        // Setting onClickListeners for Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(this);
        
        // Set home as selected by default
        bottomNavigationView.setSelectedItemId(R.id.home);
        
        // Animate bottom navigation
        bottomNavigationView.setAlpha(0f);
        bottomNavigationView.setTranslationY(100f);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            bottomNavigationView.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(600)
                .setInterpolator(new DecelerateInterpolator())
                .start();
        }, 800);
    }

    // Overriding the handler for Button Clicks on the cards
    @Override
    public void onClick(View view) {
        try {
            // Add subtle press animation
            view.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction(() -> {
                    view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100);
                });
            
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
                // Create final copy for lambda
                final Intent finalIntent = newIntent;
                
                // Delay activity start to allow animation to complete
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    try {
                        startActivityOfIntent(finalIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, 200);
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

    // Method to start an activity, if activity exists, start that, else create a new one.
    private void startActivityOfIntent(Intent newIntent) {
        startActivity(newIntent);
    }

    private void startActivityOfNavBar(Intent newIntent){
        startActivity(newIntent);
    }
}
