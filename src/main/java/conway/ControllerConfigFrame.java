package conway;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ControllerConfigFrame extends JFrame {

  private static final long serialVersionUID = 1L;
  private final Color DEFAULT_COLOR = new Color(98, 239, 131); // Default to a bright green
  private Color liveColor;
  private JComboBox<StartSeedStyle> styleComboBox;
  private JCheckBox launchFullScreenCheckBox;
  private JSlider speedSlider;

  public ControllerConfigFrame() {
    // Setup Config JFrame basic settings
    setTitle("Conway's Game");
    setLayout(new GridLayout(5, 1));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200, 200); // Don't want it on the edge, should be safe on most monitors
    liveColor = DEFAULT_COLOR;

    try {
      setIconImage(ImageIO.read(getClass().getResource("/icon.png")));
    } catch (IOException e) { /* Fail quietly */ }

    // Panel to choose type of starting seed for animation
    JPanel stylePanel = new JPanel();
    stylePanel.setBorder(BorderFactory.createTitledBorder("Initial Layout"));
    StartSeedStyle[] styles = StartSeedStyle.values();
    styleComboBox = new JComboBox<StartSeedStyle>(styles);
    styleComboBox.setSelectedIndex(0);
    stylePanel.add(styleComboBox);

    // Panel to control full screen option
    JPanel launchFullScreenPanel = new JPanel();
    launchFullScreenPanel.setBorder(BorderFactory.createTitledBorder("Size"));
    launchFullScreenCheckBox = new JCheckBox("Launch Full Screen", false);
    launchFullScreenPanel.add(launchFullScreenCheckBox);

    // Panel to control speed of animation
    JPanel speedPanel = new JPanel();
    speedPanel.setBorder(BorderFactory.createTitledBorder("Generation Length"));
    speedSlider = new JSlider(JSlider.HORIZONTAL, 25, 925, 100);
    speedPanel.add(speedSlider);

    // Panel to control color of live cells
    JPanel colorPanel = new JPanel();
    colorPanel.setBorder(BorderFactory.createTitledBorder("Live Cell Color"));
    JButton colorButton = new JButton("Custom");
    colorButton.addActionListener(new ColorListener());
    colorPanel.add(colorButton);

    // Panel to launch Controller
    JPanel continuePanel = new JPanel();
    JButton continueButton = new JButton("Launch");
    continueButton.addActionListener(new ContinueListener());
    continuePanel.add(continueButton);

    // Additional setup for Config JFrame
    add(stylePanel);
    add(launchFullScreenPanel);
    add(speedPanel);
    add(colorPanel);
    add(continuePanel);
    pack();
  }

  private class ContinueListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      setVisible(false);
      Controller controller = new Controller(
        speedSlider.getValue(),
        (StartSeedStyle)styleComboBox.getSelectedItem(),
        launchFullScreenCheckBox.isSelected(),
        liveColor
      );
      controller.start();
      dispose();
    }
  }

  private class ColorListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      liveColor = JColorChooser.showDialog(null, "Select a color for live cells", Color.WHITE);
      // Because the JColorChooser cancel button will null liveColor
      if (liveColor == null) {
        liveColor = DEFAULT_COLOR; // Reset to the default green
      } else { /* Use the color from the JColorChooser */ }
    }
  }
}
