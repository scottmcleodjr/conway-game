package conway;

import java.awt.Color;
import java.awt.Toolkit;

public class Config {

  public static final int MIN_GEN_LENGTH_MILLIS = 50;
  public static final int MAX_GEN_LENGTH_MILLIS = 1000;
  public static final int DEFAULT_GEN_LENGTH_MILLIS = 150;
  private Color liveColor;
  private boolean fullScreen;
  private int cellsWide;
  private int cellsHigh;
  private int genLengthMillis;
  private StartSeedStyle style;

  public Config(Color liveColor, boolean fullScreen, int genLengthMillis, StartSeedStyle style) {
    this.liveColor = liveColor;

    this.fullScreen = fullScreen;
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    // Fill most of the screen if not full screen
    cellsWide = fullScreen ? screenWidth : (int) (screenWidth * 0.8);
    cellsHigh = fullScreen ? screenHeight : (int) (screenHeight * 0.8);

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
    return fullScreen;
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
