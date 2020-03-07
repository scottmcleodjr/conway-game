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

    switch (style) {
      case RANDOM:
        setCurrentGenerationToRandomPattern();
        break;
      case HORIZONTAL_LINE:
        setCurrentGenerationToHorizontalLine();
        break;
      case BOX_LINE:
        setCurrentGenerationToBoxLine();
        break;
    }
    setNextGenerationPerCurrentGeneration();
  }

  public byte[][] getCurrentGeneration() {
    return currentGeneration;
  }

  public void advangeGeneration() {
    currentGeneration = nextGeneration;
    setNextGenerationPerCurrentGeneration();
  }

  private void setCurrentGenerationToRandomPattern() {
    Random randy = new Random();
    currentGeneration = new byte[rows][cols];
    // This loop truncates to not possibly make a border value 1
    for (int r = 1; r < rows - 1; r++) {
      for (int c = 1; c < cols - 1; c++) {
        if (randy.nextInt(100) < density) {
          currentGeneration[r][c] = 1;
        }
      }
    }
  }

  private void setCurrentGenerationToHorizontalLine() {
    currentGeneration = new byte[rows][cols];
    int mid = rows / 2;
    // This loop truncates to not possibly make a border value 1
    for (int c = 1; c < cols - 1; c++) {
      currentGeneration[mid][c] = 1;
    }
  }

  private void setCurrentGenerationToBoxLine() {
    currentGeneration = new byte[rows][cols];
    int horizontalTop = (rows - 2) / 3; // -2 so it is based on screen size.
    int horizontalBottom = horizontalTop * 2;
    int verticalLeft = (cols - 2) / 4; // /4 looks a lot better than 3 here
    int verticalRight = verticalLeft * 3;

    for (int r = horizontalTop; r <= horizontalBottom; r++) {
      currentGeneration[r][verticalLeft] = 1;
      currentGeneration[r][verticalRight] = 1;
    }
    for (int c = verticalLeft; c <= verticalRight; c++) {
      currentGeneration[horizontalTop][c] = 1;
      currentGeneration[horizontalBottom][c] = 1;
    }
  }

  private void setNextGenerationPerCurrentGeneration() {
    nextGeneration = new byte[rows][cols];
    /*
     * With 1 representing a live cell and 0 for dead:
     *   If 0 and adjacent to exactly 3 live, then 1
     *   If 1 and adjacent to 2 or 3 live, then 1
     *   If 1 and adjacent to (else), then 0
     */
    for (int r = 1; r < rows - 1; r++) {
      for (int c = 1; c < cols - 1; c++) {
        int count = 0;
        count += currentGeneration[r - 1][c - 1];
        count += currentGeneration[r - 1][c];
        count += currentGeneration[r - 1][c + 1];
        count += currentGeneration[r][c - 1];
        count += currentGeneration[r][c + 1];
        count += currentGeneration[r + 1][c - 1];
        count += currentGeneration[r + 1][c];
        count += currentGeneration[r + 1][c + 1];

        if (currentGeneration[r][c] == 1) {
          if (count == 2 || count == 3) {
            nextGeneration[r][c] = 1;
          }
        } else if (count == 3) { // Current cell is dead
          nextGeneration[r][c] = 1;
        }
      }
    }
  }
}
