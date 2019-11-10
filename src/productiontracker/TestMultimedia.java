package productiontracker;

import java.util.ArrayList;

/**
 * Test class used to put knowledge of interfaces, enums and inheritance into practice. Information
 * on this test product will be displayed if called in Main. Call was removed for Beta submission.
 *
 * @author Adam Dressel
 */
class TestMultimedia {
  /** Static method so test can be made by simply being called in Main. */
  public static void testMulti() {

    AudioPlayer newAudioProduct =
        new AudioPlayer(
        );

    Screen newScreen = new Screen();

    MoviePlayer newMovieProduct =
        new MoviePlayer(newScreen);

    // List created to show process of polymorphism in Java.
    ArrayList<MultimediaControl> productList = new ArrayList<>();

    productList.add(newAudioProduct);

    productList.add(newMovieProduct);
    /*
     * For loop will have no problem iterating through the list, even though there are two different
     * types of objects in the list. Since they both implement the same interface, they can be used
     * interchangeably.
     */
    for (MultimediaControl p : productList) {

      System.out.println(p);

      p.play();

      p.stop();

      p.next();

      p.previous();
    }
  }
}
