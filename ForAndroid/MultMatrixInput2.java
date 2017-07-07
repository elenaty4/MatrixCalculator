package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class MultMatrixInput2 extends AppCompatActivity {

    private int rows1;
    private int rows2;
    private int columns1;
    private int columns2;
    private String calcType;
    private GridLayout gd;
    private double[][] matrix1;
    private double[][] matrix2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult_matrix_input2);

        // GET THE MATRIX DIMENSIONS FOR BOTH MATRICES
        Bundle extras = getIntent().getExtras();
        rows1 = extras.getInt("rows1");
        columns1 = extras.getInt("columns1");
        rows2 = extras.getInt("rows2");
        columns2 = extras.getInt("columns2");
        calcType = extras.getString("calc_type");
        matrix1 = (double[][]) extras.getSerializable("matrix");

        //Use GridLayout Instead
        gd = (GridLayout) findViewById(R.id.grid5);
        //This is for the first matrix
        gd.setRowCount(rows2);
        gd.setColumnCount(columns2);
        EditText edt;

        for(int r = 0; r < rows2*columns2; r++)
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
        for(int i = 0; i < rows2*columns2; i++)
        {
            edt = (EditText) gd.getChildAt(i);
            value = edt.getText().toString();
            if(value.equals(""))
            {
                ((EditText) gd.getChildAt(i)).setText("0");
            }
        }
    }

    public void submitButton(View view)
    {
        //First check if there are empty spaces and string characters

        boolean invalidInput = false;
        TextView errortxt = (TextView) findViewById(R.id.textView14);
        String value;
        EditText edt;
        for(int j = 0; j < rows2*columns2; j++)
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
            Intent intent = new Intent(this, MultiplicationDisplay.class);
            //get the matrix
            int i = 0;
            matrix2 = new double[rows2][columns2];
            for(int r = 0; r < rows2; r++)
            {
                for(int c = 0; c < columns2; c++)
                {
                    //how to put array into matrix?
                    if(i < rows2*columns2)
                    {
                        edt = (EditText) gd.getChildAt(i);
                        value = edt.getText().toString();
                        matrix2[r][c] = Double.parseDouble(value);
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
            mBundle.putSerializable("matrix1", matrix1);
            intent.putExtras(mBundle);
            mBundle.putSerializable("matrix2", matrix2);
            intent.putExtras(mBundle);
            startActivity(intent);
        }
    }
}
