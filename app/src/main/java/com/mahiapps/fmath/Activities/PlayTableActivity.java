package com.mahiapps.fmath.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mahiapps.fmath.R;

public class PlayTableActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private EditText[] editTexts;
    private Button verifyButton;
    private TextView selectedTableTextView;

    private int tableNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_table);

        seekBar = findViewById(R.id.seekBar);
        selectedTableTextView = findViewById(R.id.selectedTableTextView);
        editTexts = new EditText[10];

        // Initialize EditText fields
        for (int i = 0; i < 10; i++) {
            int editTextId = getResources().getIdentifier("editText" + (i + 1), "id", getPackageName());
            editTexts[i] = findViewById(editTextId);

            final int nextIndex = (i + 1) % 10; // Calculate the index of the next EditText
            final EditText currentEditText = editTexts[i];

            // Add TextWatcher to move to the next EditText automatically
            currentEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        // Move to the next EditText when "Enter" key is pressed
                        editTexts[nextIndex].requestFocus();
                        return true;
                    }
                    return false;
                }
            });
        }

        verifyButton = findViewById(R.id.verifyButton);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update the table when the SeekBar progress changes
                updateTable(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                clearTable();
            }
        });

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the entered values match the selected table
                verifyTable();
            }
        });
    }

    private void clearTable() {
        for(int i = 0; i < 10; i++){
            editTexts[i].setText("");
        }
        editTexts[0].requestFocus();
    }

    private void updateTable(int tnumber) {
        // Update the column headers based on the selected table
//        for (int i = 0; i < 10; i++) {
//            int value = (i + 1) * tableNumber;
//            editTexts[i].setHint(String.valueOf(value));
//            editTexts[i].setText("");
//            editTexts[i].setTextColor(Color.BLACK); // Reset text color to black
//        }

        // Update the selected table number in the TextView
        tableNumber = tnumber;
        selectedTableTextView.setText("Selected Table: " + tableNumber);
    }

    private void verifyTable() {
        // Check if the entered values match the selected table
        boolean wrong = false;

        for (int i = 0; i < 10; i++) {
            int expectedValue = (i + 1) * tableNumber;
            String enteredValueStr = editTexts[i].getText().toString();
            if (enteredValueStr.equals(String.valueOf(expectedValue))) {
                // Set text color to green for correct values
                editTexts[i].setTextColor(Color.GREEN);
            } else {
                // Set text color to red for incorrect values
                wrong = true;
                editTexts[i].setTextColor(Color.RED);
            }
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        if(wrong){
            int rights = preferences.getInt("WRONG", 0);
            editor.putInt("WRONG", rights + 1);
            editor.apply();
        } else{
            int rights = preferences.getInt("RIGHT", 0);
            editor.putInt("RIGHT", rights + 1);
            editor.apply();
        }
    }
}