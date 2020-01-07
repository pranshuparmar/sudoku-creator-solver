package com.pranshu.sudoku;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import com.pranshu.sudoku.util.SudokuCreator;
import com.pranshu.sudoku.util.SudokuObfuscator;
import com.pranshu.sudoku.util.SudokuSolver;

/**
 * Hello world! This is a Sudoku puzzle creator and solver
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("Here's the sudoku puzzle:\n");

		SudokuCreator creator = new SudokuCreator();
		LocalDateTime creationStartTime = LocalDateTime.now();
		ArrayList<Integer[]> puzzle = creator.createSudoku();
		LocalDateTime creationEndTime = LocalDateTime.now();

		System.out.println("Puzzle creation took " + ChronoUnit.MILLIS.between(creationStartTime, creationEndTime)
				+ " millisecond(s) and " + creator.ctr + " iteration(s).\n");

		SudokuObfuscator obf = new SudokuObfuscator();
		LocalDateTime obfuscatinStartTime = LocalDateTime.now();
		// ArrayList<Integer[]> obfuscatedPuzzle = obf.obfuscateSudoku(puzzle); // might
		// return puzzle with multiple solutions
		ArrayList<Integer[]> obfuscatedPuzzle = obf.obfuscateUniqueSudoku(puzzle);
		LocalDateTime obfuscatinEndTime = LocalDateTime.now();

		System.out
				.println("Puzzle obfuscation took " + ChronoUnit.MILLIS.between(obfuscatinStartTime, obfuscatinEndTime)
						+ " millisecond(s) and " + obf.ctr + " iteration(s).\n");
		printPuzzle(obfuscatedPuzzle);

		System.out.println("\nHere's the correct solution to the puzzle:\n");
		printPuzzle(puzzle);

		SudokuSolver sudokuSolver = new SudokuSolver();
		LocalDateTime solutionStartTime = LocalDateTime.now();
		ArrayList<Integer[]> solvedPuzzle = sudokuSolver.solveSudoku(obfuscatedPuzzle);
		LocalDateTime solutionEndTime = LocalDateTime.now();

		System.out.println("\nHere's the attempted solution to the puzzle:\n");
		printPuzzle(solvedPuzzle);
		System.out.println("Puzzle solution took " + ChronoUnit.MILLIS.between(solutionStartTime, solutionEndTime)
				+ " millisecond(s) and " + sudokuSolver.ctr + " iteration(s).\n");

		if (isSolutionCorrect(puzzle, solvedPuzzle)) {
			System.out.println("\nAttempted solution is correct!\n");
		} else {
			System.err.println("\nAttempted solution is incorrect!\n");
		}
	}

	// Prints the puzzle
	private static void printPuzzle(ArrayList<Integer[]> puzzle) {
		Iterator<Integer[]> itr = puzzle.iterator();
		int row = 1;
		while (itr.hasNext()) {
			int cols = 1;
			for (Integer i : itr.next()) {
				if (cols++ % 3 == 0)
					System.out.print(i + "\t");
				else
					System.out.print(i + " ");
			}
			if (row++ % 3 == 0)
				System.out.println("\n");
			else
				System.out.println();
		}
	}

	// Checks if the attempted solution matches the actual solution
	private static boolean isSolutionCorrect(ArrayList<Integer[]> puzzle, ArrayList<Integer[]> solvedPuzzle) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle.get(i)[j] != solvedPuzzle.get(i)[j])
					return false;
			}
		}
		return true;
	}
}
