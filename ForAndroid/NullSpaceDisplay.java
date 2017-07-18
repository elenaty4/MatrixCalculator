package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NullSpaceDisplay extends AppCompatActivity {

    private int rows;
    private int columns;
    private double[][] matrix;
    private ArrayList<double[]> parametricV; //Parametric Vectors
    private double[][] nullSpace; //It's an array containing the two nullSpace vectors (as rows)
    private double value;
    private int numPivots = 0;
    private int numFreeCols = 0;
    private boolean isEmptySet = false; //empty basis
    private String calcType;
    private LinearLayout verticalL; //to hold the elements in the basis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_null_space_display);

        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");
        matrix = (double[][]) extras.getSerializable("matrix");

        verticalL = (LinearLayout) findViewById(R.id.vertical1);
        calculate(matrix);
        LinearLayout vector; //a vector in the basis
        TextView txt;
        String strValue;

        //add the vectors into the Vertical Layout. The vertical layout contains a list of horizontal layouts
        if(!isEmptySet)
        {
            //TODO
            for(int i = 0; i < nullSpace.length; i++) //column
            {
                //create the vector display
                vector = new LinearLayout(this);
                vector.setOrientation(LinearLayout.HORIZONTAL);
                vector.setBackgroundResource(R.drawable.border);
                for (int c = 0; c < nullSpace[0].length; c++) //row
                {
                    //add the elements of the nullSpace matrix into the vectors column by column
                    txt = new TextView(this);
                    strValue = Double.toString(nullSpace[i][c]);
                    txt.setText(strValue);
                    txt.setWidth(100);
                    txt.setHeight(100);
                    verticalL.addView(txt);
                }
                //Now add the vector into the vertical layout
                verticalL.addView(vector);
            }
        }
        else
        {
            //show a message that the set is empty
            TextView message = (TextView) findViewById(R.id.textView28);
            message.setVisibility(View.VISIBLE);
        }
    }

    private void calculate(double[][] matrix)
    {
        double[][] reducedMatrix = matrix;
        reducedMatrix = rref(reducedMatrix);

        parametricV = new ArrayList<>();
        double[] rowForAL; //row for ArrayList

        //While counting pivots, if isFreeColumn is true, then remove THAT CURRENT ROW
        boolean pivotFound;
        boolean isFreeColumn; //if it's a column of free variables
        boolean oneIsAdded; //an extra value "1" is added to the rowForAL
        double pivot;
        double element;
        int r = 0;
        //Iterate by columns

        for(int c =0; c < reducedMatrix[0].length; c++)
        {
            //reset the booleans and r after iterating through one column;
            pivotFound = false;
            isFreeColumn = false;
            oneIsAdded = false;
            r=0; //Reset r
            //Find the pivot in the column
            while(r < rows && !pivotFound && !isFreeColumn)
            {
                pivot = reducedMatrix[r][c];
                if(pivot == 1) //rref pivots must be ones
                {
                    //check if there are only zeros below the pivot
                    for(int i = r+1; i < rows; i++)
                    {
                        element = reducedMatrix[i][c];
                        if(element != 0)
                        {
                            isFreeColumn = true;
                            rowForAL = new double[columns];
                            int limit = 0; //Limit is here to prevent out of bounds error
                            if(columns >= rows)
                            {
                                limit = rows;
                            }
                            else if(rows > columns)
                            {
                                limit = columns;
                            }
                            for(int j=0; j < limit; j++)
                            {
                                if(reducedMatrix[j][c] == 0) //To prevent -0.0
                                {
                                    element = reducedMatrix[j][c];
                                }
                                else
                                {
                                    element = -1 * reducedMatrix[j][c]; //negate the element
                                }
                                if(j == c) //c is the freeColumn position
                                {
                                    rowForAL[j] = 1;
                                    oneIsAdded = true;
                                    if(j+1 < columns) //columns is the size of rowForAL
                                    {
                                        rowForAL[j+1] = element;
                                    }
                                }
                                else
                                {
                                    rowForAL[j] = element;
                                }

                                if(!oneIsAdded && j+1 == rows) //if one is NOT added yet
                                {
                                    rowForAL[j+1] = 1;
                                }
                            }
                            parametricV.add(rowForAL); //Add the row to the ArrayList
                        }
                    }

                    if(!isFreeColumn)
                    {
                        pivotFound = true;
                        numPivots++;
                    }
                }
                else if(pivot != 0 && pivot != 1)
                {
                    //Then it's a free variable in a free variable column
                    isFreeColumn = true;
                    rowForAL = new double[columns];
                    int limit = 0; //Limit is here to prevent out of bounds error
                    if(columns >= rows)
                    {
                        limit = rows;
                    }
                    else if(rows > columns)
                    {
                        limit = columns;
                    }

                    for(int j=0; j < limit; j++)
                    {
                        if(reducedMatrix[j][c] == 0) //To prevent -0.0
                        {
                            element = reducedMatrix[j][c];
                        }
                        else
                        {
                            element = -1 * reducedMatrix[j][c]; //negate the element
                        }
                        if(j == c) //c is the freeColumn position
                        {
                            rowForAL[j] = 1;
                            oneIsAdded = true;
                            if(j+1 < columns) //columns is the size of rowForAL
                            {
                                rowForAL[j+1] = element;
                            }
                        }
                        else
                        {
                            rowForAL[j] = element;
                        }

                        if(!oneIsAdded && j+1 == rows) //if one is NOT added yet
                        {
                            rowForAL[j+1] = 1;
                        }
                    }
                    parametricV.add(rowForAL); //Add the row to the ArrayList

                }
                r++;
                //if we still haven't find a pivot in the column and we reached the end
                if(r == rows && !pivotFound) //just in case if we encounter a zeroColumn
                {
                    isFreeColumn = true;
                    rowForAL = new double[columns];
                    int limit = 0; //Limit is here to prevent out of bounds error
                    if(columns >= rows)
                    {
                        limit = rows;
                    }
                    else if(rows > columns)
                    {
                        limit = columns;
                    }
                    for(int j=0; j < limit; j++)
                    {
                        if(reducedMatrix[j][c] == 0) //To prevent -0.0
                        {
                            element = reducedMatrix[j][c];
                        }
                        else
                        {
                            element = -1 * reducedMatrix[j][c]; //negate the element
                        }
                        if(j == c) //c is the freeColumn position
                        {
                            rowForAL[j] = 1;
                            oneIsAdded = true;
                            if(j+1 < columns) //columns is the size of rowForAL
                            {
                                rowForAL[j+1] = element;
                            }
                        }
                        else
                        {
                            rowForAL[j] = element;
                        }

                        if(!oneIsAdded && j+1 == rows) //if one is NOT added yet
                        {
                            rowForAL[j+1] = 1;
                        }
                    }
                    parametricV.add(rowForAL); //Add the row to the ArrayList
                }
            }
        }

        //Check if there are pivots in every column. Then that means the NullSpace is empty
        if(numPivots == columns)
        {
            //Then _nullSpace is an empty set
            isEmptySet = true;
        }
        else
        {
            //Now make _nullSpace = ArrayList
            nullSpace = new double[parametricV.size()][columns];
            for(int r2 = 0; r2 < parametricV.size(); r2++)
            {
                for(int c2=0; c2 < parametricV.get(0).length; c2++)
                {
                    nullSpace[r2][c2] = parametricV.get(r2)[c2];
                }
            }
        }
        numFreeCols = nullSpace.length - numPivots;
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
