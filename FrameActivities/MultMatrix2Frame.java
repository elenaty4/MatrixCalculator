package FrameActivities;

import Calculators.CalculationTypes;
import java.awt.Color;
import javax.swing.*;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Elena Ngo
 * This class lets the JFrame display a matrix or JTextFields for the user to input
 * their 2nd matrix with.
 * This display is only for Multiplication calculations for the
 * calculator require 2 matrices, where the 1st matrix's columns is the same
 * as the 2nd matrix's rows.
 */
public class MultMatrix2Frame {
    
    private JPanel _panel;
    private CalculationTypes _calcType;
    private double[][] _matrix2;
    private double[][] _matrix1;
    private int _matrix2Rows;
    private int _matrix2Cols;
    
    //components
    private JLabel _instructions;
    private JLabel _emptyError; //It's invisible. Set it to invisible and make it visible when error
    private JLabel _notANumError;
    
    private JTextField[][] _matrixInputFields;
    
    private JButton _calculate;
    private JButton _goBack;
    private JButton _setEmptyToZero;
    
    private ActionListener _listener;
    
    private CalculationFrame cf;
    
    /**
     * Constructor needs the panel so that it can change the display of the frame
     * @param panel a JPanel from the previous Frame
     * @param type needs to be transferred from one class to another until the
     * program reaches the CalculationFrame stage
     * @param row2 the number of rows that the user selected for the 2nd matrix 
     * so that it can be used to display how many rows the 2nd matrix of JTextFields 
     * have. This will not be used for this class but will be used in the next class.
     * @param column2 the number of columns that the user selected for the 2nd matrix 
     * so that it can be used to display how many columns the 2nd matrix of JTextFields 
     * have. This will not be used for this class but will be used in the next class.
     * @param matrix1 the 1st matrix that the user created. It must be transferred 
     * to different classes until CalculationFrame occurs. 1st matrix is needed to
     * be calculated with the 2nd matrix
     */
    public MultMatrix2Frame(JPanel panel, CalculationTypes type, 
                            int row2, int column2, double[][] matrix1)
    {
        _panel = panel;
        _calcType = type;
        _matrix2Rows = row2;
        _matrix2Cols = column2;
        _matrix1 = matrix1;
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
        
        //Add labels
        _instructions = new JLabel("Please enter numbers in all of the 2nd matrix's cells");
        _emptyError = new JLabel("One of the field is empty. Please fill in all of the fields");
        _notANumError = new JLabel("One of the fields is not a number. Please fill in all of the "
                + "fields with numbers");
        _emptyError.setForeground(Color.red);
        _notANumError.setForeground(Color.red);
        _emptyError.setVisible(false); //will be visible if empty cell error occurs
        _notANumError.setVisible(false); //will be visible if non-number error occurs
        
        _panel.add(_instructions);
        _panel.add(_emptyError);
        _panel.add(_notANumError);
        
        size = _instructions.getPreferredSize();
        _instructions.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
        size = _emptyError.getPreferredSize();
        _emptyError.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
        size = _notANumError.getPreferredSize();
        _notANumError.setBounds(insets.left + 5, insets.top + 30, size.width, size.height);
        
        //ADD THE MATRIX OF TEXTFIELDS
        //Create an inner panel that contains a gridlayout of JTextFields.
        GridLayout layoutForMatrix = new GridLayout(_matrix2Rows, _matrix2Cols, 10, 10);
        JPanel innerPanel = new JPanel(layoutForMatrix);
        final int FIELD_SIZE = 3;
        _matrixInputFields = new JTextField[_matrix2Rows][_matrix2Cols];

        //use a for loop to position the TextFields
        JTextField currentTF;
        for (int r = 0; r < _matrixInputFields.length; r++)
        {
            for (int c = 0; c < _matrixInputFields[r].length; c++)
            {
                //I need to know how the components are placed so go to practice code
                _matrixInputFields[r][c] = new JTextField(FIELD_SIZE);
                currentTF = _matrixInputFields[r][c];
                innerPanel.add(currentTF); 
            }
        }
        _panel.add(innerPanel);
        
        size = innerPanel.getPreferredSize();
        innerPanel.setBounds(insets.left + 20, insets.top + 70, size.width, size.height);
        
        //Add the buttons
        _calculate = new JButton("Calculate");
        _setEmptyToZero = new JButton("Set Empty Fields to Zero");
        _goBack = new JButton("Go Back");
        _calculate.addActionListener(_listener);
        _setEmptyToZero.addActionListener(_listener);
        _goBack.addActionListener(_listener);
        
        _panel.add(_calculate);
        _panel.add(_setEmptyToZero);
        _panel.add(_goBack);
        
        size = _calculate.getPreferredSize();
        _calculate.setBounds(insets.left + 50, insets.top + 400, size.width, size.height);
        size = _setEmptyToZero.getPreferredSize();
        _setEmptyToZero.setBounds(insets.left + 145, insets.top + 400, size.width, size.height);
        size = _goBack.getPreferredSize();
        _goBack.setBounds(insets.left + 325, insets.top + 400, size.width, size.height);
    }
    
    public class ClickListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == _calculate)
            {
                //Check if any fields are empty
                //Check if any fields are numbers
                boolean fieldIsEmpty = false;
                boolean notANumber = false; //remove false later
                String fieldValue;
                //Check if ALL of the fields are not empty
                for (int r = 0; r < _matrixInputFields.length; r++)
                {
                    for (int c = 0; c < _matrixInputFields[r].length; c++)
                    {
                       fieldValue = _matrixInputFields[r][c].getText();
                       fieldIsEmpty = fieldValue.equals("");
                       try
                       {
                           Double.parseDouble(fieldValue);
                       }
                       catch(NumberFormatException e)
                       {
                           notANumber = true;
                       }
                       
                       if (fieldIsEmpty)
                       {
                           //make error visible
                           //but first check if the other error is visible
                           if(_notANumError.isVisible())
                           {
                               _notANumError.setVisible(false);
                               _emptyError.setVisible(true);
                           }
                           else
                           {
                               _emptyError.setVisible(true);
                           }
                       }
                       else if(notANumber)
                       {
                          if(_emptyError.isVisible())
                           {
                               _emptyError.setVisible(false);
                               _notANumError.setVisible(true);
                           }
                           else
                           {
                               _notANumError.setVisible(true);
                           } 
                       }
                    }
                }
                
                //check if one of the booleans are NOT true before proceeding
                if(!fieldIsEmpty && !notANumber)
                {
                    //add the fieldValues to double[][]
                    double numValue;
                    _matrix2 = new double[_matrix2Rows][_matrix2Cols];
                    for(int r=0; r<_matrix2Rows; r++)
                    {
                        for(int c=0; c<_matrix2Cols; c++)
                        {
                            fieldValue = _matrixInputFields[r][c].getText();
                            numValue = Double.parseDouble(fieldValue);
                            _matrix2[r][c] = numValue;
                        }
                    }
                    
                    //change frame/activity
                    _panel.removeAll();
                    cf = new CalculationFrame(_panel, _calcType, _matrix1, _matrix2);
                    _panel.revalidate();
                    _panel.repaint();
                }
            }
            else if(event.getSource() == _setEmptyToZero)
            {
                //Check if there are empty fields
                boolean fieldIsEmpty;
                for (int r = 0; r < _matrixInputFields.length; r++)
                {
                    for (int c = 0; c < _matrixInputFields[r].length; c++)
                    {
                       fieldIsEmpty = _matrixInputFields[r][c].getText().equals("");
                       if (fieldIsEmpty)
                       {
                           //add zero on it
                           _matrixInputFields[r][c].setText("0");
                       }
                    }
                }
            }
            else if (event.getSource() == _goBack)
            {
                _panel.removeAll();
                int matrix1Rows = _matrix1.length;
                int matrix1Cols = _matrix1[0].length;
                MultMatrix1Frame mm1f = new MultMatrix1Frame(_panel, 
                        _calcType, matrix1Rows, matrix1Cols, _matrix2Rows, _matrix2Cols);
                _panel.revalidate();
                _panel.repaint();
            }
        }
    }
}
