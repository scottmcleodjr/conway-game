package conway;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import javax.swing.Timer;

public class Controller {

  private int cellsWide;
  private int cellsHigh;
  private int pixelsPerCell;
  private int imageWidth;
  private int imageHeight;
  private Color liveColor;
  private Timer timer;
  private int timerDelayMillis; // Milliseconds

  private Generation state;
  private GenerationImage image;
  private GenerationImageFrame frame;

  public Controller(int pixels, int delay, StartSeedStyle style, boolean launchFullScreen, Color liveColor) {
    cellsWide = Toolkit.getDefaultToolkit().getScreenSize().width;
    cellsHigh = Toolkit.getDefaultToolkit().getScreenSize().height;
    if (!launchFullScreen) {
      cellsWide *= 0.8; // Fill most of the screen
      cellsHigh *= 0.8;
    }
    pixelsPerCell = pixels;
    imageWidth = cellsWide * pixelsPerCell;
    imageHeight = cellsHigh * pixelsPerCell;
    timerDelayMillis = delay;
    this.liveColor = liveColor;

    state = new Generation((cellsHigh + 2), (cellsWide + 2), style); // +2 to add border values
    image = new GenerationImage(state.getCurrentGeneration(), imageWidth, imageHeight, pixelsPerCell, liveColor);
    frame = new GenerationImageFrame(image, launchFullScreen);

    timer = new Timer(timerDelayMillis, new TimerEvent());
  }

  public void start() {
    timer.start();
  }

  private class TimerEvent implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      state.advangeGeneration();
      image = new GenerationImage(state.getCurrentGeneration(), imageWidth, imageHeight, pixelsPerCell, liveColor);
      frame.updateFrameImage(image);
    }
  }
}
