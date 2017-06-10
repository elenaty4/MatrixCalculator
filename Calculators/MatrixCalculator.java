package Calculators;

/**
 *
 * @author Elena Ngo
 * An Interface that contains methods for the Calculators to use. Each calculator
 * is a Matrix Calculator
 */
public interface MatrixCalculator {
    
    public void calculate();
    
    public double[][] getNewMatrix();
    
    public double getValue();
}
