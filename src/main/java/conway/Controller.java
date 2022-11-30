package conway;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Controller {

  private Config cfg;
  private Generation state;
  private GenerationImageFrame frame;
  private Timer timer;

  public Controller(Config cfg) {
    this.cfg = cfg;

    state = new Generation(cfg);
    frame = new GenerationImageFrame(new GenerationImage(cfg, state), cfg.isFullScreen());
    state.advanceGeneration();

    timer = new Timer(cfg.getGenLengthMillis(), new TimerEvent());
  }

  public void start() {
    timer.start();
  }

  private class TimerEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      frame.updateFrameImage(new GenerationImage(cfg, state));
      state.advanceGeneration();
    }
  }
}
