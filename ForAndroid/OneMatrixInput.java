package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class OneMatrixInput extends AppCompatActivity {

    private int rows;
    private int columns;
    private String calcType;
    private double[][] matrix;
    GridLayout gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_matrix_input);

        // GET THE MATRIX DIMENSIONS FOR BOTH MATRICES
        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");

        //Use GridLayout Instead
        gd = (GridLayout) findViewById(R.id.grid6);
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

    public void submitButton(View view)
    {
        //First check if there are empty spaces and string characters

        boolean invalidInput = false;
        TextView errortxt = (TextView) findViewById(R.id.textView14);
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


                Intent intent;
                if(calcType.equals("ROW_ECHELON"))
                {
                    intent = new Intent(this, RowEchelonDisplay.class);
                    intent.putExtra("rows", rows);
                    intent.putExtra("columns", columns);
                    intent.putExtra("calc_type", calcType);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("matrix", matrix);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
                else if(calcType.equals("REDUCED_ROW_ECHELON"))
                {
                    intent = new Intent(this, RREFDisplay.class);
                    intent.putExtra("rows", rows);
                    intent.putExtra("columns", columns);
                    intent.putExtra("calc_type", calcType);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("matrix", matrix);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
                else if(calcType.equals("TRANSPOSE"))
                {
                    intent = new Intent(this, TransposeDisplay.class);
                    intent.putExtra("rows", rows);
                    intent.putExtra("columns", columns);
                    intent.putExtra("calc_type", calcType);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("matrix", matrix);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
                else if(calcType.equals("INVERSE"))
                {
                    intent = new Intent(this, InverseDisplay.class);
                    intent.putExtra("dims", rows);
                    intent.putExtra("calc_type", calcType);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("matrix", matrix);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }

            }

        }
    }
}
