package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class TransposeDisplay extends AppCompatActivity {

    private int rows;
    private int columns;
    private double[][] matrix;
    private double[][] tpMatrix;
    private String calcType;
    private GridLayout gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transpose_display);

        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");
        matrix = (double[][]) extras.getSerializable("matrix");

        //Use GridLayout Instead
        gd = (GridLayout) findViewById(R.id.gridT);
        gd.setRowCount(columns);
        gd.setColumnCount(rows);
        gd.setPadding(10,10,10,10);
        TextView txt;
        String strValue;

        tpMatrix = new double[columns][rows];
        calculate(matrix);

        for(int r = 0; r < columns; r++)
        {
            for(int c = 0; c < rows; c++)
            {
                txt = new TextView(this);
                strValue = Double.toString(tpMatrix[r][c]);
                txt.setText(strValue);
                txt.setWidth(100);
                txt.setHeight(100);
                gd.addView(txt);
            }
        }

    }

    private void calculate(double[][] matrix)
    {
        for(int r=0; r < rows; r++)
        {
            for(int c=0; c < columns; c++)
            {
                tpMatrix[c][r] = matrix[r][c];
            }
        }
    }

    public void calcAgain(View view)
    {
        Intent intent = new Intent(this, OneMatrixDim.class);
        intent.putExtra("calc_Type", calcType);
        startActivity(intent);
    }

    public void backToMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
