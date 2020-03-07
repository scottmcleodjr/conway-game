package conway;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GenerationImage extends BufferedImage {

  public GenerationImage(byte[][] state, int xPixels, int yPixels, Color liveColor) {
    // Create a buffered Image and then make it match array
    super(xPixels, yPixels, BufferedImage.TYPE_INT_RGB);

    // Removes border values
    for (int r = 1; r < state.length - 1; r++) {
      for (int c = 1; c < state[1].length - 1; c++) {
        if (state[r][c] == 1) {
          // R and C (-1) because the above starts at 1,1 and the image starts at 0,0
          setRGB(c - 1, r - 1, liveColor.getRGB());
        }
      }
    }
  }
}
