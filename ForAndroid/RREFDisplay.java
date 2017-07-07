package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class RREFDisplay extends AppCompatActivity {

    private int rows;
    private int columns;
    private double[][] matrix;
    private double[][] rrefMatrix;
    private String calcType;
    private GridLayout gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rrefdisplay);

        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");
        matrix = (double[][]) extras.getSerializable("matrix");

        //Use GridLayout Instead
        gd = (GridLayout) findViewById(R.id.gridRREF);
        gd.setRowCount(rows);
        gd.setColumnCount(columns);
        gd.setPadding(10,10,10,10);
        TextView txt;
        String strValue;

        rrefMatrix = new double[rows][columns];
        calculate(matrix);

        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                txt = new TextView(this);
                strValue = Double.toString(rrefMatrix[r][c]);
                txt.setText(strValue);
                txt.setWidth(100);
                txt.setHeight(100);
                gd.addView(txt);
            }
        }
    }

    private void calculate(double[][] matrix)
    {
        rrefMatrix = matrix;

        rrefMatrix = swapRows(rrefMatrix);

        if(rows <= columns)
        {
            for (int p = 0; p < rrefMatrix.length; ++p)
            {
                //Make this pivot into a 1
                double pivot = rrefMatrix[p][p]; //Very first element is pivot if it's not zero
                if (pivot != 0)
                {
                    //SCALE THE PIVOT AND EVERYTHING ELSE IN THE ROW MUST BE MULTIPLIED WITH IT
                    double scalar = 1 / pivot;
                    for (int i = 0; i < rrefMatrix[p].length; ++i) //interate through the ROW
                    {
                        rrefMatrix[p][i] = rrefMatrix[p][i] * scalar;
                    }
                }

                //Make other rows zero
                for (int r = 0; r < rrefMatrix.length; ++r)
                {
                    if (r != p)
                    {
                        double f = rrefMatrix[r][p];
                        for (int i = 0; i < rrefMatrix[r].length; ++i)
                        {
                            rrefMatrix[r][i] = rrefMatrix[r][i] - f * rrefMatrix[p][i];
                            //Turn the weird negative zeros to positive zeros
                            //But how?
                        }
                    }
                }
            }
        }
        else if(rows > columns)
        {
            for (int p = 0; p < rrefMatrix[0].length; ++p)
            {
                //Make this pivot into a 1
                double pivot = rrefMatrix[p][p]; //Very first element is pivot if it's not zero
                if (pivot != 0)
                {
                    //SCALE THE PIVOT AND EVERYTHING ELSE IN THE ROW MUST BE MULTIPLIED WITH IT
                    double scalar = 1 / pivot;
                    for (int i = 0; i < rrefMatrix[p].length; ++i) //interate through the ROW
                    {
                        rrefMatrix[p][i] = rrefMatrix[p][i] * scalar;
                    }
                }

                //Make other rows zero
                for (int r = 0; r < rrefMatrix.length; ++r)
                {
                    if (r != p)
                    {
                        double f = rrefMatrix[r][p];
                        for (int i = 0; i < rrefMatrix[r].length; ++i)
                        {
                            rrefMatrix[r][i] = rrefMatrix[r][i] - f * rrefMatrix[p][i];
                            //Turn the weird negative zeros to positive zeros
                            //But how?
                        }
                    }
                }
            }
        }
    }

    private double[][] swapRows(double[][] matrix) //Swap the rows
    {
        double[][] swappedMatrix;
        int c = 0; //column iterator
        //increases by 1 everytime a pivot is found in order to swap pivotRows to the top correctly
        int pivots = 0;

        //checks if there's a zero column and checks if there are only zeros before a non-zero
        int zeroCounter = 0;
        double element;
        double[] rowHolder; //holds on to one row during the swap

        while (c < matrix[0].length)
        {
            //iterate through the first row

            for(int r=0; r<matrix.length; r++)
            {
                element = matrix[r][c];
                if(element != 0)
                {
                    //check if there are zeros above it
                    if(r == zeroCounter)
                    {
                        //check if there are no more NON pivot rows for that row
                        if(pivots < matrix.length)
                        {
                            //then the swap occurs
                            rowHolder = matrix[r];
                            matrix[r] = matrix[pivots];
                            matrix[pivots] = rowHolder;

                            c++; //go to next column
                            //check if we are at the last column
                            if (c < matrix[0].length)
                            {
                                pivots++;
                                r=pivots-1; //draw a diagram to make this work
                                zeroCounter=pivots;
                            }
                            else
                            {
                                r = matrix.length;
                            }
                        }
                    }
                }
                else //theres a zero in the column
                {
                    zeroCounter++;
                }

                //check if the column has ALL zeros (aka, a Zero Column)
                if(zeroCounter >= matrix.length)
                {
                    c++; //go to next column
                    //check if we reach the last column
                    if (c < matrix[0].length)
                    {
                        r=pivots-1; //draw a diagram to make this work
                        zeroCounter=pivots;
                    }
                    else
                    {
                        r = matrix.length;
                    }
                }
            }

        }
        swappedMatrix = matrix;
        return swappedMatrix;
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
