import java.util.*;
// import edu.princeton.ex.algs4.Queue;
// import edu.princeton.ex.algs4.StdIn;
// import edu.princeton.ex.algs4.StdOut;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
	private TrieSET validDictionary;
	// public boolean[][] markedindices;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)

	/**
	 * Constructs the object.
	 *
	 * @param      dictionary  The dictionary
	 */
	public BoggleSolver(String[] dictionary) {
		this.validDictionary = new TrieSET();
		for (String str : dictionary) {
			this.validDictionary.add(str);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.

	/**
	 * Gets all valid words.
	 *
	 * @param      board  The board
	 *
	 * @return     All valid words.
	 */
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		Set<String> validWords = new HashSet<String>();
		if (board == null) {
			throw new NullPointerException("board is null");
		}
		String initialStr = "";
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] markedindices = new boolean[board.rows()][board.cols()];
				helperWordsFinder(board, i, j, markedindices, initialStr, validWords);
			}
		}
		return validWords;
	}

	//This is a helper function to store all the possible and valid words from the given 4x4 BoggleBoard.
	// This helperWordsFinder will locate all the characters in the BoggleBoard that are adjacent to the
	// character that was passed to this function from getAllValidWords function.
	/**
	 * Collect Words.
	 *
	 * @param      board   The board
	 * @param      row     The row
	 * @param      col     The col
	 * @param      marked  The marked
	 * @param      prefix  The prefix
	 * @param      set     The set
	 */
	public void helperWordsFinder(BoggleBoard board, int row, int col, boolean[][]marked, String prefix, Set<String> set) {
		if (marked[row][col]) {
			return;
		}

		char letter = board.getLetter(row, col);
		String word = prefix;

		if (letter == 'Q') {
			word += "QU";
		} else {
			word += letter;
		}

		if (!validDictionary.hasPrefix(word)) {
			return;
		}

		if (word.length() > 2 && validDictionary.contains(word)) {
			set.add(word);
		}

		marked[row][col] = true;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}

				if ((row + i >= 0) && (row + i < board.rows()) && (col + j >= 0) && (col + j < board.cols())) {
					helperWordsFinder(board, row + i, col + j, marked, word, set);
				}
			}
		}
		marked[row][col] = false;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (validDictionary.contains(word)) {
			switch (word.length()) {
			case 0:
			case 1:
			case 2:
				return 0;
			case 3:
			case 4:
				return 1;
			case 5:
				return 2;
			case 6:
				return 3;
			case 7:
				return 5;
			default:
				return 11;
			}
		} else {
			return 0;
		}
	}
}
