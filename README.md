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
