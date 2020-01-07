package com.pranshu.sudoku.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pranshu.sudoku.helper.SudokuHelper;

/*
 * Attempts to solve Sudoku puzzle!
 * 
 * NOTE -> empty cell = 0 value cell
 */

public class SudokuSolver {

	private ArrayList<Integer[]> solvedPuzzle = new ArrayList<Integer[]>();
	private ArrayList<ArrayList<Integer>> allColumns;
	private ArrayList<ArrayList<Integer>> allBlocks;
	private ArrayList<ArrayList<Integer>> possibleValues;
	private ArrayList<ArrayList<Integer>> prevPossibleValues;
	private SudokuHelper helper = new SudokuHelper(solvedPuzzle);
	public int ctr = 0;
	private boolean isUnique = true;

	public ArrayList<Integer[]> solveSudoku(ArrayList<Integer[]> puzzle) {
		copyPuzzle(puzzle);
		boolean isUnsolved = true;
		while (isUnsolved) {
			++ctr;
			helper = new SudokuHelper(solvedPuzzle);
			allColumns = helper.createColumns();
			allBlocks = helper.createBlocks();
			prevPossibleValues = possibleValues;
			findPossiblevalues();
			if (prevPossibleValues != null && prevPossibleValues.equals(possibleValues)) {
				// System.err.println("Puzzle has multiple solutions, breaking loop!");
				isUnique = false;
				break;
			}

			updateSolution();

			if (isSolved()) {
				isUnsolved = false;
			}
		}
		return solvedPuzzle;
	}

	// Copies the puzzle into a new ArrayList so that solution is not modified
	private void copyPuzzle(ArrayList<Integer[]> puzzle) {
		for (int i = 0; i < 9; i++) {
			Integer[] row = new Integer[9];
			for (int j = 0; j < 9; j++) {
				row[j] = puzzle.get(i)[j];
			}
			solvedPuzzle.add(row);
		}
	}

	// Returns all possible values on all empty cells
	private void findPossiblevalues() {
		possibleValues = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				ArrayList<Integer> values = new ArrayList<Integer>();
				int data = solvedPuzzle.get(i)[j];
				if (data != 0) {
					values.add(data);
				} else {
					ArrayList<Integer> rValues = checkRow(i);
					ArrayList<Integer> cValues = checkColumn(j);
					ArrayList<Integer> bValues = checkBlock(i, j);

					rValues.retainAll(cValues);
					rValues.retainAll(bValues);
					values = rValues; // will retain only common values
				}
				possibleValues.add(values);
			}
		}
	}

	// Finds all possible values for all empty cells in the row
	private ArrayList<Integer> checkRow(int rNum) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		List<Integer> row = Arrays.asList(solvedPuzzle.get(rNum));
		for (int i = 1; i <= 9; i++) {
			if (!row.contains(i))
				values.add(i);
		}
		return values;
	}

	// Finds all possible values for all empty cells in the column
	private ArrayList<Integer> checkColumn(int cNum) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		List<Integer> column = allColumns.get(cNum);
		for (int i = 1; i <= 9; i++) {
			if (!column.contains(i))
				values.add(i);
		}
		return values;
	}

	// Identifies block number based of row and column number
	private ArrayList<Integer> checkBlock(int rNum, int cNum) {
		int blockNum = -1;
		if (rNum < 3 && cNum < 3)
			blockNum = 0;
		else if (rNum < 3 && cNum < 6)
			blockNum = 1;
		else if (rNum < 3 && cNum < 9)
			blockNum = 2;
		else if (rNum < 6 && cNum < 3)
			blockNum = 3;
		else if (rNum < 6 && cNum < 6)
			blockNum = 4;
		else if (rNum < 6 && cNum < 9)
			blockNum = 5;
		else if (rNum < 9 && cNum < 3)
			blockNum = 6;
		else if (rNum < 9 && cNum < 6)
			blockNum = 7;
		else if (rNum < 9 && cNum < 9)
			blockNum = 8;
		return checkBlockWithNum(blockNum);
	}

	// Finds all possible values for all empty cells in the block
	private ArrayList<Integer> checkBlockWithNum(int blockNum) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		List<Integer> block = allBlocks.get(blockNum);
		for (int i = 1; i <= 9; i++) {
			if (!block.contains(i))
				values.add(i);
		}
		return values;
	}

	// Updates the solution for cells where there is only one possible value
	private void updateSolution() {
		int dataNum = -1;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				++dataNum;
				if (solvedPuzzle.get(i)[j] == 0 && possibleValues.get(dataNum).size() == 1) {
					solvedPuzzle.get(i)[j] = possibleValues.get(dataNum).get(0);
				}
			}
		}
	}

	// Verifies if any of the cell is still empty
	private boolean isSolved() {
		for (Integer[] row : solvedPuzzle) {
			for (int i = 0; i < 9; i++) {
				if (row[i] == 0)
					return false;
			}
		}
		return true;
	}

	// Returns whether the puzzle has single or multiple solutions
	public boolean isUniqueSolution(ArrayList<Integer[]> puzzle) {
		solveSudoku(puzzle);
		return isUnique;
	}
}
