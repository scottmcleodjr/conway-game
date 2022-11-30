package conway;

import java.awt.image.BufferedImage;

public class GenerationImage extends BufferedImage {

  public GenerationImage(Config cfg, Generation gen) {
    super(cfg.getCellsWide(), cfg.getCellsHigh(), BufferedImage.TYPE_INT_RGB);

    int rgbColor = cfg.getLiveColor().getRGB();
    for (int y = 0; y < cfg.getCellsHigh(); y++) {
      for (int x = 0; x < cfg.getCellsWide(); x++) {
        if (gen.getValueAt(y, x) == 1) {
          setRGB(x, y, rgbColor);
        }
      }
    }
  }
}
