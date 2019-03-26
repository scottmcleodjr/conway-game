package conway;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class ControllerConfigFrame extends JFrame {

  private Color liveColor = new Color(98,239,131); // Default to a bright green
  private StartSeedStyle style;
  private JPanel stylePanel;
  private JPanel speedPanel;
  private JPanel colorPanel;
  private JPanel continuePanel;
  private JPanel launchFullScreenPanel;
  private JRadioButton styleRandomRadioButton;
  private JRadioButton styleHorizontalLineRadioButton;
  private JRadioButton styleBoxLineRadioButton;
  private ButtonGroup styleButtons;
  private JCheckBox launchFullScreenCheckBox;
  private JSlider speedSlider;
  private JButton colorButton;
  private JButton continueButton;

  public ControllerConfigFrame() {
    // Setup Config JFrame basic settings
    setTitle("Conway's Game");
    setLayout(new GridLayout(5,1));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200,200); // Don't want it on the edge, should be safe on most monitors

    try {
      setIconImage(ImageIO.read(getClass().getResource("/resources/icon.png")));
    } catch (IOException e) {/* Fail quietly */}

    // Panel to choose type of starting seed for animation
    stylePanel = new JPanel();
    stylePanel.setBorder(BorderFactory.createTitledBorder("Initial Layout"));
    styleRandomRadioButton = new JRadioButton("Random Layout", true);
    styleHorizontalLineRadioButton = new JRadioButton("Horizontal Line");
    styleBoxLineRadioButton = new JRadioButton("Box");

    styleButtons = new ButtonGroup();
    styleButtons.add(styleRandomRadioButton);
    styleButtons.add(styleHorizontalLineRadioButton);
    styleButtons.add(styleBoxLineRadioButton);

    stylePanel.add(styleRandomRadioButton);
    stylePanel.add(styleHorizontalLineRadioButton);
    stylePanel.add(styleBoxLineRadioButton);

    // Panel to control full screen option
    launchFullScreenPanel = new JPanel();
    launchFullScreenPanel.setBorder(BorderFactory.createTitledBorder("Size"));
    launchFullScreenCheckBox = new JCheckBox("Launch Full Screen", true);
    launchFullScreenPanel.add(launchFullScreenCheckBox);

    // Panel to control speed of animation
    speedPanel = new JPanel();
    speedPanel.setBorder(BorderFactory.createTitledBorder("Generation Length"));
    speedSlider = new JSlider(JSlider.HORIZONTAL, 25, 925, 100);
    speedPanel.add(speedSlider);

    // Panel to control color of live cells
    colorPanel = new JPanel();
    colorPanel.setBorder(BorderFactory.createTitledBorder("Live Cell Color"));
    colorButton = new JButton("Custom");
    colorButton.addActionListener(new ColorListener());
    colorPanel.add(colorButton);

    // Panel to launch Controller
    continuePanel = new JPanel();
    continueButton = new JButton("Launch");
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
      // Style setting
      if (styleRandomRadioButton.isSelected()) {
        style = StartSeedStyle.RANDOM;
      } else if (styleHorizontalLineRadioButton.isSelected()) {
        style = StartSeedStyle.HORIZONTAL_LINE;
      } else if (styleBoxLineRadioButton.isSelected()) {
        style = StartSeedStyle.BOX_LINE;
      }
      Controller controller = new Controller(1, speedSlider.getValue(), style, launchFullScreenCheckBox.isSelected(), liveColor);
      controller.start();
      dispose();
    }
  }

  private class ColorListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      liveColor = JColorChooser.showDialog(null, "Select a color for live cells", Color.WHITE);
      // Because the JColorChooser cancel button will null liveColor
      if (liveColor == null) {
        liveColor = new Color(98,239,131); // Reset to the default green
      } else {/* Use the color from the JColorChooser */}
    }
  }
}
