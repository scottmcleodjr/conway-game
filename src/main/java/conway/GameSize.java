package conway;

public enum GameSize {
  FULL_SCREEN {
    @Override
    public String toString() {
      return "Full Screen";
    }
  },
  LARGE {
    @Override
    public String toString() {
      return "Large";
    }
  },
  MEDIUM {
    @Override
    public String toString() {
      return "Medium";
    }
  },
  SMALL {
    @Override
    public String toString() {
      return "Small";
    }
  };
}
