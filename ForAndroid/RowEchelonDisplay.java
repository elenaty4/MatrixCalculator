package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class RowEchelonDisplay extends AppCompatActivity {

    private int rows;
    private int columns;
    private double[][] matrix;
    private double[][] reMatrix;
    private String calcType;
    private GridLayout gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_echelon_display);

        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");
        matrix = (double[][]) extras.getSerializable("matrix");

        //Use GridLayout Instead
        gd = (GridLayout) findViewById(R.id.gridRE);
        gd.setRowCount(rows);
        gd.setColumnCount(columns);
        gd.setPadding(10,10,10,10);
        TextView txt;
        String strValue;

        reMatrix = new double[rows][columns];
        calculate(matrix);

        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                txt = new TextView(this);
                strValue = Double.toString(reMatrix[r][c]);
                txt.setText(strValue);
                txt.setWidth(100);
                txt.setHeight(100);
                gd.addView(txt);
            }
        }
    }

    private void calculate(double[][] matrix)
    {
        //Copy the matrix so that you can change it later
        reMatrix = matrix;
        //swap the rows
        reMatrix = swapRows(reMatrix);
        int pivotPosition = -1; //pivot position
        double element;
        double pivot;
        double[] rowHolder = new double[reMatrix[0].length]; //for row replacement
        boolean pivotFound = false;
        int i = 0;

        //Iterate by row first
        for(int r = 0; r < reMatrix.length; r++)
        {
            //iterate through that row
            while(!pivotFound && i < reMatrix[r].length)
            {
                pivot = reMatrix[r][i];
                //check if pivot is not zero. Zeros can never be pivots
                if(pivot != 0 && i > pivotPosition)
                {
                    //and if the pivotPosition is greater than the old pivotPosition
                    pivotFound = true;
                    pivotPosition = i;
                    //scale the row first
                    double scalar = 1 / pivot;
                    for(int j=0; j< reMatrix[r].length; j++)
                    {
                        reMatrix[r][j] = reMatrix[r][j] * scalar;
                    }

                    //now check what's under the pivot (r++) and if they are 0
                    //leave them alone, if not, ROW REPLACE
                    for(int u = r+1; u< reMatrix.length; u++)
                    {
                        element = reMatrix[u][i];
                        if(element != 0)
                        {
                            //Multiply the first row by the NEGATIVE element
                            //fr means first row
                            //iterate through the first row for it
                            for(int fr=0; fr < rowHolder.length; fr++)
                            {
                                rowHolder[fr] = reMatrix[r][fr];
                                rowHolder[fr] = rowHolder[fr] * -element;
                            }
                            //add the first row towards the second (u) row
                            //iterate through the second row
                            for(int sr=0; sr < reMatrix[0].length; sr++)
                            {
                                reMatrix[u][sr] = reMatrix[u][sr] + rowHolder[sr];
                            }
                        }
                    }
                    i=0; //i resets
                }
                else
                {
                    i++;
                }
            }
            pivotFound = false;
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
