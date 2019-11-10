package productiontracker;

/**
 * Test class used to demonstrate knowledge of interfaces. Represents a Screen as a multimedia
 * device that can be produced.
 *
 * @author Adam Dressel
 */
public class Screen implements ScreenSpec {

  private String resolution;
  private int refreshRate;
  private int responseTime;

// --Commented out by Inspection START (11/9/2019 11:17 PM):
//  /**
//   * Used to create and produce Screen objects.
//   *  */
//  Screen() {
//    this.resolution = "720x480";
//    this.refreshRate = 40;
//    this.responseTime = 22;
//  }
// --Commented out by Inspection STOP (11/9/2019 11:17 PM)

// --Commented out by Inspection START (11/9/2019 11:17 PM):
//  /**
//   * Get the String of this Screen's resolution.
//   *
//   * @return This screen's resolution.
//   */
//  @Override
//  public String getResolution() {
//    return this.resolution;
//  }
// --Commented out by Inspection STOP (11/9/2019 11:17 PM)

// --Commented out by Inspection START (11/9/2019 11:17 PM):
//  /**
//   * Get the refresh rate as integer.
//   *
//   * @return This screen's refresh rate.
//   */
//  @Override
//  public int getRefreshRate() {
//    return this.refreshRate;
//  }
// --Commented out by Inspection STOP (11/9/2019 11:17 PM)

  /**
   * Get the screen's response time.
   *
   * @return This screen's response time.
   */
  @Override
  public int getResponseTime() {
    return this.responseTime;
  }

  /**
   * Converts information of screen to a String format.
   *
   * @return returns formatted string for use in printing or displaying information.
   */
  public String toString() {
    return "Resolution: "
        + this.resolution
        + "\nRefresh Rate: "
        + this.refreshRate
        + "\nresponseTime: "
        + this.responseTime;
  }
}
