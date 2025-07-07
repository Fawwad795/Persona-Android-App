package com.nust.personaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationBarView.*;


public class AnnouncementsActivity extends AppCompatActivity implements OnItemSelectedListener {

    NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.announcements);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.announcements:
                return true;
            case R.id.home:
                Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivityOfNavBar(homeIntent);
                return true;
            case R.id.shuttle:
                Intent shuttleIntent = new Intent(getApplicationContext(), ShuttleActivity.class);
                startActivityOfNavBar(shuttleIntent);
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(AnnouncementsActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Ensure the announcements item is selected when resuming this activity
        bottomNavigationView.setSelectedItemId(R.id.announcements);
    }

    private void startActivityOfNavBar(Intent newIntent) {
        newIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(newIntent);
        overridePendingTransition(0,0);
    }
}
