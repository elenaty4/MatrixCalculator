package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MultiplicationDisplay extends AppCompatActivity {

    private int rows1;
    private int columns1;
    private int rows2;
    private int columns2;
    private double[][] matrix1;
    private double[][] matrix2;
    private double[][] productMatrix;
    private String calcType;
    private GridLayout gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_display);

        Bundle extras = getIntent().getExtras();
        rows1 = extras.getInt("rows1");
        columns1 = extras.getInt("columns1");
        rows2 = extras.getInt("rows2");
        columns2 = extras.getInt("columns2");
        calcType = extras.getString("calc_type");
        matrix1 = (double[][]) extras.getSerializable("matrix1");
        matrix2 = (double[][]) extras.getSerializable("matrix2");

        //gridlayout
        gd = (GridLayout) findViewById(R.id.gridM);
        gd.setRowCount(rows1);
        gd.setColumnCount(columns2);
        gd.setPadding(10,10,10,10);
        TextView txt;
        String strValue;

        productMatrix = new double[rows1][columns2];
        calculate(matrix1, matrix2);
        for(int r = 0; r < rows1; r++)
        {
            for(int c = 0; c < columns2; c++)
            {
                txt = new TextView(this);
                strValue = Double.toString(productMatrix[r][c]);
                txt.setText(strValue);
                txt.setWidth(100);
                txt.setHeight(100);
                gd.addView(txt);
            }
        }


    }

    private void calculate(double[][] matrix1, double[][] matrix2)
    {
        for(int r = 0; r < rows1; r++)
        {
            for(int c = 0; c < columns2; c++)
            {
                for(int i = 0; i < matrix2.length; i++)
                {
                    productMatrix[r][c] = productMatrix[r][c] + (matrix1[r][i] * matrix2[i][c]);
                }
            }
        }
    }

    public void calcAgain(View view)
    {
        Intent intent = new Intent(this, MultMatrixDim.class);
        intent.putExtra("calc_Type", calcType);
        startActivity(intent);
    }

    public void backToMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
