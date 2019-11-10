package productiontracker;

/**
 * Enum class that is used for all products created in the program. Used instead of String to
 * demonstrate knowledge and application of Java's enum type.
 *
 * @author Adam Dressel
 */
public enum ItemType {
  // The four types each with their own corresponding abbreviation.
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  public final String code;

  /**
   * Constructor used to create the four item type enums.
   *
   * @param code String that abbreviates the item type.
   */
  ItemType(String code) {
    this.code = code;
  }
}
