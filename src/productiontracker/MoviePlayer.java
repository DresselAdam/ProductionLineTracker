package productiontracker;

/**
 * Similar to the Audio Player, the MoviePlayer is a test class used to demonstrate knowledge of
 * Java implementation, inheritance and polymorphism. Movie Player represents a Visual Product that
 * can be produced in the production line tracker.
 *
 * @author Adam Dressel
 */
public class MoviePlayer extends Product implements MultimediaControl {

  private final MonitorType monitorType;
  private final Screen movieScreen;

  /**
   * Constructor implementing the super class's constructor.
   *
   * @param movieScreen Screen object that the movie is being displayed on.
   */
  MoviePlayer(Screen movieScreen) {
    super("DBPOWER MK101", ItemType.VISUAL, "OracleProduction");
    this.monitorType = MonitorType.LCD;
    this.movieScreen = movieScreen;
  }

  /** Method implemented by Multimedia Control interface. */
  @Override
  public void play() {
    System.out.print("play");
  }

  /** Method implemented by Multimedia Control interface. */
  @Override
  public void stop() {
    System.out.print("stop");
  }

  /** Method implemented by Multimedia Control interface. */
  @Override
  public void previous() {
    System.out.print("previous");
  }

  /** Method implemented by Multimedia Control interface. */
  @Override
  public void next() {
    System.out.print("next");
  }

  /**
   * Displays the information of the Movie Player as a formatted String.
   *
   * @return Returns a formatted string with all of the Movie Player's fields.
   */
  public String toString() {
    return super.toString() + "Monitor: " + this.monitorType + "\nScreen: " + this.movieScreen;
  }
}
