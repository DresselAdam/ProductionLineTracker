package productiontracker;

/**
 * Represents an Audioplayer which is a type of product that could be produced by this theoretical
 * production line software. Used to demonstrate knowledge of interfaces and inheritance.
 *
 * @author Adam Dressel
 */
public class AudioPlayer extends Product implements MultimediaControl {

  private final String audioSpecification;
  private final String mediaType;

  /**
   * Constructor makes use of its superclass's constructor, and adds additional unique fields.
   *
   */
  AudioPlayer() {
    super("DP-X1A", ItemType.AUDIO, "Onkyo");
    this.audioSpecification = "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC";
    this.mediaType = "M3U/PLS/WPL";
  }

  // --Commented out by Inspection START (11/9/2019 10:43 PM):
  //  /**
  //   * Sets the media type of this product.
  //   *
  //   * @param mediaType A string representing this product's media type.
  //   */
  //  public void setMediaType(String mediaType) {
  //    this.mediaType = mediaType;
  //  }
  // --Commented out by Inspection STOP (11/9/2019 10:43 PM)

  /** Method implemented by Multimedia Control interface. */
  public void play() {
    System.out.println("Playing");
  }

  /** Method implemented by Multimedia Control interface. */
  public void stop() {
    System.out.println("Stopping");
  }

  /** Method implemented by Multimedia Control interface. */
  public void previous() {
    System.out.println("Previous");
  }

  /** Method implemented by Multimedia Control interface. */
  public void next() {
    System.out.println("Next");
  }

  /**
   * Override of the toString method that calls the parent classes respective method, as well as
   * including this Class's unique fields.
   *
   * @return Returns formatted String representing this Product's info.
   */
  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + this.audioSpecification
        + "\nSupported Playlist Formats: "
        + this.mediaType;
  }
}
