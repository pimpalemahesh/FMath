package com.mahiapps.fmath.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mahiapps.fmath.R;

public class TestTableActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView[] textViews;
    private TextView selectedTableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_table);

        seekBar = findViewById(R.id.seekBar);
        selectedTableTextView = findViewById(R.id.selectedTableTextView);
        textViews = new TextView[10];

        // Initialize EditText fields
        for (int i = 0; i < 10; i++) {
            int editTextId = getResources().getIdentifier("textView" + (i + 1), "id", getPackageName());
            textViews[i] = findViewById(editTextId);

        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTable(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void clearTable() {
        for(int i = 0; i < 10; i++){
            textViews[i].setText("");
        }
        textViews[0].requestFocus();
    }

    private void updateTable(int tnumber) {
        for (int i = 0; i < 10; i++) {
            int value = (i + 1) * tnumber;
            textViews[i].setText(String.valueOf(value));
            textViews[i].setTextColor(Color.BLACK);
        }

        selectedTableTextView.setText("Selected Table: " + tnumber);
    }

}