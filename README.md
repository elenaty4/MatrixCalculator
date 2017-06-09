# MatrixCalculator
<p><b>Please note that the program is not complete. It has a few bugs and missing pieces: </b></p>
<h4>Bugs: </h4>
<p><b>1. RREF</b></p> 
<p>In the Gaussian Elimination (Reduced Row Echelon) calculator, it works for many different matrices except for some. 
Here's an example of a matrix that will not work: </p>
<p>2 4 6 8</p>
<p>0 0 1 4</p>
<p>0 0 0 1</p>

<p>When this matrix is used as an input for Reduced Row Echelon, it will produce this result which is incorrect: </p>
<p>1 2 1 -5</p>
<p>0 0 1 3</p>
<p>0 0 0 1</p>

<p>Many of the calculators rely on a matrix that is in its reduced form, therefore, if the example matrix above is used in calculators 
that calculates with a reduced matrix, the results will most likely be incorrect as well</p>

<p><b>2. Floating Point Values & Negative Zero</b></p>
<p>Negative zeros will sometimes appear as a result in some of the calculations. That's because floating point numbers are used in code 
to calculate. This also means that if some fractions appear as a result, it'll be in decimal form instead of Rational form. Furthermore, 
the numbers in the results will also display numbers with one decimal place, even if they are whole numbers.</p>

<p><b>3. Eigenvalues and Diagonalization</b></p>
<p>The Eigenvalues and Diagonalization calculators were supposed to be in the program, but they are taken out due to their complexity. 
However, these calculators will be added in the future once a proper algorithm for them is figured out.</p>

<h3><b>Calculators that the Matrix Calculator include: </b></h3>
<p>Addition</p>
<p>Subtraction</p>
<p>Multiplication</p>
<p>Row Echelon</p>
<p>Reduced Row Echelon</p>
<p>Inverse</p>
<p>Transpose</p>
<p>Determinant</p>
<p>Null Space</p>
<p>Column Space</p>
<p>Row Space</p>
<p>Rank</p>

<h3><b>Addition</b></h3>
<p>Addition functions like addition. What is needed for the addition calculator to work is having both matrices be the same size. When 
two mxn matrices gets added together the numbers in each cell in one matrix gets added by the corresponding cells of the second matrix. For example if the user inputted these two matrices: </p>
<p>1 2 3  &nbsp;  &nbsp;    2 4 6</p>
<p>4 5 6  &nbsp;  &nbsp;    8 0 2</p> 
<p>The result will be: </p>
<p>3 6 9</p>
<p>12 5 8</p>

<h3><b>Subtraction</b></h3>
<p>Subtraction function the same way as the Addtion calculator except that we're subtracting the numbers in each cell, not adding them</p>

<h3><b>Multiplication</b></h3>
<p>Multiplication between matrices doesn't function the same way as Addition and Subtraction. In order for Multiplication to work, an nxm matrix must be multiplied with an mxk matrix. In other words, the number of columns on the first matrix must be the same as the number of rows on the second matrix.</p>
<p>Another difference about matrix mutliplication is that it doesn't work like regular multiplication. Matrix multiplication is not 
commmutative, meaning if there exist matrices A and B, &nbsp; A*B =/= B*A</p>
<p>One of the reasons is because if we multiply an nxm matrix with an mxk matrix, the result will be an nxk matrix. In other words, the product matrix will have the same number of rows as the 1st matrix and the same number of columns as the 2nd matrix. Another reason is that the formula for matrix multiplication is that nxm matrix A will be multiplied by ALL of the columns of mxk matrix B. Specifically, <b><i>A*B = [A*b_1, A*b_2 . . . A*b_k]</i></b> where b represents the columns of B. Therefore, <b><i>B*A = [B*a_1, B*a_2 . . . B*a_m]</i></b> which proves that A*B =/= B*A</p>
<p>Here's an example: If a user inputted these two matrices where A is 2x3 and B is 3x2: </p>
<p>1 2 3 &nbsp;  &nbsp; 1 0</p>
<p>4 5 6 &nbsp;  &nbsp; 2 4</p> 
<p> &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;3 3</p>
<p>The result will be a 2x2 matrix: </p>
<p>14 17</p>
<p>32 38</p>

<h3><b>Row Echelon</b></h3>
<p>A matrix is in Row Echelon form if: </p>
<p>1. In pivot columns, there are zeros below every pivot</p>
<p>2. The pivots can be 1 or any other number but the pivots cannot be 0. Zeros can never be pivots</p>
<p>In this matrix calculator, the pivots in row echelon form are always 1.</p>
<p>Different operations are performed when getting the form. <b>Swapping Rows</b> to make the matrix be in echelon form, <b>Scaling Rows</b> when the pivots are not 1, and <b>Row Replacement</b> to get zeros below the pivots in the pivot columns</p>
<p>Here's an example: This matrix below</p>
<p>2 4 6</p>
<p>8 0 2</p>
<p>4 6 8</p>
<p>Will have this row echelon form:</p>
<p>1 2 3</p>
<p>0 1 2</p>
<p>0 0 1</p>

<h3><b>Reduced Row Echelon</b></h3>
<p>A matrix is in Reduced Row Echelon form if: </p>
<p>1. In pivot columns, there are zeros below AND above every pivot</p>
<p>2. ALL pivots must be 1</p>
<p>Different operations are performed when getting the form. <b>Swapping Rows</b> to make the matrix be in echelon form, <b>Scaling Rows</b> when the pivots are not 1, and <b>Row Replacement</b> to get zeros above and below the pivots in the pivot columns</p>
<p>Here's an example: This same matrix below</p>
<p>2 4 6</p>
<p>8 0 2</p>
<p>4 6 8</p>
<p>Will have this reduced row echelon form:</p>
<p>1 0 0</p>
<p>0 1 0</p>
<p>0 0 1</p>
<p>Compare it with its row echelon form</p>

<h3><b>Inverse</b></h3>
<p>The Inverse calculator only works with square matrices (nxn matrices), because only linearly independent square matrices are invertible. The Inverse Calculator creates an identity matrix that is the same size as the inputted nxm matrix and join the two matrices together, creating a joint matrix m rows and n*2 columns. For example, if a user inputs this matrix: </p>
<p>1 2 4</p>
<p>2 0 2</p>
<p>2 0 0</p>
<p>The calculator will create this joint matrix</p>
<p>1 2 4 | 1 0 0</p>
<p>2 0 2 | 0 1 0</p>
<p>2 0 0 | 0 0 1</p>
<p>And row reduce it by using the RREF calculator</p>
<p>1 0 0 | 0 &nbsp; 0 &nbsp; 0.5</p>
<p>0 1 0 | 0.5 -1 &nbsp; 0.75</p>
<p>0 0 1 | 0 &nbsp; 0.5 -0.5</p>
<p>The second half of the matrix is the INVERSE matrix</p>
<p>0 &nbsp; 0 &nbsp; 0.5</p>
<p>0.5 -1 &nbsp; 0.75</p>
<p>0 &nbsp; 0.5 -0.5</p>
<p>Note that the Inverse calculator can tell if a nxn matrix is invertible by checking if there are pivots = n. If a matrix is NOT invertible then it will not display any result and say that the matrix is not invertible.</p>

<h3><b>Transpose</b></h3>
<p>The transpose of a matrix is when the columns of the matrix becomes the rows and the rows becomes the columns. To get a clear picture of it, here's an example:</p>
<p>2 4 6 1</p>
<p>8 0 2 2</p>
<p>4 6 8 3</p>
<p>The transpose of the matrix is:</p>
<p>2 8 4</p>
<p>4 0 6</p>
<p>6 2 8</p>
<p>1 2 3</p>

<h3><b>Determinant</b></h3>
<p>The Determinant can only work for square matrices (nxn matrices). There are different ways to find the determinant but in this calculator, the determinant is calculated during reduced row reduction. The reduced row reduction formula for the determinant is as follows: </p>
<p><b>Row Swapping -</b>If a row is swapped, the determinant will be negative. If the row swap occurs an even number of times, then the determinant will stay positive.</p>
<p><b>Scaling -</b>When a row is multiplied by a scalar c, the determinant is ALSO multiplied by c.</p>
<p><b>Row Replacement -</b>When row replacement occurs, nothing happens to the determinant</p>
<p>Once the matrix is in its reduced form, the calculator checks if the matrix's columns are linearly independent meaning, if there is a pivot in every column(and row). If the matrix is NOT linearly independent, then the determinant is 0. If the matrix IS linearly independent then the determinant is the reciprocal of the product of scalars and is negative depending on how many times Row Swapping occured.</p>
<p>For example, if the user inputted this matrix below: </p>
<p>2 4 6</p>
<p>8 0 2</p>
<p>4 6 8</p>
<p>The calculator will display the determinant value which is <b>40</b>. Why 40? During the row reduction, the rows has been swapped an even number of times and the scalars, (1/2), (1/16), and (4/5) were all multipied together to get (1/40). The reciprocal of (1/40) is 40. The reciprocal occurs because the matrix in its reduced form is linearly independent, therefore the determinant cannot be 0.</p>
