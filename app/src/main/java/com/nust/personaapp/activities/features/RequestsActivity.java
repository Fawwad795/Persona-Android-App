package com.nust.personaapp.activities.features;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.nust.personaapp.R;

public class RequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        ImageView gif = findViewById(R.id.gifImageViewRequest);
        Glide.with(this).asGif().load(R.drawable.request).into(gif);
    }
}
