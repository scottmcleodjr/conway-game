package conway;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GenerationImageFrame extends JFrame {

  private static final long serialVersionUID = 1L;
  private JLabel imageLabel;

  public GenerationImageFrame(BufferedImage image, boolean launchFullScreen) {
    setTitle("Conway's Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.BLACK);

    if (launchFullScreen) {
      // Remove the title bar if you are full screen
      setUndecorated(true);
    } else {
      // Get the frame off the upper edge of the screen if not
      int w = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1);
      int h = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.1);
      setLocation(w, h);
    }

    try {
      setIconImage(ImageIO.read(getClass().getResource("/icon.png")));
    } catch (IOException e) { /* Fail quietly */ }

    imageLabel = new JLabel(new ImageIcon(image));
    imageLabel.addMouseListener(new ImageClick());
    add(imageLabel);
    pack();

    setVisible(true);
  }

  public void updateFrameImage(BufferedImage image) {
    imageLabel.setIcon(new ImageIcon(image));
  }

  private class ImageClick implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
      System.exit(0);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
  }
}
