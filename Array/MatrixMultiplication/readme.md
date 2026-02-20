# MATRIX MULTIPLICATION
-Its a matrix mulplication using arrays .
-The psuedo code its below
# PSUEDO CODE
BEGIN
DEFINE matix A as 3 *2
DEFINE matix B as 2*3
    DEFINE matrix A as:
        [1  2  3]
        [4  5  6]
    DEFINE matrix B as:
        [7   8]
        [9  18]
        [11  9]
    CREATE matrix C with 2 rows and 2 columns
    INITIALIZE all elements of C to 0
    FOR i FROM 0 TO 1 DO
        FOR j FROM 0 TO 1 DO
            FOR k FROM 0 TO 2 DO
                C[i][j] ← C[i][j] + (A[i][k] × B[k][j])
            END FOR
        END FOR
    END FOR

    PRINT C[0][0] , C[0][1]
    PRINT C[1][0] , C[1][1]

END


