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
      case CROSS:
        setCurrentStateToCross();
        break;
      case GLIDERS:
        setCurrentStateToGliders();
        break;
    }
  }

  private Generation(byte[][] state, int cellsHigh, int cellsWide) {
    this.state = state;
    this.cellsHigh = cellsHigh;
    this.cellsWide = cellsWide;
  }

  public byte getValueAt(int y, int x) {
    return state[y][x];
  }

  public Generation getNextGeneration() {
    byte[][] nextState = new byte[cellsHigh][cellsWide];
    /*
     * With 1 representing a live cell and 0 for dead:
     *   If 0 and adjacent to exactly 3 live, then 1
     *   If 1 and adjacent to 2 or 3 live, then 1
     *   If 1 and adjacent to (else), then 0
     */
    for (int y = 0; y < cellsHigh; y++) {
      for (int x = 0; x < cellsWide; x++) {
        int count = 0;
        int prev_row = y == 0 ? cellsHigh - 1 : y - 1;
        int next_row = y == cellsHigh - 1 ? 0 : y + 1;
        int prev_col = x == 0 ? cellsWide - 1 : x - 1;
        int next_col = x == cellsWide - 1 ? 0 : x + 1;
        count += state[prev_row][prev_col];
        count += state[prev_row][x];
        count += state[prev_row][next_col];
        count += state[y][prev_col];
        count += state[y][next_col];
        count += state[next_row][prev_col];
        count += state[next_row][x];
        count += state[next_row][next_col];

        if (state[y][x] == 1) {
          if (count == 2 || count == 3) {
            nextState[y][x] = 1;
          }
        } else if (count == 3) { // Current cell is dead
          nextState[y][x] = 1;
        }
      }
    }
    return new Generation(nextState, cellsHigh, cellsWide);
  }

  private void setCurrentStateToRandomPattern() {
    Random randy = new Random();
    state = new byte[cellsHigh][cellsWide];
    for (int y = 0; y < cellsHigh; y++) {
      for (int x = 0; x < cellsWide; x++) {
        if (randy.nextInt(100) < Config.RANDOM_SEED_INIT_DENSITY * 100) {
          state[y][x] = 1;
        }
      }
    }
  }

  private void setCurrentStateToHorizontalLine() {
    state = new byte[cellsHigh][cellsWide];
    int mid = cellsHigh / 2;
    for (int x = 0; x < cellsWide; x++) {
      state[mid][x] = 1;
    }
  }

  private void setCurrentStateToBox() {
    state = new byte[cellsHigh][cellsWide];
    int horizontalTop = cellsHigh / 3;
    int horizontalBottom = horizontalTop * 2;
    int verticalLeft = cellsWide / 4; // /4 looks a lot better than 3 here
    int verticalRight = verticalLeft * 3;

    for (int y = horizontalTop; y <= horizontalBottom; y++) {
      state[y][verticalLeft] = 1;
      state[y][verticalRight] = 1;
    }
    for (int x = verticalLeft; x <= verticalRight; x++) {
      state[horizontalTop][x] = 1;
      state[horizontalBottom][x] = 1;
    }
  }

  private void setCurrentStateToCross() {
    state = new byte[cellsHigh][cellsWide];

    int yMid = cellsHigh / 2;
    int horizontalBuffer = (int) (cellsWide * 0.05);
    for (int x = horizontalBuffer; x < cellsWide - horizontalBuffer; x++) {
      state[yMid][x] = 1;
    }

    int xMid = cellsWide / 2;
    int verticalBuffer = (int) (cellsHigh * 0.05);
    for (int y = verticalBuffer; y < cellsHigh - verticalBuffer; y++) {
      state[y][xMid] = 1;
    }
  }

  private void setCurrentStateToGliders() {
    state = new byte[cellsHigh][cellsWide];
    int gliderSpacing = 30; // Seems to look nice.
    for (int y = 0; y < cellsHigh - gliderSpacing; y += gliderSpacing) {
      for (int x = 0; x < cellsWide - gliderSpacing; x += gliderSpacing) {
        /* The glider:
           0 1 0
           0 0 1
           1 1 1 */
        state[y][x+1] = 1;
        state[y+1][x+2] = 1;
        state[y+2][x] = 1;
        state[y+2][x+1] = 1;
        state[y+2][x+2] = 1;
      }
    }
  }
}
