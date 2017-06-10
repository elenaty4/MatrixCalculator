package Calculators;

/**
 *
 * @author Elena Ngo
 * The Determinant can only work for square matrices (nxn matrices). There are 
 * different ways to find the determinant but in this calculator, the determinant 
 * is calculated during reduced row reduction. The reduced row reduction formula 
 * for the determinant is as follows:
 * 
 * Row Swapping - If a row is swapped, the determinant will be negative. If the 
 * row swap occurs an even number of times, then the determinant will stay positive.
 * 
 * Scaling - When a row is multiplied by a scalar c, the determinant is ALSO 
 * multiplied by c.
 * 
 * Row Replacement - When row replacement occurs, nothing happens to the determinant.
 * 
 * Once the matrix is in its reduced form, the calculator checks if the matrix's 
 * columns are linearly independent meaning, if there is a pivot in every 
 * column(and row). If the matrix is NOT linearly independent, then the determinant 
 * is 0. If the matrix IS linearly independent then the determinant is the 
 * reciprocal of the product of scalars and is negative depending on how many 
 * times Row Swapping occurred.
 */
public class Determinant implements MatrixCalculator{
    
    private double[][] _matrix;
    private double _determinant = 1; //the value that will be returned
    private ReducedRowEchelon rre;
    private boolean negation = false;
    //private double scalar; //when all of the scalars multiply each other up
    //get the denominator of the scalars first 
    
    /**
     * Determinant requires one square matrix.
     * @param matrix the matrix where the calculator will determine its determinant
     */
    public Determinant(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Calculates and produces the matrix's determinant
     */
    @Override
    public void calculate()
    {
        double[][] reducedMatrix = _matrix; //the reduced matrix will change in rref 
        int dimension = reducedMatrix.length;
        //Remember this formula when finding determinant by row reduction:
        //Swapping Rows: -det
        //Row Replacement: Nothing happens
        //Scaling Rows: scalar * det
        //if det(B) is 1, then invert _determinant (get the denominator of the PRODUCT of scalars)
        //if det(B) is 0, then _determinant = 0
        
        //Because of this formula, we must add RREF methods to this class with a
        //small difference: change the _determinant in it
        reducedMatrix = swapRows(_matrix);
        for (int p = 0; p < reducedMatrix.length; ++p)
        {
            //Make this pivot into a 1
            double pivot = reducedMatrix[p][p]; //Very first element is pivot if it's not zero
            if (pivot != 0)
            {
                //Scaling Rows: scalar * det
                double scalar = 1 / pivot;
                _determinant = _determinant * pivot;
                for (int i = 0; i < reducedMatrix[p].length; ++i) //interate through the ROW
                {
                    reducedMatrix[p][i] = reducedMatrix[p][i] * scalar;
                }
            }
            
            //Make other rows zero
            //Row Replacement: Nothing happens
            for (int r = 0; r < reducedMatrix.length; ++r)
            {
                if (r != p)
                {
                    double f = reducedMatrix[r][p];
                    for (int i = 0; i < reducedMatrix[r].length; ++i)
                    {
                        reducedMatrix[r][i] = reducedMatrix[r][i] - f * reducedMatrix[p][i];
                        //Turn the weird negative zeros to positive zeros
                        //But how?
                    }
                }
            }
        }
        
        if (negation)
        {
            _determinant = -_determinant;
        }
        
        //Now check if there is a zero in the diagonal.
        //If there is one then the determinant is zero
        for(int d = 0; d < dimension; d++)
        {
            if(reducedMatrix[d][d] == 0)
            {
                _determinant = 0;
            }
        }
    }
    
    /**
     * Swaps the rows in the given matrix to make the matrix appear in echelon form.
     * This is here because if the row swap occurs, it can possibly negate the 
     * determinant
     * @param matrix the given matrix
     * @return the matrix with its rows swapped
     */
    private double[][] swapRows(double[][] matrix) //Swap the rows
    {
        int swapCount = 0;
        double[][] swappedMatrix;
        int c = 0; //column iterator
        //increases by 1 everytime a pivot is found in order to swap pivotRows to the top correctly
        int pivots = 0; 
        
        //checks if there's a zero column and checks if there are only zeros before a non-zero
        int zeroCounter = 0; 
        double element;
        double[] rowHolder = new double[matrix.length]; //holds on to one row during the swap
        
        while (c < matrix[0].length)
        {
            //iterate through the first row 
            
            for(int r=0; r<matrix.length; r++)
            {
                element = matrix[r][c];
                if(element != 0)
                {
                    //check if there are zeros above it
                    if(r == zeroCounter) 
                    {
                        //check if there are no more NON pivot rows for that row
                        if(pivots < matrix.length) 
                        {
                            //then the swap occurs
                            //Swapping Rows: -det
                            
                            if(matrix[r] != matrix[pivots])
                            {
                                rowHolder = matrix[r];
                                matrix[r] = matrix[pivots];
                                matrix[pivots] = rowHolder;
                                swapCount++;
                            }
                            if(swapCount%2 != 0) //check if it's not even
                            {
                                //then -det occurs
                                negation = true;
                            }
                            
                            c++; //go to next column
                            //check if we are at the last column
                            if (c < matrix[0].length)
                            {
                                pivots++; 
                                r=pivots-1; //draw a diagram to make this work
                                zeroCounter=pivots;
                            }
                            else
                            {
                                r = matrix.length;
                            }
                        }
                    }
                }
                else //theres a zero in the column
                {
                    zeroCounter++;
                }
                
                //check if the column has ALL zeros (aka, a Zero Column)
                if(zeroCounter >= matrix.length)
                {
                    c++; //go to next column
                    //check if we reach the last column
                    if (c < matrix[0].length)
                    {
                        r=pivots-1; //draw a diagram to make this work
                        zeroCounter=pivots;
                    }
                    else
                    {
                        r = matrix.length;
                    }
                }
            }

        }
        swappedMatrix = matrix;
        return swappedMatrix;
    }
    
    /**
     * Returns the same given matrix because this calculator doesn't produce a
     * new matrix. Therefore it is not needed
     * @return the given matrix
     */
    @Override
    public double[][] getNewMatrix()
    {
        //returns the matrix that you gave it, because Determinant calculator doesn't
        //produce a new matrix. It produces a VALUE
        return _matrix;
    }
    
    /**
     * Returns the determinant of the given matrix
     * @return the determinant of the matrix
     */
    @Override
    public double getValue()
    {
        return _determinant;
    }
}
