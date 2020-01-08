# sudoku-creator-solver
Creates a sudoku puzzle and attempts to solve it

## App class provides two methods:

 * playUniqueSudoku() - for creating a well formed Sudoku with single solution and then solving it.
 * playMultiSolSudoku() - for creating a multi-solution Sudoku and then finding out all possible solutions.


## Approach for Sudoku creation-

* Create first row with shuffled number from 1 to 9
* Create next row with shuffled number from 1 to 9 and -
    * Check if it follows below basic Sudoku rules w.r.t previous rows - 
        * No duplicates in row (by default true, no need to check)
        * No duplicates in column
        * No duplicates in block
    * If above conditions are satisfied then add row else repeat step 2
* Repeat step 2 until all 9 rows are added


## Approach for well formed puzzle creation-

* Randomly hide elements of Sudoku on 0.5 probability
* Check if created puzzle has more than one solution
    * If yes, discard and repeat from step 1
    * If no, return the puzzle


## Approach for finding all solutions for a multi-solution puzzle-

* Attempt to Solve puzzle
* Solution will break on first cell with multiple possibilities and return puzzle
* Iterate over all possible values for that cell and start from step 1 in recursive manner until all solutions are found
