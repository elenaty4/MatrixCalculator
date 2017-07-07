package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class MultMatrixInput extends AppCompatActivity {

    private int rows1;
    private int rows2;
    private int columns1;
    private int columns2;
    private String calcType;
    private GridLayout gd;
    private double[][] matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult_matrix_input);

        // GET THE MATRIX DIMENSIONS FOR BOTH MATRICES
        Bundle extras = getIntent().getExtras();
        rows1 = extras.getInt("rows1");
        columns1 = extras.getInt("columns1");
        rows2 = extras.getInt("rows2");
        columns2 = extras.getInt("columns2");
        calcType = extras.getString("calc_type");

        //Use GridLayout Instead
        gd = (GridLayout) findViewById(R.id.grid3);
        //This is for the first matrix
        gd.setRowCount(rows1);
        gd.setColumnCount(columns1);
        EditText edt;

        for(int r = 0; r < rows1*columns1; r++)
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
        for(int i = 0; i < rows1*columns1; i++)
        {
            edt = (EditText) gd.getChildAt(i);
            value = edt.getText().toString();
            if(value.equals(""))
            {
                ((EditText) gd.getChildAt(i)).setText("0");
            }
        }
    }

    public void nextMatrix(View view)
    {
        Intent intent = new Intent(this, MultMatrixInput2.class);
        //First check if there are empty spaces and string characters

        boolean invalidInput = false;
        TextView errortxt = (TextView) findViewById(R.id.textView17);
        String value;
        EditText edt;
        for(int j = 0; j < rows1*columns1; j++)
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
            matrix = new double[rows1][columns1];
            for(int r = 0; r < rows1; r++)
            {
                for(int c = 0; c < columns1; c++)
                {
                    //how to put array into matrix?
                    if(i < rows1*columns1)
                    {
                        edt = (EditText) gd.getChildAt(i);
                        value = edt.getText().toString();
                        matrix[r][c] = Double.parseDouble(value);
                        i++;
                    }
                }
            }

            intent.putExtra("rows1", rows1);
            intent.putExtra("columns1", columns1);
            intent.putExtra("rows2", rows2);
            intent.putExtra("columns2", columns2);
            intent.putExtra("calc_type", calcType);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("matrix", matrix);
            intent.putExtras(mBundle);
            startActivity(intent);
        }

    }
}
