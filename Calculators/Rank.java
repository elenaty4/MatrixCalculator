package Calculators;

/**
 *
 * @author Elena Ngo
 * The Rank of a matrix is the number of pivot columns in a matrix, or in other 
 * words, the dimension of Column Space or Row Space. The dimension of a basis 
 * is the number of elements in that basis. Since Column Space consist of all 
 * pivot columns, then the dimension of Column Space is the number of pivots. 
 * The number of pivots represents the Rank.
 */
public class Rank implements MatrixCalculator {
    
    private double[][] _matrix;
    private ReducedRowEchelon rre;
    private double _rank = 0; //the Num pivot columns aka the value
    
    /**
     * Rank requires one matrix.
     * @param matrix the matrix where the calculator will find its Rank
     */
    public Rank(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Calculates the Rank of the given matrix
     */
    @Override
    public void calculate()
    {
        //The same as RowSpace EXCEPT that numPivotRows is Rank
        int rows = _matrix.length;
        int columns = _matrix[0].length;
        double[][] reducedMatrix = _matrix;
        
        //Use RREF
        rre = new ReducedRowEchelon(_matrix);
        rre.calculate();
        reducedMatrix = rre.getNewMatrix();
        
        boolean pivotFound;
        boolean isZeroRow; //if it's a column of free variables
        double pivot;
        int c = 0;
        
        //iterating by row
        for(int r =0; r < rows; r++)
        {
            pivotFound = false;
            isZeroRow = false;
            c=0; //reset c
            
            while(c < columns && !pivotFound && !isZeroRow)
            {
                pivot = reducedMatrix[r][c];
                if(pivot != 0)
                {
                    pivotFound = true;
                    _rank++;
                }
                c++;
                //if we still haven't find a pivot in the row and we reached the end
                if(c == columns && !pivotFound)
                {
                    isZeroRow = true;
                }
            }
        }
    }
    
    /**
     * Returns the same given matrix because this calculator doesn't produce a
     * new matrix. Therefore it is not needed
     * @return the given matrix
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _matrix;
    }
    
    /**
     * Returns the rank of the given matrix
     * @return the rank of the matrix
     */
    @Override
    public double getValue()
    {
        return _rank;
    }
}
