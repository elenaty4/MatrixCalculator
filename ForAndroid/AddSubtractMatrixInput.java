package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class AddSubtractMatrixInput extends AppCompatActivity {

    private int rows;
    private int columns;
    private String calcType;
    private double matrix[][];
    GridLayout gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subtract_matrix_input);

        // GET THE MATRIX DIMENSIONS FOR BOTH MATRICES
        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");

        //Use GridLayout Instead
        gd = (GridLayout) findViewById(R.id.grid1);
        gd.setRowCount(rows);
        gd.setColumnCount(columns);
        EditText edt;

        for(int r = 0; r < rows*columns; r++)
        {
            edt = new EditText(this);
            edt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            edt.setWidth(100);
            gd.addView(edt);
        }

    }

    public void setEmptyToZero()
    {
        //Iterate through whole GridLayout to check if there are empty fields
        String value;
        EditText edt;
        for(int i = 0; i < rows*columns; i++)
        {
            edt = (EditText) gd.getChildAt(i);
            value = edt.getText().toString();
            if(value.equals(""))
            {
                ((EditText) gd.getChildAt(i)).setText(0); //This doesn't work
            }
        }
    }

    public void nextMatrix(View view)
    {
        Intent intent = new Intent(this, AddSubtractMatrixInput2.class);
        //First check if there are empty spaces and string characters

        boolean invalidInput = false;
        TextView errortxt = (TextView) findViewById(R.id.textView11);
        String value;
        EditText edt;
        for(int j = 0; j < rows*columns; j++)
        {
            edt = (EditText) gd.getChildAt(j);
            value = edt.getText().toString();
            //checking for empty spaces
            if(value.equals(""))
            {
                invalidInput = true;
                errortxt.setVisibility(View.VISIBLE); //make the error message visible
            }
            //checking if input is a number
            //Do this later because wifi is being a betch

        }

        if(!invalidInput)
        {
            //get the matrix
            int i = 0;
            matrix = new double[rows][columns];
            for(int r = 0; r < rows; r++)
            {
                for(int c = 0; c < columns; c++)
                {
                    //how to put array into matrix?
                    if(i < rows*columns)
                    {
                        edt = (EditText) gd.getChildAt(i);
                        value = edt.getText().toString();
                        matrix[r][c] = Double.parseDouble(value);
                        i++;
                    }
                }
            }

            intent.putExtra("rows", rows);
            intent.putExtra("columns", columns);
            intent.putExtra("calc_type", calcType);
            //How to pass a matrix to another activity?
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("matrix", matrix);
            intent.putExtras(mBundle);
            startActivity(intent);
        }
    }
}
