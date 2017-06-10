package Calculators;

import java.util.ArrayList;

/**
 *
 * @author Elena Ngo
 * The Column Space calculator produces a basis or a set of vectors. A matrix in 
 * reduced form is needed to calculate the Column Space because the calculator 
 * will check which columns are pivot columns. If there are no pivot columns then 
 * the Column Space is empty. Pivot columns are added to the Column Space but the 
 * pivot columns in the basis are NOT reduced.
 */
public class ColumnSpace implements MatrixCalculator {
    private double[][] _matrix;
    private double[][] _columnSpace; //Contains the columns(vectors) that have PIVOTS
    private int _numPivotCols = 0; //Number of pivot Columns will be the column size of _columnSpace
    private double _value;
    private boolean _isEmptySet = false;
    private ReducedRowEchelon rre;
    
    /**
     * Column Space requires one matrix.
     * @param matrix the matrix where the calculator will find its Column Space
     */
    public ColumnSpace(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Calculates the Column Space by creating a _columnSpace matrix that contains
     * a Column Space vector in each row 
     */
    @Override
    public void calculate()
    {
        int rows = _matrix.length;
        //columns will be given by how many pivot columns there are
        double[][] reducedMatrix = _matrix;
        
        //Elim is created to eliminate the corresponding column the doesn't have a pivot
        //And put it into the _columnSpace matrix
        ArrayList<double[]> elim = new ArrayList<>(); 
        Transpose tp1 = new Transpose(_matrix);
        tp1.calculate();
        double[][] transposeM = tp1.getNewMatrix();
        for(int r1 = 0; r1 < transposeM.length; r1++)
        {
            elim.add(transposeM[r1]);
        }
        //Use RREF
        rre = new ReducedRowEchelon(reducedMatrix);
        rre.calculate();
        reducedMatrix = rre.getNewMatrix();
        
        //While counting pivots, if isFreeColumn is true, then remove THAT CURRENT ROW
        boolean pivotFound;
        boolean isFreeColumn; //if it's a column of free variables
        double pivot;
        int removeCount = 0; //counts how many times a row has been removed in elim ArrayList
        //removeCount is needed to prevent out of bounds exception
        int r = 0;
        //Iterate by columns
        for(int c =0; c < reducedMatrix[0].length; c++)
        {
            //reset the booleans and r after iterating through one column;
            pivotFound = false;
            isFreeColumn = false; 
            r=0; //Reset r
            //Find the pivot in the column
            while(r < rows && !pivotFound && !isFreeColumn)
            {
                pivot = reducedMatrix[r][c];
                if(pivot == 1) //rref pivots must be ones
                {
                    //check if there are only zeros below the pivot
                    double element;
                    for(int i = r+1; i < rows; i++)
                    {
                        element = reducedMatrix[i][c];
                        if(element != 0)
                        {
                            isFreeColumn = true;
                            elim.remove(c-removeCount);
                            removeCount++;
                        }
                    }
                    
                    if(!isFreeColumn)
                    {
                        pivotFound = true;
                        _numPivotCols++;
                    }
                }
                else if(pivot != 0 && pivot != 1)
                {
                    //Then it's a free variable in a free variable column
                    isFreeColumn = true;
                    elim.remove(c-removeCount);
                    removeCount++;
                }
                r++;
                //if we still haven't find a pivot in the column and we reached the end
                if(r == rows && !pivotFound) //just in case if we encounter a zeroColumn
                {
                    isFreeColumn = true;
                    elim.remove(c-removeCount);
                    removeCount++;
                }
            }
        }
        
        if(_numPivotCols == 0)
        {
            //Then the set is empty
            _isEmptySet = true;
        }
        else
        {
            //Now make _columnSpace = elim ArrayList
            _columnSpace = new double[elim.size()][rows];
            for(int r2 = 0; r2 < elim.size(); r2++)
            {
                for(int c2=0; c2 < elim.get(0).length; c2++)
                {
                    _columnSpace[r2][c2] = elim.get(r2)[c2];
                }
            }
        }
        
    }
    
    /**
     * Returns the _columnSpace matrix that contains a Column Space vector in each 
     * row
     * @return the _columnSpace matrix
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _columnSpace;
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
     * The Column Space is empty if there are no pivot columns (or pivots) 
     * in the given matrix. If this happens then it returns true. If not, returns
     * false
     * @return true or false depending on whether or not pivots exist in the given
     * matrix
     */
    public boolean isEmptySet()
    {
        return _isEmptySet;
    }
}
