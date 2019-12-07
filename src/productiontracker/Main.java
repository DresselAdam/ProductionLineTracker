package productiontracker;

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
      Parent empRoot = FXMLLoader.load(getClass().getResource("employee.fxml"));
      primaryStage.setTitle("Employee Login");
      primaryStage.setScene(new Scene(empRoot, 600, 300));
      primaryStage.show();
      /*
      Parent root = FXMLLoader.load(getClass().getResource("trackerFormat.fxml"));
      primaryStage.setTitle("Production Line productiontracker");
      primaryStage.setScene(new Scene(root, 600, 550));
      root.getStylesheets().add("productiontracker/production_style.css");
      primaryStage.show();

       */
    } catch (Exception e) {
      e.printStackTrace();
    } // end try catch
  } // end start method

  public static void main(String[] args) {
      launch(args);
  }
} // class main
