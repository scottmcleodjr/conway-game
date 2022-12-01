package conway;

import java.awt.Color;
import java.awt.Toolkit;

public class Config {

  public static final Color DEFAULT_LIVE_COLOR = new Color(98, 239, 131); // Nice green color
  public static final int MIN_GEN_LENGTH_MILLIS = 50;
  public static final int MAX_GEN_LENGTH_MILLIS = 1000;
  public static final int DEFAULT_GEN_LENGTH_MILLIS = 100;
  public static final float RANDOM_SEED_INIT_DENSITY = 0.12f;
  private Color liveColor;
  private GameSize size;
  private int cellsWide;
  private int cellsHigh;
  private int genLengthMillis;
  private StartSeedStyle style;

  public Config(Color liveColor, GameSize size, int genLengthMillis, StartSeedStyle style) {
    this.liveColor = liveColor;

    this.size = size;
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    // Fill most of the screen if not full screen
    switch (this.size) {
      case FULL_SCREEN:
        cellsWide = screenWidth;
        cellsHigh = screenHeight;
        break;
      case LARGE:
        cellsWide = (int) (screenWidth * 0.8);
        cellsHigh = (int) (screenHeight * 0.8);
        break;
      case MEDIUM:
        cellsWide = (int) (screenWidth * 0.6);
        cellsHigh = (int) (screenHeight * 0.6);
        break;
      case SMALL:
        cellsWide = (int) (screenWidth * 0.4);
        cellsHigh = (int) (screenHeight * 0.4);
        break;
    }

    if (!genLengthArgumentInRange(genLengthMillis)) {
      throw new IllegalArgumentException(
          String.format("genLengthMillis must be between %d and %d",
              MIN_GEN_LENGTH_MILLIS,
              MAX_GEN_LENGTH_MILLIS));
    }
    this.genLengthMillis = genLengthMillis;

    this.style = style;
  }

  public Color getLiveColor() {
    return liveColor;
  }

  public boolean isFullScreen() {
    return size == GameSize.FULL_SCREEN;
  }

  public int getCellsWide() {
    return cellsWide;
  }

  public int getCellsHigh() {
    return cellsHigh;
  }

  public int getGenLengthMillis() {
    return genLengthMillis;
  }

  public StartSeedStyle getStyle() {
    return style;
  }

  private boolean genLengthArgumentInRange(int genLengthMillis) {
    return genLengthMillis >= MIN_GEN_LENGTH_MILLIS
        || genLengthMillis <= MAX_GEN_LENGTH_MILLIS;
  }
}
