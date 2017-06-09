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
