package com.pranshu.sudoku.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class SudokuHelper {

	private ArrayList<Integer[]> puzzle;
	private ArrayList<ArrayList<Integer>> allColumns = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> allBlocks = new ArrayList<ArrayList<Integer>>();

	public SudokuHelper(ArrayList<Integer[]> puzzle) {
		this.puzzle = puzzle;
	}

	// Returns a list of all columns for the puzzle
	public ArrayList<ArrayList<Integer>> createColumns() {
		Iterator<Integer[]> itr = puzzle.iterator();
		ArrayList<Integer> column1 = new ArrayList<Integer>();
		ArrayList<Integer> column2 = new ArrayList<Integer>();
		ArrayList<Integer> column3 = new ArrayList<Integer>();
		ArrayList<Integer> column4 = new ArrayList<Integer>();
		ArrayList<Integer> column5 = new ArrayList<Integer>();
		ArrayList<Integer> column6 = new ArrayList<Integer>();
		ArrayList<Integer> column7 = new ArrayList<Integer>();
		ArrayList<Integer> column8 = new ArrayList<Integer>();
		ArrayList<Integer> column9 = new ArrayList<Integer>();

		while (itr.hasNext()) {
			Integer[] currRow = itr.next();
			column1.add(currRow[0]);
			column2.add(currRow[1]);
			column3.add(currRow[2]);
			column4.add(currRow[3]);
			column5.add(currRow[4]);
			column6.add(currRow[5]);
			column7.add(currRow[6]);
			column8.add(currRow[7]);
			column9.add(currRow[8]);
		}
		allColumns.add(column1);
		allColumns.add(column2);
		allColumns.add(column3);
		allColumns.add(column4);
		allColumns.add(column5);
		allColumns.add(column6);
		allColumns.add(column7);
		allColumns.add(column8);
		allColumns.add(column9);

		return allColumns;
	}

	// Returns a list of all blocks of the puzzle
	public ArrayList<ArrayList<Integer>> createBlocks() {
		ArrayList<Integer> block1 = new ArrayList<Integer>();
		ArrayList<Integer> block2 = new ArrayList<Integer>();
		ArrayList<Integer> block3 = new ArrayList<Integer>();
		ArrayList<Integer> block4 = new ArrayList<Integer>();
		ArrayList<Integer> block5 = new ArrayList<Integer>();
		ArrayList<Integer> block6 = new ArrayList<Integer>();
		ArrayList<Integer> block7 = new ArrayList<Integer>();
		ArrayList<Integer> block8 = new ArrayList<Integer>();
		ArrayList<Integer> block9 = new ArrayList<Integer>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				block1.add(puzzle.get(i)[j]);
			}
			for (int j = 3; j < 6; j++) {
				block2.add(puzzle.get(i)[j]);
			}
			for (int j = 6; j < 9; j++) {
				block3.add(puzzle.get(i)[j]);
			}
		}
		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				block4.add(puzzle.get(i)[j]);
			}
			for (int j = 3; j < 6; j++) {
				block5.add(puzzle.get(i)[j]);
			}
			for (int j = 6; j < 9; j++) {
				block6.add(puzzle.get(i)[j]);
			}
		}
		for (int i = 6; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				block7.add(puzzle.get(i)[j]);
			}
			for (int j = 3; j < 6; j++) {
				block8.add(puzzle.get(i)[j]);
			}
			for (int j = 6; j < 9; j++) {
				block9.add(puzzle.get(i)[j]);
			}
		}

		allBlocks.add(block1);
		allBlocks.add(block2);
		allBlocks.add(block3);
		allBlocks.add(block4);
		allBlocks.add(block5);
		allBlocks.add(block6);
		allBlocks.add(block7);
		allBlocks.add(block8);
		allBlocks.add(block9);
		return allBlocks;
	}

	// Returns whether a sudoku is invalid
	public boolean isInvalid() {
		// checking for blocks
		createBlocks();
		for (ArrayList<Integer> block : allBlocks) {
			if (Arrays.stream(block.toArray()).distinct().toArray().length < 9) {
				return true;
			}
		}

		// checking for columns
		createColumns();
		for (ArrayList<Integer> column : allColumns) {
			if (Arrays.stream(column.toArray()).distinct().toArray().length < 9) {
				return true;
			}
		}

		// checking for rows
		for (Integer[] row : puzzle) {
			if (Arrays.stream(row).distinct().toArray().length < 9) {
				return true;
			}
		}

		return false;
	}
}
