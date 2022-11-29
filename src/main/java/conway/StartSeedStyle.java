package conway;

public enum StartSeedStyle {
  RANDOM {
    @Override
    public String toString() {
      return "Random";
    }
  },
  HORIZONTAL_LINE {
    @Override
    public String toString() {
      return "Horizontal Line";
    }
  },
  BOX_LINE {
    @Override
    public String toString() {
      return "Box";
    }
  };
}
