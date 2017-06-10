package FrameActivities;

import Calculators.CalculationTypes;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

/**
 * @author Elena Ngo
 * This class lets the JFrame display the list of calculators that the user can 
 * choose from.
 */
public class MainMenuFrame {
    
    private final String[] LIST_OF_CALCULATORS = 
    {"Addition", "Subtraction", "Multiplication", "Row Echelon", "Reduced Row Echelon", 
    "Inverse", "Transpose", "Determinant", "Null Space", "Column Space", "Row Space", 
    "Rank"};
    
    //The components 
    private JLabel _instructions;
    private JPanel _panel;
    private JComboBox<String> _calculators;
    private JButton _submit;
    
    private CalculationTypes _calcType;
    private ActionListener _listener;
    
    private AddSubtractDimFrame asdf;
    private MultDimFrame mdf;
    private OneMatrixDim omd;
    private SquareMatrixDim smd;
    
    /**
     * Constructor needs the panel so that it can change the display of the frame
     * @param panel a JPanel from the previous Frame
     */
    public MainMenuFrame(JPanel panel)
    {
        _panel = panel;
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
        
        //add instructions
        _instructions = new JLabel("Please select which type of calculation you "
                + "want to use");
        _instructions.setFont(new Font("Arial", Font.BOLD, 20));
        _panel.add(_instructions);
        size = _instructions.getPreferredSize();
        _instructions.setBounds(insets.left + 5, insets.top + 5, size.width, size.height);
        
        //Add combobox
        _calculators = new JComboBox<>();
        String element;
        for(int i = 0; i < LIST_OF_CALCULATORS.length; i++)
        {
            element = LIST_OF_CALCULATORS[i];
            _calculators.addItem(element);
        }
        _panel.add(_calculators);
        
        size = _calculators.getPreferredSize();
        _calculators.setBounds(insets.left + 180, insets.top + 50, size.width, size.height);
        
        //Add submit button with a listener
        _submit = new JButton("Submit");
        _submit.addActionListener(_listener);
        _panel.add(_submit);
        
        size = _submit.getPreferredSize();
        _submit.setBounds(insets.left + 230, insets.top + 100, size.width, size.height);
        
    }
    
    
    public class ClickListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == _submit)
            {
                //Check which calculator the user selected
                String selection = _calculators.getSelectedItem().toString();
                _panel.removeAll();
                switch (selection)
                {
                    case "Addition":
                        _calcType = CalculationTypes.ADDITION;
                        asdf = new AddSubtractDimFrame(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Subtraction":
                        _calcType = CalculationTypes.SUBTRACTION;
                        asdf = new AddSubtractDimFrame(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Multiplication":
                        _calcType = CalculationTypes.MULTIPLICATION;
                        mdf = new MultDimFrame(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Row Echelon":
                        _calcType = CalculationTypes.ROW_ECHELON;
                        omd = new OneMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Reduced Row Echelon":
                        _calcType = CalculationTypes.REDUCED_ROW_ECHELON;
                        omd = new OneMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Inverse":
                        _calcType = CalculationTypes.INVERSE;
                        smd = new SquareMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Transpose":
                        _calcType = CalculationTypes.TRANSPOSE;
                        omd = new OneMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Determinant":
                        _calcType = CalculationTypes.DETERMINANT;
                        smd = new SquareMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Null Space":
                        _calcType = CalculationTypes.NULL_SPACE;
                        omd = new OneMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Column Space":
                        _calcType = CalculationTypes.COLUMN_SPACE;
                        omd = new OneMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Row Space":
                        _calcType = CalculationTypes.ROW_SPACE;
                        omd = new OneMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                    case "Rank":
                        _calcType = CalculationTypes.RANK;
                        omd = new OneMatrixDim(_panel, _calcType);
                        _panel.revalidate();
                        _panel.repaint();
                        break;
                }
            }
        }
    }
}