package FrameActivities;

import javax.swing.*;
import Calculators.CalculationTypes;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Font;

/**
 *
 * @author Elena Ngo
 * This class lets the JFrame display choices for the user to select a dimension
 * size for the 2 matrices. If the 1st matrix has size m x n, then the 2nd matrix
 * has size n x k. If the user selects n columns for the 1st matrix and a different
 * number of row for the 2nd matrix, the program will display an error message and
 * cannot proceed to calculate until the user selects the correct dimensions for
 * both matrices.
 * This display is only for Multiplication calculations for the
 * calculator require 2 matrices, where the 1st matrix's columns is the same
 * as the 2nd matrix's rows.
 */
public class MultDimFrame {
    
    private JPanel _panel;
    private CalculationTypes _calcType;
    private final int[] DIMENSIONS = {1,2,3,4,5};
    
    //components
    private JLabel _instructions;
    private JLabel _rows1;
    private JLabel _columns1;
    private JLabel _rows2;
    private JLabel _columns2;
    private JLabel _sizeError;
    
    private JComboBox<Integer> _row1Dims;
    private JComboBox<Integer> _column1Dims;
    private JComboBox<Integer> _row2Dims;
    private JComboBox<Integer> _column2Dims;
    
    private JButton _submit;
    private JButton _goBack;
    
    private ActionListener _listener;
    
    private MultMatrix1Frame mm1f;
    
    /**
     * Constructor needs the panel so that it can change the display of the frame
     * @param panel a JPanel from the previous Frame
     * @param type needs to be transferred from one class to another until the
     * program reaches the CalculationFrame stage
     */
    public MultDimFrame(JPanel panel, CalculationTypes type)
    {
        _panel = panel;
        _calcType = type;
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
        
        //Add the labels
        _instructions = new JLabel("Please select the dimensions for the two matrices.");
        _instructions.setFont(new Font("Arial", Font.BOLD, 14));
        _sizeError = new JLabel("Matrix 1 Columns must be the same as Matrix 2 Rows");
        _sizeError.setForeground(Color.red);
        _sizeError.setVisible(false); //will be visible when error occurs
        _rows1 = new JLabel("Matrix 1 Rows: ");
        _columns1 = new JLabel("Matrix 1 Columns: ");
        _rows2 = new JLabel("Matrix 2 Rows: ");
        _columns2 = new JLabel("Matrix 2 Columns: ");
        
        _panel.add(_instructions);
        _panel.add(_sizeError);
        _panel.add(_rows1);
        _panel.add(_columns1);
        _panel.add(_rows2);
        _panel.add(_columns2);
        
        size = _instructions.getPreferredSize();
        _instructions.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
        size = _sizeError.getPreferredSize();
        _sizeError.setBounds(insets.left + 5, insets.top + 25, size.width, size.height);
        size = _rows1.getPreferredSize();
        _rows1.setBounds(insets.left + 170, insets.top + 50, size.width, size.height);
        size = _columns1.getPreferredSize();
        _columns1.setBounds(insets.left + 320, insets.top + 50, size.width, size.height);
        size = _rows2.getPreferredSize();
        _rows2.setBounds(insets.left + 170, insets.top + 120, size.width, size.height);
        size = _columns2.getPreferredSize();
        _columns2.setBounds(insets.left + 320, insets.top + 120, size.width, size.height);
        
        //Add ComboBoxes
        int element;
        _row1Dims = new JComboBox<>();
        _column1Dims = new JComboBox<>();
        _row2Dims = new JComboBox<>();
        _column2Dims = new JComboBox<>();
        
        for(int i=0; i< DIMENSIONS.length; i++){
            element = DIMENSIONS[i];
            _row1Dims.addItem(element);
            _column1Dims.addItem(element);
            _row2Dims.addItem(element);
            _column2Dims.addItem(element);
        }
        
        _panel.add(_row1Dims);
        _panel.add(_column1Dims);
        _panel.add(_row2Dims);
        _panel.add(_column2Dims);
        
        size = _row1Dims.getPreferredSize();
        _row1Dims.setBounds(insets.left + 170, insets.top + 80, size.width, size.height);
        size = _column1Dims.getPreferredSize();
        _column1Dims.setBounds(insets.left + 320, insets.top + 80, size.width, size.height);
        size = _row2Dims.getPreferredSize();
        _row2Dims.setBounds(insets.left + 170, insets.top + 150, size.width, size.height);
        size = _column2Dims.getPreferredSize();
        _column2Dims.setBounds(insets.left + 320, insets.top + 150, size.width, size.height);
        
        //Add buttons
        _submit = new JButton("Submit");
        _goBack = new JButton("Go Back");
        _submit.addActionListener(_listener);
        _goBack.addActionListener(_listener);
        
        _panel.add(_submit);
        _panel.add(_goBack);
        
        size = _submit.getPreferredSize();
        _submit.setBounds(insets.left + 180, insets.top + 210, size.width, size.height);
        size = _goBack.getPreferredSize();
        _goBack.setBounds(insets.left + 270, insets.top + 210, size.width, size.height);
    }
    
    public class ClickListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == _submit)
            {
                //Check if matrix 1 columns is the same as matrix 2 rows
                //If yes then proceed
                int rows1 = (Integer) _row1Dims.getSelectedItem();
                int columns1 = (Integer) _column1Dims.getSelectedItem();
                int rows2 = (Integer) _row2Dims.getSelectedItem();
                int columns2 = (Integer) _column2Dims.getSelectedItem();
                
                boolean notSameSize = columns1 != rows2;
                if(notSameSize)
                {
                    _sizeError.setVisible(true);
                }
                else
                {
                    //Change frame/activity
                    _panel.removeAll();
                    mm1f = new MultMatrix1Frame(_panel, _calcType, rows1, columns1, rows2, columns2);
                    _panel.revalidate();
                    _panel.repaint();
                }
            }
            else if(event.getSource() == _goBack)
            {
                _panel.removeAll();
                MainMenuFrame mmf = new MainMenuFrame(_panel);
                _panel.revalidate();
                _panel.repaint();
            }
        }
    }
}
