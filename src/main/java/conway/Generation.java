package conway;

import java.util.Random;

public class Generation {

  private byte[][] currentGeneration;
  private byte[][] nextGeneration;
  private int rows;
  private int cols;
  private int density = 12; // Percentage of cells live in random start

  public Generation(Config cfg) {
    this.rows = cfg.getCellsHigh();
    this.cols = cfg.getCellsWide();

    switch (cfg.getStyle()) {
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

  public byte getValueAt(int r, int c) {
    return currentGeneration[r][c];
  }

  public void advance() {
    currentGeneration = nextGeneration;
    setNextGenerationPerCurrentGeneration();
  }

  private void setCurrentGenerationToRandomPattern() {
    Random randy = new Random();
    currentGeneration = new byte[rows][cols];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (randy.nextInt(100) < density) {
          currentGeneration[r][c] = 1;
        }
      }
    }
  }

  private void setCurrentGenerationToHorizontalLine() {
    currentGeneration = new byte[rows][cols];
    int mid = rows / 2;
    for (int c = 0; c < cols; c++) {
      currentGeneration[mid][c] = 1;
    }
  }

  private void setCurrentGenerationToBoxLine() {
    currentGeneration = new byte[rows][cols];
    int horizontalTop = rows / 3;
    int horizontalBottom = horizontalTop * 2;
    int verticalLeft = cols / 4; // /4 looks a lot better than 3 here
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
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        int count = 0;
        int prev_row = r == 0 ? rows - 1 : r - 1;
        int next_row = r == rows - 1 ? 0 : r + 1;
        int prev_col = c == 0 ? cols - 1 : c - 1;
        int next_col = c == cols - 1 ? 0 : c + 1;
        count += currentGeneration[prev_row][prev_col];
        count += currentGeneration[prev_row][c];
        count += currentGeneration[prev_row][next_col];
        count += currentGeneration[r][prev_col];
        count += currentGeneration[r][next_col];
        count += currentGeneration[next_row][prev_col];
        count += currentGeneration[next_row][c];
        count += currentGeneration[next_row][next_col];

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
