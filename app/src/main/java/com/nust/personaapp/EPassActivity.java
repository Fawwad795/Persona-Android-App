package com.nust.personaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class EPassActivity extends AppCompatActivity {

    ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epass);

        gif = findViewById(R.id.gifImageView);

        Glide.with(this).asGif().load(R.drawable.scan).into(gif);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(homeIntent);
    }
}