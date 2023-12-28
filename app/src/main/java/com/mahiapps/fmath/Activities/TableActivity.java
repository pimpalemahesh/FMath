package com.mahiapps.fmath.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.mahiapps.fmath.R;

public class TableActivity extends AppCompatActivity {

    private CardView playTableCardView, testTableCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        playTableCardView = findViewById(R.id.playTableCardView);
        testTableCardView = findViewById(R.id.testTableCardView);

        playTableCardView.setOnClickListener(view -> startActivity(new Intent(this, PlayTableActivity.class)));
        testTableCardView.setOnClickListener(view -> startActivity(new Intent(this, TestTableActivity.class)));
    }
}