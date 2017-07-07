package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SquareMatrixDim extends AppCompatActivity {

    //For the rest of the calculations that require a SQUARE matrix

    private String calcType;
    private Spinner matrixDims;
    private final Integer[] DIMENSIONS = {2,3,4,5,6,7,8,9,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_matrix_dim);

        //Get the calculation type
        Bundle extras = getIntent().getExtras();
        calcType = extras.getString("calc_type");

        //Matrix Dimensions dropdown
        matrixDims = (Spinner)findViewById(R.id.spinner5);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                DIMENSIONS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matrixDims.setAdapter(adapter);

    }

    public void submitButton(View view){

        Integer dims = (Integer) matrixDims.getSelectedItem();

        Intent intent = new Intent(this, OneMatrixInput.class);
        intent.putExtra("rows", dims);
        intent.putExtra("columns", dims);
        intent.putExtra("calc_type", calcType);
        startActivity(intent);
    }
}
