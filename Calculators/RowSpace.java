package Calculators;

import java.util.ArrayList;

/**
 *
 * @author Elena Ngo
 * The Row Space calculator produces a basis or a set of matrix rows. A matrix in 
 * reduced form is needed to calculate the Row Space because the calculator 
 * will check which rows contain pivots. If there are no pivot rows then 
 * the Row Space is empty. Pivot Rows are added to the Row Space but the 
 * pivot rows in the basis ARE reduced (RREF).
 */
public class RowSpace implements MatrixCalculator{
    
    private double[][] _matrix;
    private double[][] _rowSpace; //Contains the rows(arrays) that have PIVOTS
    private int _numPivotRows = 0; //Number of Pivot Rows will be the size of _rowSpace
    private double _value;
    private boolean _isEmptySet = false;
    private ReducedRowEchelon rre;
    
    /**
     * Row Space requires one matrix.
     * @param matrix the matrix where the calculator will find its Row Space
     */
    public RowSpace(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Calculates the Row Space by creating a _rowSpace matrix that contains
     * a reduced pivot row in each row
     */
    @Override
    public void calculate()
    {
        //Unlike Column Space, Row Space contains ARRAYS of the rows in RREF FORM
        //NOT in original matrix form! Therefore, RREF class is definitely needed
        int rows = _matrix.length;
        int columns = _matrix[0].length;
        double[][] reducedMatrix = _matrix;
        
        //Use RREF
        rre = new ReducedRowEchelon(reducedMatrix);
        rre.calculate();
        reducedMatrix = rre.getNewMatrix();
        
        //Make ArrayList = the reducedMatrix
        ArrayList<double[]> elim = new ArrayList<>();
        for(int r = 0; r < rows; r++)
        {
            elim.add(reducedMatrix[r]);
        }
        
        //While counting pivots, if isFreeColumn is true, then remove THAT CURRENT ROW
        boolean pivotFound;
        boolean isZeroRow; //if it's a row of zeros. A free row is a row of zeros
        double pivot;
        int removeCount = 0; //counts how many times a row has been removed in elim ArrayList
        //removeCount is needed to prevent out of bounds exception
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
                    _numPivotRows++;
                }
                c++;
                //if we still haven't find a pivot in the row and we reached the end
                if(c == columns && !pivotFound) //just in case if we encounter a zeroRow
                {
                    isZeroRow = true;
                    elim.remove(r-removeCount);
                    removeCount++;
                }
            }
        }
        
        //Check if there are NO pivotRows. If so, then the set is empty
        if(_numPivotRows == 0)
        {
            _isEmptySet = true;
        }
        else
        {
            //Now make _rowSpace = elim
            _rowSpace = new double[elim.size()][columns];
            for(int r2=0; r2 < elim.size(); r2++)
            {
                for(int c2=0; c2 < columns; c2++)
                {
                    _rowSpace[r2][c2] = elim.get(r2)[c2];
                }
            }
        }
        
    }
    
    /**
     * Returns the _rowSpace matrix that contains a pivot row in each row
     * @return the _rowSpace matrix
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _rowSpace;
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
     * The Row Space is empty if there are no pivot rows (or pivots) 
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