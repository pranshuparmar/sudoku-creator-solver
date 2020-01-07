package com.pranshu.sudoku.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.pranshu.sudoku.helper.SudokuHelper;

/*
 * Creates Sudoku puzzle and returns as an ArrayList of Integer arrays
 */

public class SudokuCreator {

	public int ctr = 0;
	private final int MAX_LIMIT = 2500000;
	private ArrayList<Integer[]> puzzle;
	private int checkingNum;

	public ArrayList<Integer[]> createSudoku() {
		puzzle = new ArrayList<Integer[]>();
		puzzle.add(createRow());
		while (puzzle.size() != 9) {
			if (ctr > MAX_LIMIT)
				break;
			createRemainingRows();
		}

		if (ctr > MAX_LIMIT) { // if counter has crossed max limit then it is highly likely that created puzzle
								// is incorrect, need to recreate the puzzle
			ctr = 0;
			createSudoku();
		}

		return puzzle;
	}

	public ArrayList<Integer[]> createRemainingRows() {
		ArrayList<ArrayList<Integer>> allColumns = new SudokuHelper(puzzle).createColumns();
		boolean rowAdded = false;
		while (!rowAdded) {
			++ctr;
			if (ctr > MAX_LIMIT)
				break;
			Integer[] row = createRow();
			boolean rowToAdd = true;
			for (int i = 0; i < 9; i++) {
				if (row[i] != 0) {
					if (allColumns.get(i).contains(row[i])) {
						rowToAdd = false;
						break;
					}
					boolean isClear = checkBlock(i, row[i]);
					if (!isClear) {
						rowToAdd = false;
						break;
					}
				}
			}
			if (rowToAdd) {
				puzzle.add(row);
				rowAdded = true;
			}
		}
		return puzzle;
	}

	// Shuffles an integer array of size 9 with unique numbers from 1-9
	private Integer[] createRow() {
		Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		List<Integer> intList = Arrays.asList(intArray);
		Collections.shuffle(intList);
		return intList.toArray(intArray);
	}

	// Determines the current block of the number
	private boolean checkBlock(int yPos, int checkingNum) {
		int xPos = puzzle.size();
		this.checkingNum = checkingNum;
		if (!(xPos == 3 || xPos == 6)) {
			if (xPos < 3 && yPos < 3)
				return performBlockCheck(3, 3);
			else if (xPos < 3 && yPos < 6)
				return performBlockCheck(3, 6);
			else if (xPos < 3 && yPos < 9)
				return performBlockCheck(3, 9);
			else if (xPos < 6 && yPos < 3)
				return performBlockCheck(6, 3);
			else if (xPos < 6 && yPos < 6)
				return performBlockCheck(6, 6);
			else if (xPos < 6 && yPos < 9)
				return performBlockCheck(6, 9);
			else if (xPos < 9 && yPos < 3)
				return performBlockCheck(9, 3);
			else if (xPos < 9 && yPos < 6)
				return performBlockCheck(9, 6);
			else if (xPos < 9 && yPos < 9)
				return performBlockCheck(9, 9);
		}
		return true;
	}

	// Checks whether this number is already present in the block
	private boolean performBlockCheck(int xPos, int yPos) {
		for (int i = xPos - 3; i < xPos; i++) {
			try {
				Integer[] row = puzzle.get(i);
				for (int y = yPos - 3; y < yPos; y++) {
					if (row[y] == checkingNum) {
						return false;
					}
				}
			} catch (IndexOutOfBoundsException ex) {
			} // checking complete block irrespective of filled data
		}
		return true;
	}

}
