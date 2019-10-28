package productionTracker;

public enum itemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  public final String code;

  itemType(String code) {
    this.code = code;
  }
}
