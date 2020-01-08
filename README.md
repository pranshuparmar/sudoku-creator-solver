# sudoku-creator-solver
Creates a sudoku puzzle and attempts to solve it

App class provides two methods:
 1) playUniqueSudoku() - for creating a well formed Sudoku with single solution and then solving it.
 2) playMultiSolSudoku() - for creating a multi-solution Sudoku and then finding out all possible solutions.


Approach for Sudoku creation-
1) Create first row with shuffled number from 1 to 9
2) Create next row with shuffled number from 1 to 9 and -
    2.1) Check if it follows below basic Sudoku rules w.r.t previous rows
        2.1.1) No duplicates in row (by default true, no need to check)
        2.1.2) No duplicates in column
        2.1.3) No duplicates in block
    2.2) If above conditions are satisfied then add row else repeat step 2
3) Repeat step 2 until all 9 rows are added


Approach for well formed puzzle creation-
1) Randomly hide elements of Sudoku on 0.5 probability
2) Check if created puzzle has more than one solution
    2.1) If yes, discard and repeat from step 1
    2.2) If no, return the puzzle


Approach for finding all solutions for a multi-solution puzzle-
1) Attempt to Solve puzzle
2) Solution will break on first cell with multiple possibilities and return puzzle
3) Iterate over all possible values for that cell and start from step 1 in recursive manner until all solutions are found
