package conway;

import java.util.Random;

public class Generation {

  private byte[][] state;
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
      case BOX:
        setCurrentStateToBox();
        break;
    }
  }

  private Generation(byte[][] state, int cellsHigh, int cellsWide) {
    this.state = state;
    this.cellsHigh = cellsHigh;
    this.cellsWide = cellsWide;
  }

  public byte getValueAt(int r, int c) {
    return state[r][c];
  }

  public Generation getNextGeneration() {
    byte[][] nextState = new byte[cellsHigh][cellsWide];
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
        count += state[prev_row][prev_col];
        count += state[prev_row][c];
        count += state[prev_row][next_col];
        count += state[r][prev_col];
        count += state[r][next_col];
        count += state[next_row][prev_col];
        count += state[next_row][c];
        count += state[next_row][next_col];

        if (state[r][c] == 1) {
          if (count == 2 || count == 3) {
            nextState[r][c] = 1;
          }
        } else if (count == 3) { // Current cell is dead
          nextState[r][c] = 1;
        }
      }
    }
    return new Generation(nextState, cellsHigh, cellsWide);
  }

  private void setCurrentStateToRandomPattern() {
    Random randy = new Random();
    state = new byte[cellsHigh][cellsWide];
    for (int r = 0; r < cellsHigh; r++) {
      for (int c = 0; c < cellsWide; c++) {
        if (randy.nextInt(100) < Config.RANDOM_SEED_INIT_DENSITY * 100) {
          state[r][c] = 1;
        }
      }
    }
  }

  private void setCurrentStateToHorizontalLine() {
    state = new byte[cellsHigh][cellsWide];
    int mid = cellsHigh / 2;
    for (int c = 0; c < cellsWide; c++) {
      state[mid][c] = 1;
    }
  }

  private void setCurrentStateToBox() {
    state = new byte[cellsHigh][cellsWide];
    int horizontalTop = cellsHigh / 3;
    int horizontalBottom = horizontalTop * 2;
    int verticalLeft = cellsWide / 4; // /4 looks a lot better than 3 here
    int verticalRight = verticalLeft * 3;

    for (int r = horizontalTop; r <= horizontalBottom; r++) {
      state[r][verticalLeft] = 1;
      state[r][verticalRight] = 1;
    }
    for (int c = verticalLeft; c <= verticalRight; c++) {
      state[horizontalTop][c] = 1;
      state[horizontalBottom][c] = 1;
    }
  }
}
