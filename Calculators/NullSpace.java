package Calculators;

import java.util.ArrayList;
/**
 *
 * @author Elena Ngo
 * The Null Space calculator doesn't produce a matrix nor a value. It produces a 
 * basis or a set of vectors. The Null Space is calculated by putting the matrix 
 * in reduced row echelon form. After the matrix is in reduced form, the calculator 
 * checks if there are free columns. Free Columns are columns that doesn't have 
 * a pivot. If all of the matrix's columns are linearly independent, then the 
 * Null Space is an empty set.
 * 
 * If there are free columns, then free variables exist and the Null Space is 
 * not empty. Usually we put the matrix in parametric vector form and get add the 
 * parametric vectors into the Null Space basis, but in this calculator, it uses 
 * a different algorithm. It NEGATES the numbers in the free column and add a 1 
 * to the nth spot in the nth column. The vectors in the Null Space has the same 
 * number of elements as the number of columns.
 */
public class NullSpace implements MatrixCalculator {
    
    private double[][] _matrix;
    private ArrayList<double[]> _parametricV; //Parametric Vectors
    private double[][] _nullSpace; //It's an array containing the two nullSpace vectors (as rows)
    private double _value;
    private int _numPivots = 0;
    private boolean _isEmptySet = false; //empty basis
    private ReducedRowEchelon rre;
    
    /**
     * Null Space requires one matrix.
     * @param matrix the matrix where the calculator will find its Null Space
     */
    public NullSpace(double[][] matrix)
    {
        _matrix = matrix;
    }
    
    /**
     * Calculates the Null Space by creating a _nullSpace matrix that contains
     * a Null Space vector in each row 
     */
    @Override
    public void calculate()
    {
        int rows = _matrix.length;
        int columns = _matrix[0].length;
        double[][] reducedMatrix = _matrix;
        
        //Use reduced row echelon form
        rre = new ReducedRowEchelon(_matrix);
        rre.calculate();
        reducedMatrix = rre.getNewMatrix();
        _parametricV = new ArrayList<>();
        double[] rowForAL; //row for ArrayList
        
        
        //While counting pivots, if isFreeColumn is true, then remove THAT CURRENT ROW
        boolean pivotFound;
        boolean isFreeColumn; //if it's a column of free variables
        boolean oneIsAdded; //an extra value "1" is added to the rowForAL
        double pivot;
        double element;
        int r = 0;
        //Iterate by columns
        for(int c =0; c < reducedMatrix[0].length; c++)
        {
            //reset the booleans and r after iterating through one column;
            pivotFound = false;
            isFreeColumn = false; 
            oneIsAdded = false;
            r=0; //Reset r
            //Find the pivot in the column
            while(r < rows && !pivotFound && !isFreeColumn)
            {
                pivot = reducedMatrix[r][c];
                if(pivot == 1) //rref pivots must be ones
                {
                    //check if there are only zeros below the pivot
                    for(int i = r+1; i < rows; i++)
                    {
                        element = reducedMatrix[i][c];
                        if(element != 0)
                        {
                            isFreeColumn = true;
                            rowForAL = new double[columns]; 
                            int limit = 0; //Limit is here to prevent out of bounds error
                            if(columns >= rows)
                            {
                                limit = rows;
                            }
                            else if(rows > columns)
                            {
                                limit = columns;
                            }
                            for(int j=0; j < limit; j++)
                            {
                                if(reducedMatrix[j][c] == 0) //To prevent -0.0
                                {
                                    element = reducedMatrix[j][c];
                                }
                                else
                                {
                                    element = -1 * reducedMatrix[j][c]; //negate the element
                                }
                                if(j == c) //c is the freeColumn position
                                {
                                    rowForAL[j] = 1;
                                    oneIsAdded = true;
                                    if(j+1 < columns) //columns is the size of rowForAL
                                    {
                                        rowForAL[j+1] = element;
                                    }
                                }
                                else
                                {
                                    rowForAL[j] = element;  
                                }
                                
                                if(!oneIsAdded && j+1 == rows) //if one is NOT added yet
                                {
                                    rowForAL[j+1] = 1;
                                }
                            }
                            _parametricV.add(rowForAL); //Add the row to the ArrayList
                        }
                    }
                    
                    if(!isFreeColumn)
                    {
                        pivotFound = true;
                        _numPivots++;
                    }
                }
                else if(pivot != 0 && pivot != 1)
                {
                    //Then it's a free variable in a free variable column
                    isFreeColumn = true;
                    rowForAL = new double[columns]; 
                    int limit = 0; //Limit is here to prevent out of bounds error
                    if(columns >= rows)
                    {
                        limit = rows;
                    }
                    else if(rows > columns)
                    {
                        limit = columns;
                    }
                    
                    for(int j=0; j < limit; j++)
                    {
                        if(reducedMatrix[j][c] == 0) //To prevent -0.0
                        {
                            element = reducedMatrix[j][c];
                        }
                        else
                        {
                            element = -1 * reducedMatrix[j][c]; //negate the element
                        }
                        if(j == c) //c is the freeColumn position
                        {
                            rowForAL[j] = 1;
                            oneIsAdded = true;
                            if(j+1 < columns) //columns is the size of rowForAL
                            {
                                rowForAL[j+1] = element;
                            }
                        }
                        else
                        {
                            rowForAL[j] = element;  
                        }
                                
                        if(!oneIsAdded && j+1 == rows) //if one is NOT added yet
                        {
                            rowForAL[j+1] = 1;
                        }
                    }
                    _parametricV.add(rowForAL); //Add the row to the ArrayList
                    
                }
                r++;
                //if we still haven't find a pivot in the column and we reached the end
                if(r == rows && !pivotFound) //just in case if we encounter a zeroColumn
                {
                    isFreeColumn = true;
                    rowForAL = new double[columns]; 
                    int limit = 0; //Limit is here to prevent out of bounds error
                    if(columns >= rows)
                    {
                        limit = rows;
                    }
                    else if(rows > columns)
                    {
                        limit = columns;
                    }
                    for(int j=0; j < limit; j++)
                    {
                        if(reducedMatrix[j][c] == 0) //To prevent -0.0
                        {
                            element = reducedMatrix[j][c];
                        }
                        else
                        {
                            element = -1 * reducedMatrix[j][c]; //negate the element
                        }
                        if(j == c) //c is the freeColumn position
                        {
                            rowForAL[j] = 1;
                            oneIsAdded = true;
                            if(j+1 < columns) //columns is the size of rowForAL
                            {
                                rowForAL[j+1] = element;
                            }
                        }
                        else
                        {
                            rowForAL[j] = element;  
                        }
                                
                        if(!oneIsAdded && j+1 == rows) //if one is NOT added yet
                        {
                            rowForAL[j+1] = 1;
                        }
                    }
                    _parametricV.add(rowForAL); //Add the row to the ArrayList
                }
            }
        }
        
        //Check if there are pivots in every column. Then that means the NullSpace is empty
        if(_numPivots == columns)
        {
            //Then _nullSpace is an empty set
            _isEmptySet = true;
        }
        else
        {
            //Now make _nullSpace = ArrayList
            _nullSpace = new double[_parametricV.size()][columns];
            for(int r2 = 0; r2 < _parametricV.size(); r2++)
            {
                for(int c2=0; c2 < _parametricV.get(0).length; c2++)
                {
                    _nullSpace[r2][c2] = _parametricV.get(r2)[c2];
                }
            }
            
            //print out matrix to check
            for(int i=0; i< _nullSpace.length; i++)
            {
                for(int j=0; j< _nullSpace[0].length; j++)
                {
                    System.out.print(_nullSpace[i][j] + " ");
                }
                System.out.println("");
            }  
        }
    }
    
    /**
     * Returns the _nullSpace matrix that contains a Null Space vector in each 
     * row
     * @return the _nullSpace matrix
     */
    @Override
    public double[][] getNewMatrix()
    {
        return _nullSpace;
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
     * The Null Space is empty if there are no free columns (or free variables) 
     * in the given matrix. If this happens then it returns true. If not, returns
     * false
     * @return true or false depending on whether or not free variables exist in 
     * the given matrix
     */
    public boolean isEmptySet()
    {
        return _isEmptySet;
    }
}
