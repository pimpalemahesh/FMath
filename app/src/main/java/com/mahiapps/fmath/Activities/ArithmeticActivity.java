package com.mahiapps.fmath.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.mahiapps.fmath.Adapters.CourseGVAdapter;
import com.mahiapps.fmath.Model.CourseModel;
import com.mahiapps.fmath.R;

import java.util.ArrayList;

public class ArithmeticActivity extends AppCompatActivity {

    GridView coursesGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic);

        coursesGV = findViewById(R.id.idGVcourses);
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();

        courseModelArrayList.add(new CourseModel("ADDITION", R.drawable.add));
        courseModelArrayList.add(new CourseModel("SUBTRACTION", R.drawable.minus));
        courseModelArrayList.add(new CourseModel("MULTIPLICATION", R.drawable.multiplication));
//        courseModelArrayList.add(new CourseModel("DIVISION", R.drawable.divide));

        CourseGVAdapter adapter = new CourseGVAdapter(this, courseModelArrayList);
        coursesGV.setAdapter(adapter);
    }
}