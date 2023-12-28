package com.mahiapps.fmath.Activities;

import static com.mahiapps.fmath.R.color.green;
import static com.mahiapps.fmath.R.color.orange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.mahiapps.fmath.Adapters.CourseGVAdapter;
import com.mahiapps.fmath.Model.CourseModel;
import com.mahiapps.fmath.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CardView tableCardView, arithmeticCardView;
    private TextView score;

    private int right = 0, wrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.score);
        tableCardView = findViewById(R.id.tableCardView);
        arithmeticCardView = findViewById(R.id.arithmeticCardView);

        tableCardView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TableActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        arithmeticCardView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ArithmeticActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    private void placeScoreValue() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

            right = preferences.getInt("RIGHT", 0);
            wrong = preferences.getInt("WRONG", 0);
            int total = right - wrong;
            score.setText(String.valueOf(total));
            if(total > 0){
                score.setTextColor(getResources().getColor(green));
            } else{
                score.setTextColor(getResources().getColor(orange));
            }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(preferences.contains("RIGHT") && preferences.contains("WRONG")){
            placeScoreValue();
        } else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("RIGHT", 0);
            editor.putInt("WRONG", 0);
            editor.apply();
            score.setText("0");
        }
    }
}
