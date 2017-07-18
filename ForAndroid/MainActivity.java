package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity implements OnTouchListener {

    private View view;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.entire_view);
        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent e)
    {
        intent = new Intent(this, CalcSelection.class);
        startActivity(intent);
        return true;
    }
}
