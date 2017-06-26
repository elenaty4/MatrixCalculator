package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddSubtractMatrixDim extends AppCompatActivity {
    private String calcType;
    private Spinner matrixRows;
    private Spinner matrixColumns;
    private final Integer[] DIMENSIONS = {2,3,4,5,6,7,8,9,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        //Get the calculation type
        Bundle extras = getIntent().getExtras();
        //If it's addition, we add, if it's subtraction, we subtract
        calcType = extras.getString("calc_type");

        //Matrix Rows dropdown
        matrixRows = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                DIMENSIONS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matrixRows.setAdapter(adapter);

        //Matrix Columns dropdown
        matrixColumns = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                DIMENSIONS);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matrixColumns.setAdapter(adapter);
    }

    //When the submit button is clicked
    public void submitButton(View view)
    {
        //get the dimensions
        Integer rows = (Integer) matrixRows.getSelectedItem();
        Integer columns = (Integer) matrixColumns.getSelectedItem();

        Intent intent = new Intent(this, AddSubtractMatrixInput.class);
        //Transferring rows and columns into another activity
        intent.putExtra("rows", rows);
        intent.putExtra("columns", columns);
        intent.putExtra("calc_type", calcType);
        startActivity(intent);
    }

    //When the GoBack button is clicked
    public void goBackButton(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
