package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

  private Connection conn;
  private PreparedStatement psmt;
  private ResultSet rset;
  final String JDBC_DRIVER = "org.h2.Driver";
  //final String DB_path =

  @Override
  public void start(Stage primaryStage) throws Exception {


    try {
      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      primaryStage.setTitle("Hello World");
      primaryStage.setScene(new Scene(root, 600, 550));
      primaryStage.show();
    }
   catch(Exception e){
      e.printStackTrace();
    }

    // end try catch
  }// end start method

  public void initializeDatabase() {

    String query = "";

    try {


    }catch (Exception db){

    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}// class main
