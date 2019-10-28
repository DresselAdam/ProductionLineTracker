package productionTracker;

import java.util.ArrayList;

public class testMultimedia {
  public static void testMulti() {

    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");

    Screen newScreen = new Screen("720x480", 40, 22);

    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", MonitorType.LCD, newScreen);

    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();

    productList.add(newAudioProduct);

    productList.add(newMovieProduct);

    for (MultimediaControl p : productList) {

      System.out.println(p);

      p.play();

      p.stop();

      p.next();

      p.previous();
    }
  }
}
