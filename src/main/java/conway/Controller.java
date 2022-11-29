package conway;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import javax.swing.Timer;

public class Controller {

  private int cellsWide;
  private int cellsHigh;
  private Color liveColor;
  private Generation state;
  private GenerationImage image;
  private GenerationImageFrame frame;
  private Timer timer;

  public Controller(int delay, StartSeedStyle style, boolean launchFullScreen, Color liveColor) {
    cellsWide = Toolkit.getDefaultToolkit().getScreenSize().width;
    cellsHigh = Toolkit.getDefaultToolkit().getScreenSize().height;
    if (!launchFullScreen) {
      // Fill most of the screen if not full screen
      cellsWide *= 0.8;
      cellsHigh *= 0.8;
    }
    this.liveColor = liveColor;

    state = new Generation((cellsHigh), (cellsWide), style);
    image = new GenerationImage(state, cellsWide, cellsHigh, liveColor);
    frame = new GenerationImageFrame(image, launchFullScreen);

    timer = new Timer(delay, new TimerEvent());
  }

  public void start() {
    timer.start();
  }

  private class TimerEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      state.advangeGeneration();
      image = new GenerationImage(state, cellsWide, cellsHigh, liveColor);
      frame.updateFrameImage(image);
    }
  }
}
