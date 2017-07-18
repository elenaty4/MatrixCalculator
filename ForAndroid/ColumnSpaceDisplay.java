package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ColumnSpaceDisplay extends AppCompatActivity {

    private int rows;
    private int columns;
    private double[][] matrix;
    private double[][] columnSpace; //Contains the columns(vectors) that have PIVOTS
    private int numPivotCols = 0; //Number of pivot Columns will be the column size of _columnSpace
    private double value;
    private boolean isEmptySet = false;
    private String calcType;
    private LinearLayout verticalL; //to hold the elements in the basis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_space_display);

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
            //show a message that the set is empty
            TextView message = (TextView) findViewById(R.id.textView45);
            message.setVisibility(View.VISIBLE);
        }
    }

    private void calculate(double[][] matrix)
    {
        double[][] reducedMatrix = matrix;
        reducedMatrix = rref(reducedMatrix);

        //Elim is created to eliminate the corresponding column the doesn't have a pivot
        //And put it into the _columnSpace matrix
        ArrayList<double[]> elim = new ArrayList<>();
        double[][] transposeM = matrix;
        transposeM = transpose(transposeM);
        for(int r1 = 0; r1 < transposeM.length; r1++)
        {
            elim.add(transposeM[r1]);
        }

        //While counting pivots, if isFreeColumn is true, then remove THAT CURRENT ROW
        boolean pivotFound;
        boolean isFreeColumn; //if it's a column of free variables
        double pivot;
        int removeCount = 0; //counts how many times a row has been removed in elim ArrayList
        //removeCount is needed to prevent out of bounds exception
        int r = 0;
        //Iterate by columns
        for(int c =0; c < reducedMatrix[0].length; c++)
        {
            //reset the booleans and r after iterating through one column;
            pivotFound = false;
            isFreeColumn = false;
            r=0; //Reset r
            //Find the pivot in the column
            while(r < rows && !pivotFound && !isFreeColumn)
            {
                pivot = reducedMatrix[r][c];
                if(pivot == 1) //rref pivots must be ones
                {
                    //check if there are only zeros below the pivot
                    double element;
                    for(int i = r+1; i < rows; i++)
                    {
                        element = reducedMatrix[i][c];
                        if(element != 0)
                        {
                            isFreeColumn = true;
                            elim.remove(c-removeCount);
                            removeCount++;
                        }
                    }

                    if(!isFreeColumn)
                    {
                        pivotFound = true;
                        numPivotCols++;
                    }
                }
                else if(pivot != 0 && pivot != 1)
                {
                    //Then it's a free variable in a free variable column
                    isFreeColumn = true;
                    elim.remove(c-removeCount);
                    removeCount++;
                }
                r++;
                //if we still haven't find a pivot in the column and we reached the end
                if(r == rows && !pivotFound) //just in case if we encounter a zeroColumn
                {
                    isFreeColumn = true;
                    elim.remove(c-removeCount);
                    removeCount++;
                }
            }
        }

        if(numPivotCols == 0)
        {
            //Then the set is empty
            isEmptySet = true;
        }
        else
        {
            //Now make _columnSpace = elim ArrayList
            columnSpace = new double[elim.size()][rows];
            for(int r2 = 0; r2 < elim.size(); r2++)
            {
                for(int c2=0; c2 < elim.get(0).length; c2++)
                {
                    columnSpace[r2][c2] = elim.get(r2)[c2];
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
