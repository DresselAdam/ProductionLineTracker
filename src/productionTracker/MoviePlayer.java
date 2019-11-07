package productionTracker;

public class MoviePlayer extends Product implements MultimediaControl {

  private MonitorType monitorType;
  private Screen movieScreen;

  MoviePlayer(String name, String manufacturer, MonitorType monitorType, Screen movieScreen) {
    super(name, ItemType.VISUAL, manufacturer);
    this.monitorType = monitorType;
    this.movieScreen = movieScreen;
  }

  @Override
  public void play() {}

  @Override
  public void stop() {}

  @Override
  public void previous() {}

  @Override
  public void next() {}

  public String toString() {
    return super.toString() + "Monitor: " + this.monitorType + "\nScreen: " + this.movieScreen;
  }
}
