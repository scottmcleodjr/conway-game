package conway;

public enum StartSeedStyle {
  RANDOM {
    public String toString() {
      return "Random";
    }
  },
  HORIZONTAL_LINE {
    public String toString() {
      return "Horizontal Line";
    }
  },
  BOX_LINE {
    public String toString() {
      return "Box";
    }
  };
}
