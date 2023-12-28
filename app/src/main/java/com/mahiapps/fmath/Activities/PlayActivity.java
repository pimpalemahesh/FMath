package com.mahiapps.fmath.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mahiapps.fmath.R;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private ImageView operationImage;
    private TextView num1, num2;
    private EditText result;
    private AppCompatButton check, next;

    private String operation = "";
    private int imageId, number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        operationImage = findViewById(R.id.operationImage);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        result = findViewById(R.id.result);
        check = findViewById(R.id.check);
        next = findViewById(R.id.next);

        placeNumbers();


        if(getIntent().getExtras() != null){
            operation = getIntent().getExtras().getString("OPERATION");
            imageId = getIntent().getExtras().getInt("IMAGE");
        }

        try {
            operationImage.setImageResource(imageId);
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        check.setOnClickListener(v -> {
            int resultValue = Integer.valueOf(result.getText().toString());
            int answer = calculate(operation);

            if(answer == resultValue){
                Toast.makeText(this, String.valueOf(answer), Toast.LENGTH_SHORT).show();
                result.setTextColor(getResources().getColor(R.color.green));
                int rights = preferences.getInt("RIGHT", 0);
                editor.putInt("RIGHT", rights + 1);
                editor.apply();
            } else{
                result.setTextColor(getResources().getColor(R.color.red));
                int rights = preferences.getInt("WRONG", 0);
                editor.putInt("WRONG", rights + 1);
                editor.apply();
            }
        });

        next.setOnClickListener(v -> placeNumbers());

    }

    private void placeNumbers() {
        Random random = new Random();
        number1 = random.nextInt(100) + 1;
        number2 = random.nextInt(100) + 1;
        num1.setText(String.valueOf(number1));
        num2.setText(String.valueOf(number2));
    }

    private int calculate(String operation){
        int ans = 0;
        switch (operation){
            case "ADDITION":
                ans = number1 + number2;
                break;

            case "SUBTRACTION":
                ans = number1 - number2;
                break;

            case "MULTIPLICATION":
                ans = number1 * number2;
                break;

            case "DIVISION":
                ans = number1 / number2;
                break;
        }
        return ans;
    }
}