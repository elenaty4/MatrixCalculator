package com.myblueshare.matrixcalculator.matrixcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.List;

public class AddSubtractMatrixInput2 extends AppCompatActivity {

    //FOR THE 2ND MATRIX INPUT
    private MatrixAdapter adapter;
    private List<Matrix> matrixList;
    private GridView gridView;
    private int rows;
    private int columns;
    private String calcType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subtract_matrix_input2);

        // GET THE MATRIX DIMENSIONS FOR BOTH MATRICES
        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");


    }


    public void submitButton(View view)
    {

    }

    public void setEmptyToZero(View view)
    {

    }
}
