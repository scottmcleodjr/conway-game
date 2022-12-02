package conway;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

  private Config cfg;
  private Generation gen;
  private GenerationImageFrame frame;
  private Timer timer;

  public Controller(Config cfg) {
    this.cfg = cfg;

    gen = new Generation(cfg);
    frame = new GenerationImageFrame(new GenerationImage(cfg, gen), cfg.isFullScreen());
    gen = gen.getNextGeneration();

    timer = new Timer();
  }

  public void start() {
    frame.show();
    timer.scheduleAtFixedRate(new UpdateImageTask(), 0, cfg.getGenLengthMillis());
  }

  private class UpdateImageTask extends TimerTask {
    @Override
    public void run() {
      frame.updateFrameImage(new GenerationImage(cfg, gen));
      gen = gen.getNextGeneration();
    }
  }
}
