package conway;

import java.util.Random;

public class Generation {

  private byte[][] currentState;
  private byte[][] nextState;
  private int cellsHigh;
  private int cellsWide;

  public Generation(Config cfg) {
    this.cellsHigh = cfg.getCellsHigh();
    this.cellsWide = cfg.getCellsWide();

    switch (cfg.getStyle()) {
      case RANDOM:
        setCurrentStateToRandomPattern();
        break;
      case HORIZONTAL_LINE:
        setCurrentStateToHorizontalLine();
        break;
      case BOX_LINE:
        setCurrentStateToBox();
        break;
    }
    updateNextState();
  }

  public byte getValueAt(int r, int c) {
    return currentState[r][c];
  }

  public void advance() {
    currentState = nextState;
    updateNextState();
  }

  private void setCurrentStateToRandomPattern() {
    Random randy = new Random();
    currentState = new byte[cellsHigh][cellsWide];
    for (int r = 0; r < cellsHigh; r++) {
      for (int c = 0; c < cellsWide; c++) {
        if (randy.nextInt(100) < Config.RANDOM_SEED_INIT_DENSITY * 100) {
          currentState[r][c] = 1;
        }
      }
    }
  }

  private void setCurrentStateToHorizontalLine() {
    currentState = new byte[cellsHigh][cellsWide];
    int mid = cellsHigh / 2;
    for (int c = 0; c < cellsWide; c++) {
      currentState[mid][c] = 1;
    }
  }

  private void setCurrentStateToBox() {
    currentState = new byte[cellsHigh][cellsWide];
    int horizontalTop = cellsHigh / 3;
    int horizontalBottom = horizontalTop * 2;
    int verticalLeft = cellsWide / 4; // /4 looks a lot better than 3 here
    int verticalRight = verticalLeft * 3;

    for (int r = horizontalTop; r <= horizontalBottom; r++) {
      currentState[r][verticalLeft] = 1;
      currentState[r][verticalRight] = 1;
    }
    for (int c = verticalLeft; c <= verticalRight; c++) {
      currentState[horizontalTop][c] = 1;
      currentState[horizontalBottom][c] = 1;
    }
  }

  private void updateNextState() {
    nextState = new byte[cellsHigh][cellsWide];
    /*
     * With 1 representing a live cell and 0 for dead:
     *   If 0 and adjacent to exactly 3 live, then 1
     *   If 1 and adjacent to 2 or 3 live, then 1
     *   If 1 and adjacent to (else), then 0
     */
    for (int r = 0; r < cellsHigh; r++) {
      for (int c = 0; c < cellsWide; c++) {
        int count = 0;
        int prev_row = r == 0 ? cellsHigh - 1 : r - 1;
        int next_row = r == cellsHigh - 1 ? 0 : r + 1;
        int prev_col = c == 0 ? cellsWide - 1 : c - 1;
        int next_col = c == cellsWide - 1 ? 0 : c + 1;
        count += currentState[prev_row][prev_col];
        count += currentState[prev_row][c];
        count += currentState[prev_row][next_col];
        count += currentState[r][prev_col];
        count += currentState[r][next_col];
        count += currentState[next_row][prev_col];
        count += currentState[next_row][c];
        count += currentState[next_row][next_col];

        if (currentState[r][c] == 1) {
          if (count == 2 || count == 3) {
            nextState[r][c] = 1;
          }
        } else if (count == 3) { // Current cell is dead
          nextState[r][c] = 1;
        }
      }
    }
  }
}
