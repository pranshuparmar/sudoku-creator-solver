package com.pranshu.sudoku.util;

import java.util.ArrayList;
import java.util.Random;

/*
 * Hides random elements from every block of the puzzle and returns it
 */

public class SudokuObfuscator {

	private Random random = new Random();
	private ArrayList<Integer[]> obfuscatedPuzzle;
	public int ctr = 0;

	// This method randomly hides numbers from the puzzle but doesn't guarantee a
	// well-formed sudoku with unique solution
	public ArrayList<Integer[]> obfuscateSudoku(ArrayList<Integer[]> puzzle) {
		++ctr;
		obfuscatedPuzzle = new ArrayList<Integer[]>();
		for (int i = 0; i < 9; i++) {
			Integer[] row = new Integer[9];
			for (int j = 0; j < 9; j++) {
				if (random.nextInt(2) == 1) {
					row[j] = 0;
				} else {
					row[j] = puzzle.get(i)[j];
				}
			}
			obfuscatedPuzzle.add(row);
		}
		return obfuscatedPuzzle;
	}

	// This method will call SudokuSolver to discard the obfuscatedPuzzle if it has
	// multiple solutions thus returning only a well-formed sudoku
	public ArrayList<Integer[]> obfuscateUniqueSudoku(ArrayList<Integer[]> puzzle) {
		obfuscatedPuzzle = obfuscateSudoku(puzzle);
		while (!new SudokuSolver().isUniqueSolution(obfuscatedPuzzle)) {
			obfuscatedPuzzle = obfuscateSudoku(puzzle);
		}
		return obfuscatedPuzzle;
	}
}
