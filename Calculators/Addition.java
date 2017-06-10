package Calculators;

/**
 *
 * @author Elena Ngo
 * Addition functions like addition. 
 * What is needed for the addition calculator to work is having both matrices be 
 * the same size. When two mxn matrices gets added together the numbers in each 
 * cell in one matrix gets added by the corresponding cells of the second matrix.
 */
public class Addition implements MatrixCalculator {
    
    private double[][] _matrix1;
    private double[][] _matrix2;
    private double[][] _sumMatrix;
    private double _value;
    
    /**
     * Matrix Addition requires 2 matrices of the same size
     * @param matrix1 The 1st matrix
     * @param matrix2 The 2nd matrix
     */
    public Addition(double[][] matrix1, double[][] matrix2)
    {
        _matrix1 = matrix1;
        _matrix2 = matrix2;
    }
    
    /**
     * Adds two matrices together and produces a new matrix that contains the sum
     */
    @Override
    public void calculate()
    {
        int rows = _matrix1.length;
        int columns = _matrix1[0].length;
        double sum;
        
        //the sum matrix is the same size as the other two matrices
        _sumMatrix = new double[rows][columns];
        
        //calculate the matrices by adding their elements together.
        //NO JFRAMES INVOLVED 
        //JUST CALCULATE THE MATRICES
        //since matrix 1 and 2 are the same size, there will be 1 for loop
        for (int r = 0; r < _matrix1.length; r++)
        {
            for (int c = 0; c < _matrix1[r].length; c++)
            {
                sum = _matrix1[r][c] + _matrix2[r][c];
                _sumMatrix[r][c] = sum;
            }
        }
    }
    
    /**
     * Returns the sum matrix
     * @return the result matrix (the sum)
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _sumMatrix;
    }
    
    /**
     * Returns nothing. This is here because Addition implements MatrixCalculator
     * like a subclass and must use all of its abstract methods. This method is 
     * not used
     * @return a value but there is no value.
     */
    @Override 
    public double getValue()
    {
        return _value; //value will always be null
    }
}
