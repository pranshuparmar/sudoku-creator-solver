package com.pranshu.sudoku.helper;

import java.util.ArrayList;

import com.pranshu.sudoku.util.SudokuSolver;

public class MultiSolutionHelper {

	private static ArrayList<ArrayList<Integer[]>> allSolutions = new ArrayList<ArrayList<Integer[]>>();

	public ArrayList<ArrayList<Integer[]>> attemptMultipleSolutions(ArrayList<Integer[]> puzzle) {
		SudokuSolver solver = new SudokuSolver();
		ArrayList<Integer[]> solution = solver.solveSudoku(puzzle);
		if (isSolved(solution)) {
			allSolutions.add(solution);
		} else {
			ArrayList<ArrayList<Integer>> possibleValues = solver.possibleValues;
			for (ArrayList<Integer> values : possibleValues) {
				if (values.size() > 3) {
					for (int i = 2; i < values.size(); i++) {
						solution.get(values.get(0))[values.get(1)] = values.get(i);
						attemptMultipleSolutions(solution);
					}
					break;
				}
			}
		}
		return allSolutions;
	}

	// Checks for the validity of solution
	private boolean isSolved(ArrayList<Integer[]> solution) {
		// verifies if any of the cell is still empty
		for (Integer[] row : solution) {
			for (int i = 0; i < 9; i++) {
				if (row[i] == 0)
					return false;
			}
		}

		// discards incorrect solution
		if (new SudokuHelper(solution).isInvalid())
			return false;

		return true;
	}
}
