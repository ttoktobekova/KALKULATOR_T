package com.example.kalkulator_t;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private boolean isLiked = false; // Флаг для отслеживания состояния


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String result = getIntent().getStringExtra("result");
        TextView resultTextView = findViewById(R.id.result_text_view);
        resultTextView.setText(result.toString());
        ImageView imageView = findViewById(R.id.serdechka);


        imageView.setOnClickListener(v -> {
            if (isLiked) {
                imageView.clearColorFilter();
            } else {
                imageView.setColorFilter(Color.argb(128, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
            }

            isLiked = !isLiked;
        });
    }
}