package com.nust.personaapp.activities.features;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.nust.personaapp.R;

public class DuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dues);

        ImageView gif = findViewById(R.id.gifImageViewDues);
        Glide.with(this).asGif().load(R.drawable.dues1).into(gif);
    }
}
