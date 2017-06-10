package Calculators;

/**
 *
 * @author Elena Ngo
 * The transpose of a matrix is when the columns of the matrix becomes the rows 
 * and the rows becomes the columns.
 */
public class Transpose implements MatrixCalculator{
    
    private double[][] _matrix;
    private double[][] _transposeMatrix;
    private double _value; 
    
    /**
     * Transpose requires one matrix.
     * @param matrix the matrix that will be transposed
     */
    public Transpose(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Creates the transpose of the matrix
     */
    @Override
    public void calculate()
    {
        int rows = _matrix.length;
        int columns = _matrix[0].length;
        
        _transposeMatrix = new double[columns][rows];
        
        for(int r=0; r < rows; r++)
        {
            for(int c=0; c < columns; c++)
            {
                _transposeMatrix[c][r] = _matrix[r][c];
            }
        }
    }
    
    /**
     * Returns the transpose of the given matrix
     * @return the result matrix (the transpose matrix)
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _transposeMatrix;
    }
    
    /**
     * Returns nothing. This is here because Multiplication implements MatrixCalculator
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
