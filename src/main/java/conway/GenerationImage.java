package conway;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GenerationImage extends BufferedImage {

  public GenerationImage(byte[][] state, int xPixels, int yPixels, Color liveColor) {
    // Create a buffered Image and then make it match array
    super(xPixels, yPixels, BufferedImage.TYPE_INT_RGB);

    for (int r = 0; r < state.length; r++) {
      for (int c = 0; c < state[1].length; c++) {
        if (state[r][c] == 1) {
          setRGB(c, r, liveColor.getRGB());
        }
      }
    }
  }
}
