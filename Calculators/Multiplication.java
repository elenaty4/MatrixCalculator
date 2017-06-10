package Calculators;

/**
 *
 * @author Elena Ngo
 * Multiplication between matrices doesn't function the same way as Addition and
 * Subtraction. In order for Multiplication to work, an nxm matrix must be 
 * multiplied with an mxk matrix. In other words, the number of columns on the 
 * first matrix must be the same as the number of rows on the second matrix.
 * Another difference about matrix multiplication is that it doesn't work like 
 * regular multiplication. Matrix multiplication is not commutative, meaning if
 * there exist matrices A and B,   A*B =/= B*A
 * 
 * One of the reasons is because if we multiply an nxm matrix with an mxk matrix,
 * the result will be an nxk matrix. In other words, the product matrix will have
 * the same number of rows as the 1st matrix and the same number of columns as 
 * the 2nd matrix. Another reason is that the formula for matrix multiplication 
 * is that nxm matrix A will be multiplied by ALL of the columns of mxk matrix B.
 * Specifically, A*B = [A*b_1, A*b_2 . . . A*b_k] where b represents the columns 
 * of B. Therefore, B*A = [B*a_1, B*a_2 . . . B*a_m] which proves that A*B =/= B*A
 */
public class Multiplication implements MatrixCalculator {
    
    private double[][] _matrix1;
    private double[][] _matrix2;
    private double[][] _productMatrix;
    private double _value;
    
    /**
     * Matrix Multiplication requires 2 matrices. An mxn one and an nxk one
     * @param matrix1 The 1st matrix
     * @param matrix2 The 2nd matrix
     */
    public Multiplication(double[][] matrix1, double[][] matrix2)
    {
        _matrix1 = matrix1;
        _matrix2 = matrix2;
    }
    
    /**
     * Multiplies two matrices together and produces a new matrix that contains
     * the product
     */
    @Override
    public void calculate()
    {
        //for matrix A mxn and matrix B nxk, 
        //new matrix will have m columns and k rows
        int rows = _matrix1.length; //matrix 1 rows
        int columns = _matrix2[0].length; //matrix 2 columns
        
        _productMatrix = new double[rows][columns];
        
        //to multiply two matrices, multiply ALL of the rows of the FIRST matrix (matrix1)
        //for EACH COLUMN of the SECOND matrix (matrix2)
        //In order words, let A be matrix1 and B be matrix2
        // A * B = Ab1 + Ab2 .... Abk
        //where k is the max number of columns of B
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                for(int i = 0; i < _matrix2.length; i++)
                {
                    _productMatrix[r][c] = _productMatrix[r][c] + _matrix1[r][i] * _matrix2[i][c];
                }
            }
        }
    }
    
    /**
     * Returns the product matrix
     * @return the result matrix (the product)
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _productMatrix;
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