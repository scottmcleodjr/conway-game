package conway;

import java.util.Random;

public class Generation {

	private byte[][] currentGeneration;
	private byte[][] nextGeneration;
	private int rows;
	private int cols;
	private int density = 12; // Percentage of cells live in random start

	public Generation(int rows, int cols, StartSeedStyle style) {
		this.rows = rows;
		this.cols = cols;
		setFirstGeneration(style);
	}

	public byte[][] getCurrentGeneration() {
		return currentGeneration;
	}

	public void advangeGeneration() {
		currentGeneration = nextGeneration;
		nextGeneration = createNextGeneration(currentGeneration);
	}

	public void setFirstGeneration(StartSeedStyle style) {
		switch (style) {
			case RANDOM:
				currentGeneration = makeRandomSeed(rows, cols, density);
				break;
			case HORIZONTAL_LINE:
				currentGeneration = makeHorizontalLineSeed(rows, cols);
				break;
			case BOX_LINE:
				currentGeneration = makeBoxLineSeed(rows, cols);
				break;
		}
		nextGeneration = createNextGeneration(currentGeneration);
	}

	/*
	 * Private static methods below this comment.
	 */

	private static byte[][] makeRandomSeed(int rows, int cols, int density) {
		Random randy = new Random();
		byte [][] output = new byte[rows][cols];
		//This loop truncates to not possibly make a border value 1
		for (int r = 1; r < rows - 1; r++) {
			for (int c = 1; c < cols - 1; c ++) {
				if (randy.nextInt(100) < density) {
					output[r][c] = 1;
				}
			}
		}
		return output;
	}

	private static byte[][] makeHorizontalLineSeed(int rows, int cols) {
		byte [][] output = new byte[rows][cols];
		int mid = rows / 2; // Close enough to an actual center.
		//This loop truncates to not possibly make a border value 1
		for (int r = 1; r < rows - 1; r++) {
			for (int c = 1; c < cols - 1; c ++) {
				if (r == mid) {
					output[r][c] = 1;
				}
			}
		}
		return output;
	}

	private static byte[][] makeBoxLineSeed(int rows, int cols) {
		byte [][] output = new byte[rows][cols];
		// Determine where the box lines appear on screen
		int horizontalTop = (rows - 2) / 3; // -2 so it is based on screen size.
		int horizontalBottom = horizontalTop * 2;
		int verticalLeft = (cols - 2) / 4; // /4 here because screen is wider than tall
		int verticalRight = verticalLeft * 3;
		//This loop truncates to not possibly make a border value 1
		for (int r = 1; r < rows - 1; r++) {
			for (int c = 1; c < cols - 1; c ++) {
				// Add each line to the image
				if ((r == horizontalTop) && ((c >= verticalLeft) && (c <= verticalRight))) {
					output[r][c] = 1;
				} else if ((r == horizontalBottom) && ((c >= verticalLeft) && (c <= verticalRight))) {
					output[r][c] = 1;
				} else if ((c == verticalLeft) && ((r >= horizontalTop) && (r <= horizontalBottom))) {
					output[r][c] = 1;
				} else if ((c == verticalRight) && ((r >= horizontalTop) && (r <= horizontalBottom))) {
					output[r][c] = 1;
				}
			}
		}
		return output;
	}

	private static byte[][] createNextGeneration(byte[][] input) {

		int count;
		byte[][] output = new byte[input.length][input[1].length];

		/*
		 * With 1 representing a live cell and 0 for dead:
		 * 		if 0 and adjacent to exactly 3, then 1
		 * 		if 1 and adjacent to <= 1,      then 0
		 * 		if 1 and adjacent to >=4,       then 0
		 * 		if 1 and (else),                then 1
		 */

		for (int r = 1; r < (input.length - 1); r++) {
			for (int c = 1; c < (input[1].length -1); c++) {
				count = 0;
				count += input[r-1][c-1];
				count += input[r-1][c];
				count += input[r-1][c+1];
				count += input[r][c-1];
				count += input[r][c+1];
				count += input[r+1][c-1];
				count += input[r+1][c];
				count += input[r+1][c+1];

				if (input[r][c] == 1) {
					if ((count <= 1) || (count >= 4)) {
						output[r][c] = 0;
					} else {
						output[r][c] = 1;
					}
				} else { //Occurs if input[r][c] == 0
					if (count == 3) {
						output[r][c] = 1;
					} else {
						output[r][c] = 0;
					}
				}
			}
		}
		return output;
	}
}
