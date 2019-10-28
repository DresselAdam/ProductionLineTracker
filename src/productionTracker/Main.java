package productionTracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// Sources: https://docs.oracle.com https://www.tutorialspoint.com/javafx/
// https://www.tutorialspoint.com/jdbc http://tutorials.jenkov.com/javafx/
// https://www.baeldung.com/java-pad-string

/**
 * Main initializes UI and starts the Controller class.
 *
 * @author Adam Dressel
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {

    try {
      Parent root = FXMLLoader.load(getClass().getResource("trackerFormat.fxml"));
      primaryStage.setTitle("Hello World");
      primaryStage.setScene(new Scene(root, 600, 550));
      root.getStylesheets().add("productionTracker/production_style.css");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    } // end try catch
  } // end start method

  public static void main(String[] args) {
    launch(args);
    testMultimedia.testMulti();
  }
} // class main
