package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeterminantDisplay extends AppCompatActivity {

    private int dims;
    private double[][] _matrix;
    private double determinant = 1;
    private String calcType;
    private boolean negation = false;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determinant_display);

        Bundle extras = getIntent().getExtras();
        dims = extras.getInt("dims");
        calcType = extras.getString("calc_type");
        _matrix = (double[][]) extras.getSerializable("matrix");

        TextView txt = new TextView(this);
        String strValue;
        calculate(_matrix);

        ll = (LinearLayout) findViewById(R.id.linearLayout);
        strValue = Double.toString(determinant);
        txt.setText(strValue);
        txt.setWidth(100);
        txt.setHeight(100);
        ll.addView(txt);
    }

    private void calculate(double[][] matrix)
    {
        double[][] reducedMatrix = _matrix; //the reduced matrix will change in rref
        int dimension = reducedMatrix.length;

        reducedMatrix = swapRows(_matrix);
        for (int p = 0; p < reducedMatrix.length; ++p)
        {
            //Make this pivot into a 1
            double pivot = reducedMatrix[p][p]; //Very first element is pivot if it's not zero
            if (pivot != 0)
            {
                //Scaling Rows: scalar * det
                double scalar = 1 / pivot;
                determinant = determinant * pivot;
                for (int i = 0; i < reducedMatrix[p].length; ++i) //interate through the ROW
                {
                    reducedMatrix[p][i] = reducedMatrix[p][i] * scalar;
                }
            }

            //Make other rows zero
            //Row Replacement: Nothing happens
            for (int r = 0; r < reducedMatrix.length; ++r)
            {
                if (r != p)
                {
                    double f = reducedMatrix[r][p];
                    for (int i = 0; i < reducedMatrix[r].length; ++i)
                    {
                        reducedMatrix[r][i] = reducedMatrix[r][i] - f * reducedMatrix[p][i];
                        //Turn the weird negative zeros to positive zeros
                        //But how?
                    }
                }
            }
        }

        if (negation)
        {
            determinant = -determinant;
        }

        //Now check if there is a zero in the diagonal.
        //If there is one then the determinant is zero
        for(int d = 0; d < dimension; d++)
        {
            if(reducedMatrix[d][d] == 0)
            {
                determinant = 0;
            }
        }
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

    private double[][] swapRows(double[][] matrix) //Swap the rows
    {
        int swapCount = 0;
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
                            //Swapping Rows: -det

                            if(matrix[r] != matrix[pivots])
                            {
                                rowHolder = matrix[r];
                                matrix[r] = matrix[pivots];
                                matrix[pivots] = rowHolder;
                                swapCount++;
                            }
                            if(swapCount%2 != 0) //check if it's not even
                            {
                                //then -det occurs
                                negation = true;
                            }

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
        Intent intent = new Intent(this, SquareMatrixDim.class);
        intent.putExtra("calc_Type", calcType);
        startActivity(intent);
    }

    public void backToMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
