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
 * size for the matrix.
 * This display is only for calculators that require one matrix such as Row Echelon,
 * Reduced Row Echelon, Transpose, Null Space, Column Space, Row Space, and Rank
 * Calculators that require one nxn matrix are not included for this.
 */
public class OneMatrixDim {
    
    private JPanel _panel;
    private CalculationTypes _calcType;
    private final int[] DIMENSIONS = {1,2,3,4,5};
    
    //components
    private JLabel _instructions;
    private JLabel _rowLabel;
    private JLabel _columnLabel;
    
    private JComboBox<Integer> _rowDims;
    private JComboBox<Integer> _columnDims;
    
    private JButton _submit;
    private JButton _goBack;
    
    private ActionListener _listener;
    
    private OneMatrixInputFrame omif;
    
    /**
     * Constructor needs the panel so that it can change the display of the frame
     * @param panel a JPanel from the previous Frame
     * @param type needs to be transferred from one class to another until the
     * program reaches the CalculationFrame stage
     */
    public OneMatrixDim(JPanel panel, CalculationTypes type)
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
        _instructions = new JLabel("Please select the dimensions for the matrix");
        _instructions.setFont(new Font("Arial", Font.BOLD, 14));
        _rowLabel = new JLabel("Rows: ");
        _columnLabel = new JLabel("Columns: ");
        
        _panel.add(_instructions);
        _panel.add(_rowLabel);
        _panel.add(_columnLabel);
        
        size = _instructions.getPreferredSize();
        _instructions.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
        size = _rowLabel.getPreferredSize();
        _rowLabel.setBounds(insets.left + 200, insets.top + 30, size.width, size.height);
        size = _columnLabel.getPreferredSize();
        _columnLabel.setBounds(insets.left + 290, insets.top + 30, size.width, size.height);
        
        //Add JComboBoxes
        int element;
        _rowDims = new JComboBox<>();
        _columnDims = new JComboBox<>();
        for(int i=0; i< DIMENSIONS.length; i++){
            element = DIMENSIONS[i];
            _rowDims.addItem(element);
            _columnDims.addItem(element);
        }
        
        _panel.add(_rowDims);
        _panel.add(_columnDims);
        
        size = _rowDims.getPreferredSize();
        _rowDims.setBounds(insets.left + 200, insets.top + 50, size.width, size.height);
        size = _columnDims.getPreferredSize();
        _columnDims.setBounds(insets.left + 290, insets.top + 50, size.width, size.height);
        
        //Add buttons
        _submit = new JButton("Submit");
        _goBack = new JButton("Go Back");
        _submit.addActionListener(_listener);
        _goBack.addActionListener(_listener);
        
        _panel.add(_submit);
        _panel.add(_goBack);
        
        size = _submit.getPreferredSize();
        _submit.setBounds(insets.left + 180, insets.top + 90, size.width, size.height);
        size = _goBack.getPreferredSize();
        _goBack.setBounds(insets.left + 270, insets.top + 90, size.width, size.height);
        
    }
    
    public class ClickListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == _submit)
            {
                _panel.removeAll();
                int rows = (Integer) _rowDims.getSelectedItem();
                int columns = (Integer) _columnDims.getSelectedItem();
                
                omif = new OneMatrixInputFrame(_panel, _calcType, rows, columns);
                _panel.revalidate();
                _panel.repaint();
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
