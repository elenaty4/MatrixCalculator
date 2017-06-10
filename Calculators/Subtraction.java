package Calculators;

/**
 *
 * @author Elena Ngo
 * Subtraction functions like subtraction. 
 * What is needed for the subtraction calculator to work is having both matrices 
 * be the same size. When two mxn matrices gets added together, the numbers in each 
 * cell in one matrix gets subtracted by the corresponding cells of the second matrix.
 */
public class Subtraction implements MatrixCalculator{
    
    private double[][] _matrix1;
    private double[][] _matrix2;
    private double[][] _diffMatrix;
    private double _value;
    
    /**
     * Matrix Subtraction requires 2 matrices of the same size
     * @param matrix1 The 1st matrix
     * @param matrix2 The 2nd matrix
     */
    public Subtraction(double[][] matrix1, double[][] matrix2)
    {
        _matrix1 = matrix1;
        _matrix2 = matrix2;
    }
    
    /**
     * Subtracts two matrices together and produces a new matrix that contains
     * the difference
     */
    @Override
    public void calculate()
    {
        int rows = _matrix1.length;
        int columns = _matrix1[0].length;
        double difference;
        
        //the sum matrix is the same size as the other two matrices
        _diffMatrix = new double[rows][columns];
        
        //calculate the matrices by adding their elements together.
        //NO FRAMES INVOLVED 
        //JUST CALCULATE THE MATRICES
        //since matrix 1 and 2 are the same size, there will be 1 for loop
        for (int r = 0; r < _matrix1.length; r++)
        {
            for (int c = 0; c < _matrix1[r].length; c++)
            {
                difference = _matrix1[r][c] - _matrix2[r][c];
                _diffMatrix[r][c] = difference;
            }
        }
    }
    
    /**
     * Returns the difference matrix
     * @return the result matrix (the difference)
     */
    @Override 
    public double[][] getNewMatrix()
    {
        return _diffMatrix; 
    }
    
    /**
     * Returns nothing. This is here because Subtraction implements MatrixCalculator
     * like a subclass and must use all of its abstract methods. This method is 
     * not used
     * @return a value but there is no value.
     */
    @Override
    public double getValue()
    {
        return _value;
    }
    
}
