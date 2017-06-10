package Calculators;

/**
 *
 * @author Elena Ngo
 * A matrix is in Reduced Row Echelon form if:
 * 1. In pivot columns, there are zeros below AND above every pivot
 * 2. ALL pivots must be 1
 * 
 * Different operations are performed when getting the form. Swapping Rows to 
 * make the matrix be in echelon form, Scaling Rows when the pivots are not 1, 
 * and Row Replacement to get zeros above and below the pivots in the pivot columns
 */
public class RowEchelon implements MatrixCalculator{
    private double[][] _matrix;
    private double[][] _refMatrix;
    private double _value;
    
    /**
     * Row Echelon requires one matrix.
     * @param matrix the matrix that will be reduced to row echelon form
     */
    public RowEchelon(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Reduces the matrix into row echelon form
     */
    @Override
    public void calculate()
    {
        //Copy the matrix so that you can change it later
        _refMatrix = _matrix;
        //swap the rows
        _refMatrix = swapRows(_refMatrix);
        int pivotPosition = -1; //pivot position
        double element;
        double pivot;
        double[] rowHolder = new double[_refMatrix[0].length]; //for row replacement
        boolean pivotFound = false;
        int i = 0;
        
        //Iterate by row first
        for(int r = 0; r < _refMatrix.length; r++)
        {
            //iterate through that row
            while(!pivotFound && i < _refMatrix[r].length)
            {
                pivot = _refMatrix[r][i];
                //check if pivot is not zero. Zeros can never be pivots
                if(pivot != 0 && i > pivotPosition)
                {
                    //and if the pivotPosition is greater than the old pivotPosition
                    pivotFound = true;
                    pivotPosition = i;
                    //scale the row first
                    double scalar = 1 / pivot;
                    for(int j=0; j< _refMatrix[r].length; j++)
                    {
                        _refMatrix[r][j] = _refMatrix[r][j] * scalar;
                    }
                    
                    //now check what's under the pivot (r++) and if they are 0
                    //leave them alone, if not, ROW REPLACE
                    for(int u = r+1; u< _refMatrix.length; u++)
                    {
                        element = _refMatrix[u][i];
                        if(element != 0)
                        {
                            //Multiply the first row by the NEGATIVE element
                            //fr means first row
                            //iterate through the first row for it
                            for(int fr=0; fr < rowHolder.length; fr++)
                            {
                                rowHolder[fr] = _refMatrix[r][fr];
                                rowHolder[fr] = rowHolder[fr] * -element;
                            }
                            //add the first row towards the second (u) row
                            //iterate through the second row
                            for(int sr=0; sr < _refMatrix[0].length; sr++)
                            {
                                _refMatrix[u][sr] = _refMatrix[u][sr] + rowHolder[sr];
                            }
                        }
                    }
                    i=0; //i resets
                }
                else
                {
                    i++;
                }
            }    
            pivotFound = false;
        }
    }
    
    /**
     * Swaps the rows in the given matrix to make the matrix appear in echelon form
     * @param matrix the given matrix
     * @return the matrix with its rows swapped
     */
    private double[][] swapRows(double[][] matrix) //Swap the rows
    {
        double[][] swappedMatrix;
        int c = 0; //column iterator
        //increases by 1 everytime a pivot is found in order to swap pivotRows to the top correctly
        int pivots = 0; 
        
        //checks if there's a zero column and checks if there are only zeros before a non-zero
        int zeroCounter = 0; 
        double element;
        double[] rowHolder; //holds on to one row during the swap
        
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
                            rowHolder = matrix[r];
                            matrix[r] = matrix[pivots];
                            matrix[pivots] = rowHolder;
                            
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
     * Returns the row echelon matrix
     * @return the result matrix (the matrix in row echelon form)
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _refMatrix;
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
