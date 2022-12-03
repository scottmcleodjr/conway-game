package conway;

public enum StartSeedStyle {
  RANDOM {
    @Override
    public String toString() {
      return "Random";
    }
  },
  SYMMETRICAL_RANDOM {
    @Override
    public String toString() {
      return "Symmetrical Random";
    }
  },
  HORIZONTAL_LINE {
    @Override
    public String toString() {
      return "Horizontal Line";
    }
  },
  BOX {
    @Override
    public String toString() {
      return "Box";
    }
  },
  CROSS {
    @Override
    public String toString() {
      return "Cross";
    }
  },
  GLIDERS {
    @Override
    public String toString() {
      return "Gliders";
    }
  };
}
