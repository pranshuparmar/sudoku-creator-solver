package com.pranshu.sudoku.helper;

import java.util.ArrayList;

import com.pranshu.sudoku.util.SudokuSolver;

public class MultiSolutionHelper {

	private static ArrayList<ArrayList<Integer[]>> allSolutions = new ArrayList<ArrayList<Integer[]>>();

	public ArrayList<ArrayList<Integer[]>> attemptMultipleSolutions(ArrayList<Integer[]> origPuzzle) {
		solveSudoku(origPuzzle);
		discardIncorrectSolution();

		return allSolutions;
	}

	private void solveSudoku(ArrayList<Integer[]> puzzle) {
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
						solveSudoku(solution);
					}
					break;
				}
			}
		}
	}

	// Verifies if any of the cell is still empty
	private boolean isSolved(ArrayList<Integer[]> solution) {
		for (Integer[] row : solution) {
			for (int i = 0; i < 9; i++) {
				if (row[i] == 0)
					return false;
			}
		}
		return true;
	}

	/*
	 * Discards incorrect solutions. SudokuSolver is working fine but there is issue
	 * in multi solution logic, this method should not be required. Regardless, all
	 * solutions are being found.
	 */
	private void discardIncorrectSolution() {
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for (int i = allSolutions.size() - 1; i >= 0; i--) {
			if (new SudokuHelper(allSolutions.get(i)).isInvalid())
				toRemove.add(i);
		}
		for (Integer i : toRemove) {
			allSolutions.remove((int) i);
		}
	}
}
