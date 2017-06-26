package com.myblueshare.matrixcalculator.matrixcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class AddSubtractMatrixInput extends AppCompatActivity {

    private MatrixAdapter adapter;
    private List<Matrix> matrixList;
    private GridView gridView;
    private int rows;
    private int columns;
    private String calcType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subtract_matrix_input);

        // GET THE MATRIX DIMENSIONS FOR BOTH MATRICES
        Bundle extras = getIntent().getExtras();
        rows = extras.getInt("rows");
        columns = extras.getInt("columns");
        calcType = extras.getString("calc_type");

        //MATRIX 1 INPUT

        // INITIALISE THE GRID
        gridView = (GridView)findViewById(R.id.grid);
        gridView.setNumColumns(columns);

        // CREATE A LIST OF MATRIX OBJECTS
        matrixList = new ArrayList<>(); //THIS IS ONLY FOR THE MATRIX OF EDIT_TEXTS
        //Getting the matrix values will come from the user input

        // ADD SOME CONTENTS TO EACH ITEM
        for (int i=0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
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
        //HOW TO GET THE VALUE OF EACH EDITTEXT FIELD?

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
            ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i);

            // GET THE CHILDREN SIZE OF THAT 'i' th CHILD
            int childSize = gridChild.getChildCount();
            // ITERATE THOUGH EACH
            for(int k = 0; k < childSize; k++)
            {
                //HOW TO GET THE VALUE OF EACH EDITTEXT FIELD?
                //THIS CODE BELOW DOESN'T WORK
                View v = gridChild.getChildAt(k);
                if (v instanceof EditText) {
                    element = (EditText) gridChild.getChildAt(k);
                    cell_value = element.getText().toString();
                }
            }
        }
    }
}
