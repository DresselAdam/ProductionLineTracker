package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// Sources: https://docs.oracle.com https://www.tutorialspoint.com/javafx/
// https://www.tutorialspoint.com/jdbc http://tutorials.jenkov.com/javafx/

/**
 * Main initializes UI and starts the Controller class.
 *
 * @author Adam Dressel
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {

    try {
      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      primaryStage.setTitle("Hello World");
      primaryStage.setScene(new Scene(root, 600, 550));
      root.getStylesheets().add("sample/production_style.css");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    } // end try catch
  } // end start method

  public static void main(String[] args) {
    launch(args);
  }
} // class main
