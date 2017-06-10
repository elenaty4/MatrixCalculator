package Calculators;

/**
 * 
 * @author Elena Ngo
 * The Inverse calculator only works with square matrices (nxn matrices), 
 * because only linearly independent square matrices are invertible. 
 * The Inverse Calculator creates an identity matrix that is the same size as the 
 * inputted nxm matrix and join the two matrices together, creating a joint matrix 
 * m rows and n*2 columns.
 * 
 * Note that the Inverse calculator can tell if a nxn matrix is invertible by 
 * checking if there are pivots = n. If a matrix is NOT invertible then it will 
 * not display any result and say that the matrix is not invertible.
 */
public class Inverse implements MatrixCalculator{
    
    private double[][] _matrix;
    private double[][] _identityMatrix;
    private double[][] _inverseMatrix;
    private double _value;
    private boolean _isInvertible = true; //check if the matrix is invertible. Starts off as true
    //if it's not invertible, then the error message will appear in the CalculationFrame
    
    private ReducedRowEchelon rre;
    
    /**
     * Inverse requires one square matrix.
     * @param matrix the matrix that will be inverted if it is invertible
     */
    public Inverse(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Produces the inverse of the given matrix
     */
    @Override
    public void calculate()
    {
        int dimension = _matrix.length;
        _inverseMatrix = new double[dimension][dimension];
        
        //create an identity matrix out of the dimension size
        _identityMatrix = new double[dimension][dimension];
        final double ONE = 1;
        for(int p=0; p < _identityMatrix.length; p++)
        {
            _identityMatrix[p][p] = ONE;
        }
        
        //create a matrix that combines the matrix and identityMatrix together
        int jointColumns = dimension + dimension;
        double[][] jointMatrix = new double[dimension][jointColumns];
        //how to combine the two matrices together?
        //Use System.arraycopy. It works!
        for(int r=0; r < dimension; r++)
        {
            System.arraycopy(_matrix[r], 0, jointMatrix[r], 0, _matrix[r].length);
            System.arraycopy(_identityMatrix[r], 0, jointMatrix[r], _matrix[r].length, 
                    _identityMatrix[r].length);
        }
        
        //Row reduce the jointMatrix by using the ReducedRowEchelon class
        rre = new ReducedRowEchelon(jointMatrix);
        rre.calculate();
        jointMatrix = rre.getNewMatrix(); //The REDUCED jointMatrix
        
        //split up the matrix and check if there are pivots in every diagonal on the first part of it
        double[][] reducedMatrix = new double[dimension][dimension]; //the first part of the jointMatrix
        //inverseMatrix is the second part of the jointMatrix
        for(int r2=0; r2 < dimension; r2++)
        {
            for(int c2=0; c2 < dimension; c2++)
            {
                reducedMatrix[r2][c2] = jointMatrix[r2][c2];
            }
        }
        //Now check if there is a pivot in the diagonal of reducedMatrix
        //If there is no pivot in every diagonal then the matrix is NOT invertible
        for(int d = 0; d < dimension; d++)
        {
            if(reducedMatrix[d][d] != 1)
            {
                _isInvertible = false;
            }
        }
       
        if(_isInvertible)
        {
            //Fill in the _inverseMatrix
            for(int i=0; i < dimension; i++)
            {
                for(int j=0; j < dimension; j++)
                {
                    _inverseMatrix[i][j] = jointMatrix[i][j+dimension];
                }
            }
        }       
    }
    
    /**
     * Returns the inverse of the given matrix
     * @return the result matrix (the inverse matrix)
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _inverseMatrix;
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
    
    /**
     * If the matrix is linearly independent then it returns true. 
     * Else, it returns false
     * @return true or false depending on linear independence
     */
    public boolean isInvertible()
    {
        return _isInvertible;
    }
}
