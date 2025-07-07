package com.nust.personaapp;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;

public class DuesActivity extends AppCompatActivity {

    ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dues);

        gif = findViewById(R.id.gifImageViewDues);
        Glide.with(this).asGif().load(R.drawable.dues1).into(gif);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(homeIntent);
    }
}