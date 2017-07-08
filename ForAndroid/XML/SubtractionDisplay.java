package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class SubtractionDisplay extends AppCompatActivity {

    private int rows;
    private int columns;
    private double[][] matrix1;
    private double[][] matrix2;
    private double[][] diffMatrix;
    private String calcType;
    private GridLayout gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction_display);

        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");
        matrix1 = (double[][]) extras.getSerializable("matrix1");
        matrix2 = (double[][]) extras.getSerializable("matrix2");

        //Use GridLayout Instead
        gd = (GridLayout) findViewById(R.id.gridS);
        gd.setRowCount(rows);
        gd.setColumnCount(columns);
        gd.setPadding(10,10,10,10);
        TextView txt;
        String strValue;

        diffMatrix = new double[rows][columns];
        calculate(matrix1, matrix2);
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                txt = new TextView(this);
                strValue = Double.toString(diffMatrix[r][c]);
                txt.setText(strValue);
                txt.setWidth(100);
                txt.setHeight(100);
                gd.addView(txt);
            }
        }

    }

    private void calculate(double[][] matrix1, double[][] matrix2)
    {
        //Addition calculation
        double difference;
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                difference = matrix1[r][c] - matrix2[r][c];
                diffMatrix[r][c] = difference;
            }
        }
    }

    public void calcAgain(View view)
    {
        Intent intent = new Intent(this, AddSubtractMatrixDim.class);
        intent.putExtra("calc_Type", calcType);
        startActivity(intent);
    }

    public void backToMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
