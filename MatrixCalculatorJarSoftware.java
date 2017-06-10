package matrixcalculatorjarsoftware;

import FrameActivities.SoftwareFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Elena Ngo
 * @version 1.0 June 9, 2017
 * 
 * Matrix Calculator calculates different Linear Algebra-related operations
 * See ReadMe.md (https://github.com/elenaty4/MatrixCalculator) for more 
 * information 
 * 
 */
public class MatrixCalculatorJarSoftware {

    /**
     * Runs the software by creating a JFrame
     */
    public static void runSoftware()
    {
        JFrame frame = new SoftwareFrame();
        frame.setTitle("Matrix Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        runSoftware();
    }
    
}
