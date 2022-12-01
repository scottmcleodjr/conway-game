package conway;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

public class ConfigDialog {

  private JDialog dialog;
  private Config cfg;
  private Color liveColor;
  private JComboBox<StartSeedStyle> styleComboBox;
  private JComboBox<GameSize> sizeComboBox;
  private JSlider speedSlider;

  public ConfigDialog() {
    dialog = new JDialog();
    dialog.setTitle("Conway's Game");
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setModal(true);
    dialog.setLayout(new GridLayout(5, 1));
    dialog.setLocation(200, 200); // Don't want it on the edge

    Border emptyBorder = BorderFactory.createEmptyBorder();

    // Start Style
    JPanel stylePanel = new JPanel();
    stylePanel.setBorder(BorderFactory.createTitledBorder(emptyBorder, "Initial Layout"));
    styleComboBox = new JComboBox<StartSeedStyle>(StartSeedStyle.values());
    stylePanel.add(styleComboBox);

    // Launch Size
    JPanel sizePanel = new JPanel();
    sizePanel.setBorder(BorderFactory.createTitledBorder(emptyBorder, "Game Size"));
    sizeComboBox = new JComboBox<GameSize>(GameSize.values());
    sizeComboBox.setSelectedIndex(1); // Default to large
    sizePanel.add(sizeComboBox);

    // Generation Length
    JPanel speedPanel = new JPanel();
    speedPanel.setBorder(BorderFactory.createTitledBorder(emptyBorder, "Generation Length"));
    speedSlider = new JSlider(
        JSlider.HORIZONTAL,
        Config.MIN_GEN_LENGTH_MILLIS,
        Config.MAX_GEN_LENGTH_MILLIS,
        Config.DEFAULT_GEN_LENGTH_MILLIS);
    speedPanel.add(speedSlider);

    // Color Selection
    JPanel colorPanel = new JPanel();
    colorPanel.setBorder(BorderFactory.createTitledBorder(emptyBorder, "Live Cell Color"));
    JButton colorButton = new JButton("Custom");
    colorButton.addActionListener(new ColorListener());
    colorPanel.add(colorButton);

    // Continue Button
    JPanel continuePanel = new JPanel();
    JButton continueButton = new JButton("Launch");
    continueButton.addActionListener(new ContinueListener());
    continuePanel.add(continueButton);

    dialog.add(stylePanel);
    dialog.add(sizePanel);
    dialog.add(speedPanel);
    dialog.add(colorPanel);
    dialog.add(continuePanel);
    dialog.pack();
  }

  public Config show() {
    dialog.setVisible(true);
    return cfg;
  }

  private class ContinueListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      cfg = new Config(
          liveColor != null ? liveColor : Config.DEFAULT_LIVE_COLOR,
          (GameSize) sizeComboBox.getSelectedItem(),
          speedSlider.getValue(),
          (StartSeedStyle) styleComboBox.getSelectedItem());
      dialog.dispose();
    }
  }

  private class ColorListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      liveColor = JColorChooser.showDialog(null, "Select a Color for Live Cells", Color.WHITE);
    }
  }
}
