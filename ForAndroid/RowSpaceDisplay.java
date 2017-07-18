package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class RowSpaceDisplay extends AppCompatActivity {

    private int rows;
    private int columns;
    private double[][] matrix;
    private double[][] rowSpace; //Contains the rows(arrays) that have PIVOTS
    private int numPivotRows = 0; //Number of Pivot Rows will be the size of _rowSpace
    private double value;
    private boolean isEmptySet = false;
    private String calcType;
    private LinearLayout verticalL; //to hold the elements in the basis


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_space_display);

        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");
        matrix = (double[][]) extras.getSerializable("matrix");

        verticalL = (LinearLayout) findViewById(R.id.vertical1);
        calculate(matrix);

        if(!isEmptySet)
        {

        }
        else
        {

        }
    }

    private void calculate(double[][] matrix)
    {
        double[][] reducedMatrix = matrix;
        reducedMatrix = rref(reducedMatrix);

        //Make ArrayList = the reducedMatrix
        ArrayList<double[]> elim = new ArrayList<>();
        for(int r = 0; r < rows; r++)
        {
            elim.add(reducedMatrix[r]);
        }

        //While counting pivots, if isFreeColumn is true, then remove THAT CURRENT ROW
        boolean pivotFound;
        boolean isZeroRow; //if it's a row of zeros. A free row is a row of zeros
        double pivot;
        int removeCount = 0; //counts how many times a row has been removed in elim ArrayList
        //removeCount is needed to prevent out of bounds exception
        int c = 0;

        //iterating by row
        for(int r =0; r < rows; r++)
        {
            pivotFound = false;
            isZeroRow = false;
            c=0; //reset c

            while(c < columns && !pivotFound && !isZeroRow)
            {
                pivot = reducedMatrix[r][c];
                if(pivot != 0)
                {
                    pivotFound = true;
                    numPivotRows++;
                }
                c++;
                //if we still haven't find a pivot in the row and we reached the end
                if(c == columns && !pivotFound) //just in case if we encounter a zeroRow
                {
                    isZeroRow = true;
                    elim.remove(r-removeCount);
                    removeCount++;
                }
            }
        }

        //Check if there are NO pivotRows. If so, then the set is empty
        if(numPivotRows == 0)
        {
            isEmptySet = true;
        }
        else
        {
            //Now make _rowSpace = elim
            rowSpace = new double[elim.size()][columns];
            for(int r2=0; r2 < elim.size(); r2++)
            {
                for(int c2=0; c2 < columns; c2++)
                {
                    rowSpace[r2][c2] = elim.get(r2)[c2];
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
        double[] rowHolder = new double[matrix.length]; //holds on to one row during the swap

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

    private double[][] rref(double[][] matrix)
    {
        double[][] rrefMatrix = matrix;
        rrefMatrix = swapRows(rrefMatrix);
        int rows = rrefMatrix.length;
        int columns = rrefMatrix[0].length;

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
        return rrefMatrix;
    }

    private double[][] transpose(double[][] matrix)
    {
        double[][] transposeMatrix = new double[columns][rows];

        for(int r=0; r < rows; r++)
        {
            for(int c=0; c < columns; c++)
            {
                transposeMatrix[c][r] = matrix[r][c];
            }
        }

        return transposeMatrix;
    }

    //onclick methods
    public void calcAgain(View view)
    {
        Intent intent = new Intent(this, OneMatrixDim.class);
        intent.putExtra("calc_Type", calcType);
        startActivity(intent);
    }

    public void backToMenu(View view)
    {
        Intent intent = new Intent(this, CalcSelection.class);
        startActivity(intent);
    }
}
