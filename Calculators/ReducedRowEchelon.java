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
 * and Row Replacement to get zeros above and below the pivots in the pivot columns.
 */
public class ReducedRowEchelon implements MatrixCalculator {

    private double[][] _matrix;
    private double[][] _rrefMatrix;
    private double _value;
    
    /**
     * Reduced Row Echelon requires one matrix.
     * @param matrix the matrix that will be reduced to reduced row echelon form
     */
    public ReducedRowEchelon(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Reduces the matrix into reduced row echelon form
     */
    @Override
    public void calculate()
    {
        int rows = _matrix.length;
        int columns = _matrix[0].length;
        
        //Copy the matrix so that you can change it later
        _rrefMatrix = _matrix;
        
        _rrefMatrix = swapRows(_rrefMatrix);
        
        if(rows <= columns)
        {
            for (int p = 0; p < _rrefMatrix.length; ++p)
            {
                //Make this pivot into a 1
                double pivot = _rrefMatrix[p][p]; //Very first element is pivot if it's not zero
                if (pivot != 0)
                {
                    //SCALE THE PIVOT AND EVERYTHING ELSE IN THE ROW MUST BE MULTIPLIED WITH IT
                    double scalar = 1 / pivot;
                    for (int i = 0; i < _rrefMatrix[p].length; ++i) //interate through the ROW
                    {
                        _rrefMatrix[p][i] = _rrefMatrix[p][i] * scalar;
                    }
                }

                //Make other rows zero
                for (int r = 0; r < _rrefMatrix.length; ++r)
                {
                    if (r != p)
                    {
                        double f = _rrefMatrix[r][p];
                        for (int i = 0; i < _rrefMatrix[r].length; ++i)
                        {
                            _rrefMatrix[r][i] = _rrefMatrix[r][i] - f * _rrefMatrix[p][i];
                            //Turn the weird negative zeros to positive zeros
                            //But how?
                        }
                    }
                }
            }
        }
        else if(rows > columns)
        {
            for (int p = 0; p < _rrefMatrix[0].length; ++p)
            {
                //Make this pivot into a 1
                double pivot = _rrefMatrix[p][p]; //Very first element is pivot if it's not zero
                if (pivot != 0)
                {
                    //SCALE THE PIVOT AND EVERYTHING ELSE IN THE ROW MUST BE MULTIPLIED WITH IT
                    double scalar = 1 / pivot;
                    for (int i = 0; i < _rrefMatrix[p].length; ++i) //interate through the ROW
                    {
                        _rrefMatrix[p][i] = _rrefMatrix[p][i] * scalar;
                    }
                }

                //Make other rows zero
                for (int r = 0; r < _rrefMatrix.length; ++r)
                {
                    if (r != p)
                    {
                        double f = _rrefMatrix[r][p];
                        for (int i = 0; i < _rrefMatrix[r].length; ++i)
                        {
                            _rrefMatrix[r][i] = _rrefMatrix[r][i] - f * _rrefMatrix[p][i];
                            //Turn the weird negative zeros to positive zeros
                            //But how?
                        }
                    }
                }
            }
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
     * Returns the reduced row echelon matrix
     * @return the result matrix (the matrix in reduced row echelon form)
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _rrefMatrix;
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

