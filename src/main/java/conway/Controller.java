package conway;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Controller {

  private Config cfg;
  private Generation state;
  private GenerationImage image;
  private GenerationImageFrame frame;
  private Timer timer;

  public Controller(Config cfg) {
    this.cfg = cfg;

    state = new Generation(cfg.getCellsHigh(), cfg.getCellsWide(), cfg.getStyle());
    image = new GenerationImage(cfg, state);
    frame = new GenerationImageFrame(image, cfg.isFullScreen());

    timer = new Timer(cfg.getGenLengthMillis(), new TimerEvent());
  }

  public void start() {
    timer.start();
  }

  private class TimerEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      state.advanceGeneration();
      image = new GenerationImage(cfg, state);
      frame.updateFrameImage(image);
    }
  }
}
