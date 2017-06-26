package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MultMatrixDim extends AppCompatActivity {

    //When the user selects Mutiplication

    private String calcType;
    private Spinner matrix1Rows;
    private Spinner matrix1Columns;
    private Spinner matrix2Rows;
    private Spinner matrix2Columns;
    private final Integer[] DIMENSIONS = {2,3,4,5,6,7,8,9,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult_matrix_dim);

        //Get the calculation type
        Bundle extras = getIntent().getExtras();
        //This class is only for Multiplication but I'll still put it here
        calcType = extras.getString("calc_type");

        //Matrix 1 Rows dropdown
        matrix1Rows = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                DIMENSIONS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matrix1Rows.setAdapter(adapter);

        //Matrix 1 Columns dropdown
        matrix1Columns = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                DIMENSIONS);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matrix1Columns.setAdapter(adapter);

        //Matrix 2 Rows dropdown
        matrix2Rows = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<Integer> adapter3 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                DIMENSIONS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matrix2Rows.setAdapter(adapter);

        //Matrix 2 Columns dropdown
        matrix2Columns = (Spinner)findViewById(R.id.spinner4);
        ArrayAdapter<Integer> adapter4 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
                DIMENSIONS);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matrix2Columns.setAdapter(adapter);

    }

    //When the submit button is clicked
    public void submitButton(View view)
    {
        Intent intent;
        //get the dimensions
        Integer rows1 = (Integer) matrix1Rows.getSelectedItem();
        Integer columns1 = (Integer) matrix1Columns.getSelectedItem();
        Integer rows2 = (Integer) matrix2Rows.getSelectedItem();
        Integer columns2 = (Integer) matrix2Columns.getSelectedItem();

        //check if the num columns of first matrix matches the num rows of the 2nd matrix
        boolean rowColDoNotMatch = columns1 != rows2;

        if(rowColDoNotMatch)
        {
            //Error message occurs
            TextView errorMessage = (TextView)findViewById(R.id.textView3);
            errorMessage.setVisibility(View.VISIBLE);
        }
        else {
            intent = new Intent(this, MultMatrixInput.class);
            //Transferring rows and columns into another activity
            intent.putExtra("rows1", rows1);
            intent.putExtra("columns1", columns1);
            intent.putExtra("rows2", rows2);
            intent.putExtra("columns2", columns2);
            intent.putExtra("calc_type", calcType);
            startActivity(intent);
        }
    }

    //When the GoBack button is clicked
    public void goBackButton(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
