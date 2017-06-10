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
 * their 1st matrix with.
 * This display is only for Addition and Subtraction calculations for these
 * calculators both require 2 matrices that are the same size.
 */
public class AddSubtractMatrix1Frame {
    
    private JPanel _panel;
    private CalculationTypes _calcType;
    private int _matrixRows;
    private int _matrixColumns;
    private double[][] _matrix1;
    
    //components
    private JLabel _instructions;
    private JLabel _emptyError; 
    private JLabel _notANumError;
    
    private JTextField[][] _matrixInputFields;
    
    private JButton _nextMatrix;
    private JButton _goBack;
    private JButton _setEmptyToZero;
    
    private ActionListener _listener;
    
    private AddSubtractMatrix2Frame asm2f;
    
    /**
     * Constructor needs the panel so that it can change the display of the frame
     * @param panel a JPanel from the previous Frame
     * @param type needs to be transferred from one class to another until the
     * program reaches the CalculationFrame stage
     * @param rows the number of rows that the user selected so that it can be 
     * used to display how many rows the matrix of JTextFields have.
     * @param columns the number of columns that the user selected so that it can be 
     * used to display how many columns the matrix of JTextFields have.
     */
    public AddSubtractMatrix1Frame(JPanel panel, 
                                   CalculationTypes type, 
                                   int rows, int columns)
    {
        _panel = panel;
        _calcType = type;
        _matrixRows = rows;
        _matrixColumns = columns;
        addComponents();
    }
    
    /**
     * Adds the components into the JPanel for the frame
     */
    private void addComponents()
    {
        Insets insets = _panel.getInsets();
        Dimension size;
        _listener = new ClickListener();
        
        //Add Labels
        _instructions = new JLabel("Please enter numbers in all of the 1st matrix's cells");
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
        
        //Create an inner panel that contains a gridlayout(matrix) of JTextFields.
        GridLayout layoutForMatrix = new GridLayout(_matrixRows, _matrixColumns, 10, 10);
        JPanel innerPanel = new JPanel(layoutForMatrix);
        final int FIELD_SIZE = 3;
        _matrixInputFields = new JTextField[_matrixRows][_matrixColumns];

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
        _nextMatrix = new JButton("Next Matrix");
        _setEmptyToZero = new JButton("Set Empty Fields to Zero");
        _goBack = new JButton("Go Back");
        _nextMatrix.addActionListener(_listener);
        _setEmptyToZero.addActionListener(_listener);
        _goBack.addActionListener(_listener);
        
        _panel.add(_nextMatrix);
        _panel.add(_setEmptyToZero);
        _panel.add(_goBack);
        
        size = _nextMatrix.getPreferredSize();
        _nextMatrix.setBounds(insets.left + 50, insets.top + 400, size.width, size.height);
        size = _setEmptyToZero.getPreferredSize();
        _setEmptyToZero.setBounds(insets.left + 160, insets.top + 400, size.width, size.height);
        size = _goBack.getPreferredSize();
        _goBack.setBounds(insets.left + 340, insets.top + 400, size.width, size.height);
        
    }
    
    public class ClickListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == _nextMatrix)
            {
                boolean fieldIsEmpty = false;
                boolean notANumber = false;
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
                           //make error visible
                           //but first check if the other error is visible
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
                    _matrix1 = new double[_matrixRows][_matrixColumns];
                    for(int r=0; r<_matrixRows; r++)
                    {
                        for(int c=0; c<_matrixColumns; c++)
                        {
                            fieldValue = _matrixInputFields[r][c].getText();
                            numValue = Double.parseDouble(fieldValue);
                            _matrix1[r][c] = numValue;
                        }
                    }
                    
                    //change frame/activity
                    _panel.removeAll();
                    asm2f = new AddSubtractMatrix2Frame(_panel, _calcType, 
                            _matrixRows, _matrixColumns, _matrix1);
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
            else if(event.getSource() == _goBack)
            {
                _panel.removeAll();
                AddSubtractDimFrame asdf = new AddSubtractDimFrame(_panel, _calcType);
                _panel.revalidate();
                _panel.repaint();
            }
        }
        
    }
    
}
