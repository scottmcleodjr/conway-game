package conway;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GenerationImage extends BufferedImage {

  public GenerationImage(byte[][] state, int xPixels, int yPixels, int pixelsPerCell, Color liveColor) {
    // Create a buffered Image and then make it match array
    super(xPixels, yPixels, BufferedImage.TYPE_INT_RGB);

    // Removes border values
    for (int r = 1; r < state.length - 1; r++) {
      for (int c = 1; c < state[1].length - 1; c++) {
        if (state[r][c] == 1) {
          // R and C (-1) because the above starts at 1,1 and the image starts at 0,0
          setRGB(c - 1, r - 1, liveColor.getRGB());

          // The below is used for cells>1x1px.
          /*
           * imageR and imageC coorespond to the upper left pixel of the cell in the
           * image. The image's .setRGB() method starts at (x,y) as (0,0). int imageR =
           * (r-1) * pixelsPerCell; int imageC = (c-1) * pixelsPerCell; for (int row =
           * imageR; row < (imageR + pixelsPerCell); row++) { for (int col = imageC; col <
           * (imageC + pixelsPerCell); col++) { setRGB(col, row, liveColor.getRGB()); } }
           */
        }
      }
    }
  }
}
