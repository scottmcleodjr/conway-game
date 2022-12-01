package conway;

public class Main {

  public static void main(String[] args) {
    Config cfg = new ConfigDialog().show();
    if (cfg == null) {
      // User x-ed out the dialog
      System.exit(0);
    }
    new Controller(cfg).start();
  }
}
