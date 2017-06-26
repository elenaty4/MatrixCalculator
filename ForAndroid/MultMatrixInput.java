package com.myblueshare.matrixcalculator.matrixcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MultMatrixInput extends AppCompatActivity {

    private MatrixAdapter adapter;
    private List<Matrix> matrixList;
    private GridView gridView;
    private int rows1;
    private int columns1;
    private int rows2;
    private int columns2;
    private String calcType;

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

        //MATRIX 1 INPUT

        // INITIALISE THE GRID FOR MATRIX 1
        gridView = (GridView)findViewById(R.id.grid2);
        gridView.setNumColumns(columns1);

        // CREATE A LIST OF MATRIX OBJECTS
        matrixList = new ArrayList<>(); //THIS IS ONLY FOR THE MATRIX OF EDIT_TEXTS
        //Getting the matrix values will come from the user input

        // ADD SOME CONTENTS TO EACH ITEM
        for (int i=0;i<rows1;i++)
        {
            for (int j=0;j<columns1;j++)
            {
                matrixList.add(new Matrix(i,j));
            }
        }

        // CREATE AN ADAPTER  (MATRIX ADAPTER)
        adapter = new MatrixAdapter(getApplicationContext(),matrixList);

        // ATTACH THE ADAPTER TO GRID
        gridView.setAdapter(adapter);

    }

    public void nextMatrix(View view) //The next button goes to the next matrix
    {
        //It collects what the user inputted too
        //Checks if one of the fields are empty
        //But how to get the values from each EditText?

    }

    public void setEmptyToZero(View view) //Fills the empty text boxes to zero
    {
        //how to make the program add zeros to the text fields?
        //iterate through the textField matrix first. If an empty field is
        //found, add zero to it
        EditText textField;
        boolean fieldIsEmpty;

        // ITERATE THROUGH EACH CHILDS
        EditText element;
        String cell_value;

        //Iterate through GridView
        int gridSize = gridView.getChildCount();

        for(int i=0; i<gridSize; i++)
        {
            if(gridView.getChildAt(i) instanceof EditText)
            {
                element = (EditText) gridView.getChildAt(i);
                cell_value = element.getText().toString();
            }
        }
    }
}
