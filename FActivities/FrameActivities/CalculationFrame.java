package FrameActivities;

import Calculators.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Elena Ngo
 * This class lets the JFrame display the result of the calculation. It will use
 * one of the Calculator classes to calculate depending on what CalculationType
 * the user selected and then display the result in the JPanel
 */
public class CalculationFrame {

    private JPanel _panel;
    private double[][] _matrix1;
    private double[][] _matrix2;
    private double[][] _newMatrix;
    private double _newValue;
    private CalculationTypes _calcType;
    
    //components
    private JLabel _title;
    private JLabel _hereIsResult;
    private JLabel[][] _resultDisplay;
    private JLabel _errorMessage;
    
    private GridLayout _grid;
    
    private JButton _calculateAgain;
    private JButton _backToMenu; //Goes back to the main menu
    
    private ActionListener _listener;
    private MatrixCalculator _mc;
    
    //With one matrix (the rest)
    /**
     * This Constructor is for any Calculator that requires the usage of one matrix.
     * Constructor needs the panel so that it can change the display of the frame
     * @param panel a JPanel from the previous Frame
     * @param type needs to be transferred from one class to another until the
     * program reaches the CalculationFrame stage
     * @param matrix1 the one matrix that the user created. It is needed to be 
     * calculated in order for the class to produce a result
     */
    public CalculationFrame(JPanel panel, CalculationTypes type, double[][] matrix1)
    {
        _panel = panel;
        _calcType = type;
        _matrix1 = matrix1;
        addComponents();
    }
    
    //With two matrices (Addition, Subtraction, Multiplication)
    /**
     * This Constructor is for any Calculator that requires the usage of two matrices.
     * Constructor needs the panel so that it can change the display of the frame
     * @param panel
     * @param type
     * @param matrix1
     * @param matrix2 
     */
    public CalculationFrame(JPanel panel, CalculationTypes type,
                            double[][] matrix1, double[][] matrix2)
    {
        _panel = panel;
        _calcType = type;
        _matrix1 = matrix1;
        _matrix2 = matrix2;
        addComponents();
    }
    
    /**
     * Adds the components into the JPanel for the frame
     */
    private void addComponents()
    {
        Dimension size;
        Insets insets = _panel.getInsets();
        _listener = new ClickListener();
        //Check which calculation type is it
        if(_calcType.equals(CalculationTypes.ADDITION))
        {
            //Uses the Additon class
            int rows = _matrix1.length; //both matrices are same size so it doesn't matter
            int columns = _matrix1[0].length;
            _newMatrix = new double[rows][columns];
            
            //Add the labels and label[][]
            _title = new JLabel("Addition");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _panel.add(_title);
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            
            _hereIsResult = new JLabel("Here is the result matrix");
            _panel.add(_hereIsResult);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use Addition class
            _mc = new Addition(_matrix1, _matrix2);
            _mc.calculate();
            _newMatrix = _mc.getNewMatrix();
            
            //now display the JLabel[][]
            _resultDisplay = new JLabel[rows][columns];
            
            //make a GridLayout
            _grid = new GridLayout(rows, columns, 0, 0);
            
            displayMatrixResult();
            displayButtons();
            
        }
        else if(_calcType.equals(CalculationTypes.SUBTRACTION))
        {
            int rows = _matrix1.length; //both matrices are same size so it doesn't matter
            int columns = _matrix1[0].length;
            _newMatrix = new double[rows][columns];
            
            //Add the labels and label[][]
            _title = new JLabel("Subtraction");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _panel.add(_title);
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            
            _hereIsResult = new JLabel("Here is the result matrix");
            _panel.add(_hereIsResult);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Subtraction class
            _mc = new Subtraction(_matrix1, _matrix2);
            _mc.calculate();
            _newMatrix = _mc.getNewMatrix();
            
            //now display the JLabel[][]
            _resultDisplay = new JLabel[rows][columns];
            //make innerPanel a GridLayout
            _grid = new GridLayout(rows, columns, 0, 0);
            
            displayMatrixResult();
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.MULTIPLICATION))
        {
            int rows1 = _matrix1.length;
            int columns1 = _matrix1[0].length;
            int rows2 = _matrix2.length;
            int columns2 = _matrix2[0].length;
            _newMatrix = new double[rows1][columns2];
            
            //Add the labels and label[][]
            _title = new JLabel("Multiplication");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _panel.add(_title);
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            
            _hereIsResult = new JLabel("Here is the result matrix");
            _panel.add(_hereIsResult);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Muliplication class
            _mc = new Multiplication(_matrix1, _matrix2);
            _mc.calculate();
            _newMatrix = _mc.getNewMatrix();
            
            //now display the JLabel[][]
            _resultDisplay = new JLabel[rows1][columns2];
            //make innerPanel a GridLayout
            _grid = new GridLayout(rows1, columns2, 0, 0);
            
            displayMatrixResult();
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.ROW_ECHELON))
        {
            int rows = _matrix1.length;
            int columns = _matrix1[0].length;
            _newMatrix = new double[rows][columns];
            
            //Add the labels and label[][]
            _title = new JLabel("Row Echelon");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _panel.add(_title);
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            
            _hereIsResult = new JLabel("Here is the result matrix");
            _panel.add(_hereIsResult);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Row Echelon class
            _mc = new RowEchelon(_matrix1);
            _mc.calculate();
            _newMatrix = _mc.getNewMatrix();
            
            //now display the JLabel[][]
            _resultDisplay = new JLabel[rows][columns];
            //make innerPanel a GridLayout
            _grid = new GridLayout(rows, columns, 0, 0);
            
            displayMatrixResult();
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.REDUCED_ROW_ECHELON))
        {
            int rows = _matrix1.length;
            int columns = _matrix1[0].length;
            _newMatrix = new double[rows][columns];
            
            //Add the labels and label[][]
            _title = new JLabel("Reduced Row Echelon");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _panel.add(_title);
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            
            _hereIsResult = new JLabel("Here is the result matrix");
            _panel.add(_hereIsResult);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Reduced Row Echelon class
            _mc = new ReducedRowEchelon(_matrix1);
            _mc.calculate();
            _newMatrix = _mc.getNewMatrix();
            
            //now display the JLabel[][]
            _resultDisplay = new JLabel[rows][columns];
            //make innerPanel a GridLayout
            _grid = new GridLayout(rows, columns, 0, 0);
            
            displayMatrixResult();
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.INVERSE))
        {
            int dimension = _matrix1.length;
            _newMatrix = new double[dimension][dimension];
            
            //Add the labels and label[][]
            _title = new JLabel("Inverse");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _hereIsResult = new JLabel("Here is the inverted matrix");
            _errorMessage = new JLabel("The matrix you given is NOT invertible");
            _errorMessage.setForeground(Color.red);
            _errorMessage.setVisible(false);
            
            _panel.add(_hereIsResult);
            _panel.add(_title);
            _panel.add(_errorMessage);
            
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            size = _errorMessage.getPreferredSize();
            _errorMessage.setBounds(insets.left + 5, insets.top + 45, size.width, size.height);
            
            //Use the Inverse Class
            Inverse mc = new Inverse(_matrix1);
            mc.calculate();
            //check if it's invertible
            boolean isInvertible = mc.isInvertible();
            if (isInvertible)
            {
                _newMatrix = mc.getNewMatrix();
            
                //now display the JLabel[][]
                _resultDisplay = new JLabel[dimension][dimension];
                //make innerPanel a GridLayout
                _grid = new GridLayout(dimension, dimension, 0, 0);
            
                displayMatrixResult();
                displayButtons();
            }
            else
            {
                //display error message
                _errorMessage.setVisible(true);
                displayButtons();
            }
        }
        else if(_calcType.equals(CalculationTypes.TRANSPOSE))
        {
            int rows = _matrix1.length;
            int columns = _matrix1[0].length;
            _newMatrix = new double[columns][rows];
            
            //Add the labels and label[][]
            _title = new JLabel("Transpose");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _panel.add(_title);
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            
            _hereIsResult = new JLabel("Here is the transposed matrix");
            _panel.add(_hereIsResult);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Transpose Class
            _mc = new Transpose(_matrix1);
            _mc.calculate();
            _newMatrix = _mc.getNewMatrix();
            
            //now display the JLabel[][]
            _resultDisplay = new JLabel[columns][rows];
            //make innerPanel a GridLayout
            _grid = new GridLayout(columns, rows, 0, 0);
            
            displayMatrixResult();
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.DETERMINANT))
        {
            int dimension = _matrix1.length;
            
            //Add the labels and label[][]
            _title = new JLabel("Determinant");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _hereIsResult = new JLabel("Here is the Determinant value");
            
            _panel.add(_hereIsResult);
            _panel.add(_title);
            
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Determinant class
            _mc = new Determinant(_matrix1);
            _mc.calculate();
            _newValue = _mc.getValue();
            
            //Present the value as a JLabel
            JLabel determinant = new JLabel(Double.toString(_newValue));
            determinant.setFont(new Font("Arial", Font.BOLD, 15));
            _panel.add(determinant);
            
            size = determinant.getPreferredSize();
            determinant.setBounds(insets.left + 5, insets.top + 60, size.width, size.height);
            
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.NULL_SPACE))
        {
            int rows = _matrix1.length;
            int columns = _matrix1[0].length;
            
            //Add the labels and label[][]
            _title = new JLabel("Null Space");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _hereIsResult = new JLabel("Here is the Null Basis: ");
            
            _panel.add(_hereIsResult);
            _panel.add(_title);
            
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Null Space class
            NullSpace mc = new NullSpace(_matrix1);
            mc.calculate();
            //Check if the set is empty
            boolean isEmptySet = mc.isEmptySet();
            if(isEmptySet)
            {
                //Display a Label about it
                JLabel emptySet = new JLabel("There is nothing in the Null Space therefore, "
                        + "the set is empty");
                emptySet.setForeground(Color.red);
                JLabel startBase = new JLabel("{");
                JLabel endBase = new JLabel("}");
                startBase.setFont(new Font("Arial", Font.BOLD, 40));
                endBase.setFont(new Font("Arial", Font.BOLD, 40));
                _panel.add(emptySet);
                _panel.add(startBase);
                _panel.add(endBase);
                size = emptySet.getPreferredSize();
                emptySet.setBounds(insets.left + 5, insets.top + 50, size.width, size.height);
                size = startBase.getPreferredSize();
                startBase.setBounds(insets.left + 10, insets.top + 150, size.width, size.height);
                size = endBase.getPreferredSize();
                endBase.setBounds(insets.left + 50, insets.top + 150, size.width, size.height);
            }
            else
            {
                _newMatrix = mc.getNewMatrix();
                displayBasis();
            }
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.COLUMN_SPACE))
        {
            int rows = _matrix1.length;
            int columns = _matrix1[0].length;
            
            //Add the labels and label[][]
            _title = new JLabel("Column Space");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _hereIsResult = new JLabel("Here is the Column Basis: ");
            
            _panel.add(_hereIsResult);
            _panel.add(_title);
            
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the column space class
            ColumnSpace mc = new ColumnSpace(_matrix1);
            mc.calculate();
            //check if the set is empty first
            boolean isEmptySet = mc.isEmptySet();
            if(isEmptySet)
            {
                //Display a Label about it
                JLabel emptySet = new JLabel("There is nothing in the Column Space therefore, "
                        + "the set is empty");
                emptySet.setForeground(Color.red);
                JLabel startBase = new JLabel("{");
                JLabel endBase = new JLabel("}");
                startBase.setFont(new Font("Arial", Font.BOLD, 40));
                endBase.setFont(new Font("Arial", Font.BOLD, 40));
                _panel.add(emptySet);
                _panel.add(startBase);
                _panel.add(endBase);
                size = emptySet.getPreferredSize();
                emptySet.setBounds(insets.left + 5, insets.top + 50, size.width, size.height);
                size = startBase.getPreferredSize();
                startBase.setBounds(insets.left + 10, insets.top + 150, size.width, size.height);
                size = endBase.getPreferredSize();
                endBase.setBounds(insets.left + 50, insets.top + 150, size.width, size.height);
            }
            else
            {
                _newMatrix = mc.getNewMatrix();
                displayBasis();
            }
            
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.ROW_SPACE))
        {
            int rows = _matrix1.length;
            int columns = _matrix1[0].length;
            
            //Add the labels and label[][]
            _title = new JLabel("Row Space");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _hereIsResult = new JLabel("Here is the Row Basis: ");
            
            _panel.add(_hereIsResult);
            _panel.add(_title);
            
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Row Space class
            RowSpace mc = new RowSpace(_matrix1);
            mc.calculate();
            boolean isEmptySet = mc.isEmptySet();
            
            if(isEmptySet)
            {
                //Display a Label about it
                JLabel emptySet = new JLabel("There is nothing in the Row Space therefore, "
                        + "the set is empty");
                emptySet.setForeground(Color.red);
                JLabel startBase = new JLabel("{");
                JLabel endBase = new JLabel("}");
                startBase.setFont(new Font("Arial", Font.BOLD, 40));
                endBase.setFont(new Font("Arial", Font.BOLD, 40));
                _panel.add(emptySet);
                _panel.add(startBase);
                _panel.add(endBase);
                size = emptySet.getPreferredSize();
                emptySet.setBounds(insets.left + 5, insets.top + 50, size.width, size.height);
                size = startBase.getPreferredSize();
                startBase.setBounds(insets.left + 10, insets.top + 150, size.width, size.height);
                size = endBase.getPreferredSize();
                endBase.setBounds(insets.left + 50, insets.top + 150, size.width, size.height);
            }
            else
            {
                _newMatrix = mc.getNewMatrix();
            
                //How to display the Row basis?
                //Create big { } labels first
                JLabel startBase = new JLabel("{");
                JLabel endBase = new JLabel("}");
                startBase.setFont(new Font("Arial", Font.BOLD, 40));
                endBase.setFont(new Font("Arial", Font.BOLD, 40));
                _panel.add(startBase);
                _panel.add(endBase);
                size = startBase.getPreferredSize();
                startBase.setBounds(insets.left + 10, insets.top + 150, size.width, size.height);
                size = endBase.getPreferredSize();
                endBase.setBounds(insets.left + 500, insets.top + 150, size.width, size.height);
            
                //Create a big gridLayout first
                _grid = new GridLayout(3, _newMatrix.length, 20, 20);
                JPanel bigInnerPanel = new JPanel(_grid);
            
                //Create a list of inner panels that has a gridlayout
                //_grid will be used for all inner JPanels
                _grid = new GridLayout(1, _newMatrix[0].length, 10, 10); //row vectors only have ONE row
                _resultDisplay = new JLabel[1][_newMatrix[0].length];
                JPanel[] innerPanels = new JPanel[_newMatrix.length];
            
                //iterate through JPanel[]
                for(int i = 0; i < innerPanels.length; i++)
                {
                    innerPanels[i] = new JPanel(_grid); //instantiate them
                    innerPanels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    for(int c = 0; c < _newMatrix[0].length; c++)
                    {
                        String element = Double.toString(_newMatrix[i][c]);
                        _resultDisplay[0][c] = new JLabel(element);
                        _resultDisplay[0][c].setFont(new Font("Arial", Font.PLAIN, 20));
                        innerPanels[i].add(_resultDisplay[0][c]);
                    }
                    bigInnerPanel.add(innerPanels[i]);
                }
                _panel.add(bigInnerPanel);
                size = bigInnerPanel.getPreferredSize();
                bigInnerPanel.setBounds(insets.left + 30, insets.top + 130, size.width, size.height); 
            }
            displayButtons();
        }
        else if(_calcType.equals(CalculationTypes.RANK))
        {
            int rows = _matrix1.length;
            int columns = _matrix1[0].length;
            
            //Add the labels and label[][]
            _title = new JLabel("Rank");
            _title.setFont(new Font("Arial", Font.BOLD, 20));
            _hereIsResult = new JLabel("Here is the Rank value: ");
            
            _panel.add(_hereIsResult);
            _panel.add(_title);
            
            size = _title.getPreferredSize();
            _title.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
            size = _hereIsResult.getPreferredSize();
            _hereIsResult.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
            
            //Use the Rank class
            _mc = new Rank(_matrix1);
            _mc.calculate();
            _newValue = _mc.getValue();
            
            //Present the value as a JLabel
            JLabel rank = new JLabel(Double.toString(_newValue));
            rank.setFont(new Font("Arial", Font.BOLD, 15));
            _panel.add(rank);
            size = rank.getPreferredSize();
            rank.setBounds(insets.left + 5, insets.top + 60, size.width, size.height);
            
            displayButtons();
        }
    }
    
    /**
     * This is made to make the code not be repetitive and long
     * It makes the panel display the "Calculate Again" and "Back to Menu" buttons
     */
    private void displayButtons()
    {
        Dimension size;
        Insets insets = _panel.getInsets();
        //now display the buttons
        _calculateAgain = new JButton("Calculate Again");
        _backToMenu = new JButton("Back to Menu");
        _calculateAgain.addActionListener(_listener);
        _backToMenu.addActionListener(_listener);
            
        _panel.add(_calculateAgain);
        _panel.add(_backToMenu);
            
        size = _calculateAgain.getPreferredSize();
        _calculateAgain.setBounds(insets.left + 20, insets.top + 400, size.width, size.height);
        size = _backToMenu.getPreferredSize();
        _backToMenu.setBounds(insets.left + 150, insets.top + 400, size.width, size.height);
    }
    
    /**
     * This is made to make the code not be repetitive and long
     * Many of the calculators will produce a matrix as a result. This method 
     * makes the panel display the values of the result matrix
     */
    private void displayMatrixResult()
    {
        Dimension size;
        Insets insets = _panel.getInsets();
        
        JPanel innerPanel = new JPanel(_grid);
            
        //iterate and put the newMatrix values into the JLabel matrix
        for (int r = 0; r < _newMatrix.length; r++)
        {
            for (int c = 0; c < _newMatrix[r].length; c++)
            {
                String element = Double.toString(_newMatrix[r][c]);
                _resultDisplay[r][c] = new JLabel(element);
                _resultDisplay[r][c].setFont(new Font("Arial", Font.PLAIN, 15));
                _resultDisplay[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                innerPanel.add(_resultDisplay[r][c]);
            }
        }
            
        _panel.add(innerPanel);
        size = innerPanel.getPreferredSize();
        innerPanel.setBounds(insets.left + 20, insets.top + 65, size.width, size.height); 
    }
    
    /**
     * This is made to make the code not be repetitive and long.
     * This is specifically for calculators that display a basis of vectors as a
     * result (Null Space & Column Space). This method makes the panel display 
     * the vectors inside the basis.
     */
    private void displayBasis()
    {
        Dimension size;
        Insets insets = _panel.getInsets();
        
        //How to display the basis?
        //Create big { } labels first
        JLabel startBase = new JLabel("{");
        JLabel endBase = new JLabel("}");
        startBase.setFont(new Font("Arial", Font.BOLD, 40));
        endBase.setFont(new Font("Arial", Font.BOLD, 40));
        _panel.add(startBase);
        _panel.add(endBase);
        size = startBase.getPreferredSize();
        startBase.setBounds(insets.left + 10, insets.top + 150, size.width, size.height);
        size = endBase.getPreferredSize();
        endBase.setBounds(insets.left + 350, insets.top + 150, size.width, size.height);
            
        //Create a big gridLayout first
        _grid = new GridLayout(1, _newMatrix.length, 20, 20);
        JPanel bigInnerPanel = new JPanel(_grid);
            
        //Create a list of inner panels that has a gridlayout
        //_grid will be used for all inner JPanels
        _grid = new GridLayout(_newMatrix[0].length, 1, 10, 10); //vectors only have ONE column
        _resultDisplay = new JLabel[_newMatrix[0].length][1];
        JPanel[] innerPanels = new JPanel[_newMatrix.length];
            
        //iterate through JPanel[]
        for(int i = 0; i < innerPanels.length; i++)
        {
            innerPanels[i] = new JPanel(_grid); //instantiate them
            innerPanels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            for(int c = 0; c < _newMatrix[0].length; c++)
            {
                String element = Double.toString(_newMatrix[i][c]);
                _resultDisplay[c][0] = new JLabel(element);
                _resultDisplay[c][0].setFont(new Font("Arial", Font.PLAIN, 20));
                innerPanels[i].add(_resultDisplay[c][0]);
            }
            bigInnerPanel.add(innerPanels[i]);
        }
        _panel.add(bigInnerPanel);
        size = bigInnerPanel.getPreferredSize();
        bigInnerPanel.setBounds(insets.left + 30, insets.top + 130, size.width, size.height); 
    }
    
    public class ClickListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == _calculateAgain)
            {
                _panel.removeAll();
                //depends on the calculation type
                boolean isAddSubtract = _calcType.equals(CalculationTypes.ADDITION) ||
                        _calcType.equals(CalculationTypes.SUBTRACTION);
                boolean isMult = _calcType.equals(CalculationTypes.MULTIPLICATION);
                boolean isOneMatrix = _calcType.equals(CalculationTypes.ROW_ECHELON) ||
                        _calcType.equals(CalculationTypes.REDUCED_ROW_ECHELON) || 
                        _calcType.equals(CalculationTypes.TRANSPOSE) || 
                        _calcType.equals(CalculationTypes.NULL_SPACE) || 
                        _calcType.equals(CalculationTypes.COLUMN_SPACE) || 
                        _calcType.equals(CalculationTypes.ROW_SPACE) || 
                        _calcType.equals(CalculationTypes.RANK);
                boolean isSquareMatrix = _calcType.equals(CalculationTypes.INVERSE) ||
                        _calcType.equals(CalculationTypes.DETERMINANT);
                
                //Make an if statement
                if(isAddSubtract)
                {
                    AddSubtractDimFrame asdf = new AddSubtractDimFrame(_panel, _calcType);
                    _panel.revalidate();
                    _panel.repaint();
                }
                else if(isMult)
                {
                    MultDimFrame mdf = new MultDimFrame(_panel, _calcType);
                    _panel.revalidate();
                    _panel.repaint();
                }
                else if(isOneMatrix)
                {
                    OneMatrixDim omd = new OneMatrixDim(_panel, _calcType);
                    _panel.revalidate();
                    _panel.repaint();
                }
                else if(isSquareMatrix)
                {
                    SquareMatrixDim smd = new SquareMatrixDim(_panel, _calcType);
                    _panel.revalidate();
                    _panel.repaint();
                }
            }
            else if(event.getSource() == _backToMenu)
            {
                _panel.removeAll();
                MainMenuFrame mmf = new MainMenuFrame(_panel);
                _panel.revalidate();
                _panel.repaint();
            }
        }
    }
}
