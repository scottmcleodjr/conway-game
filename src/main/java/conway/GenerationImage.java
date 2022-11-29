package conway;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GenerationImage extends BufferedImage {

  public GenerationImage(Generation state, int xPixels, int yPixels, Color liveColor) {
    // Create a buffered Image and then make it match array
    super(xPixels, yPixels, BufferedImage.TYPE_INT_RGB);

    for (int r = 0; r < state.getRows(); r++) {
      for (int c = 0; c < state.getCols(); c++) {
        if (state.getValueAt(r, c) == 1) {
          setRGB(c, r, liveColor.getRGB());
        }
      }
    }
  }
}
