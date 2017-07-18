package com.myblueshare.matrixcalculator.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CalcSelection extends AppCompatActivity {

    private Spinner calculationTypes;
    private CalculationTypes type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_selection);

        //For Dropdown menu
        calculationTypes = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.calculation_types));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calculationTypes.setAdapter(adapter);
    }

    public void selectButton(View view)
    {
        Intent intent;
        String calcType;
        String selectedItem = (String)calculationTypes.getSelectedItem();

        switch (selectedItem)
        {
            case "Addition":
                type = CalculationTypes.ADDITION;
                calcType = type.name();
                intent = new Intent(this, AddSubtractMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Subtraction":
                type = CalculationTypes.SUBTRACTION;
                calcType = type.name();
                intent = new Intent(this, AddSubtractMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Multiplication":
                type = CalculationTypes.MULTIPLICATION;
                calcType = type.name();
                intent = new Intent(this, MultMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Row Echelon":
                type = CalculationTypes.ROW_ECHELON;
                calcType = type.name();
                intent = new Intent(this, OneMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Reduced Row Echelon":
                type = CalculationTypes.REDUCED_ROW_ECHELON;
                calcType = type.name();
                intent = new Intent(this, OneMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Inverse":
                type = CalculationTypes.INVERSE;
                calcType = type.name();
                intent = new Intent(this, SquareMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Transpose":
                type = CalculationTypes.TRANSPOSE;
                calcType = type.name();
                intent = new Intent(this, OneMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Determinant":
                type = CalculationTypes.DETERMINANT;
                calcType = type.name();
                intent = new Intent(this, SquareMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Null Space":
                type = CalculationTypes.NULL_SPACE;
                calcType = type.name();
                intent = new Intent(this, OneMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Column Space":
                type = CalculationTypes.COLUMN_SPACE;
                calcType = type.name();
                intent = new Intent(this, OneMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Row Space":
                type = CalculationTypes.ROW_SPACE;
                calcType = type.name();
                intent = new Intent(this, OneMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
            case "Rank":
                type = CalculationTypes.RANK;
                calcType = type.name();
                intent = new Intent(this, OneMatrixDim.class);
                intent.putExtra("calc_type", calcType);
                startActivity(intent);
                break;
        }
    }
}
