package productiontracker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;;

public class EmployeeController {

  @FXML Button signInBtn;
  @FXML Button acctBtn;
  @FXML TextField userField;
  @FXML TextField passField;
  @FXML Label userLbl;
  @FXML Label passLbl;

  public void initialize() {
    signInBtn.setOnAction(this::signIn);
  }

  @FXML
  private void signIn(ActionEvent signEvent) {

    Stage mainStage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("trackerFormat.fxml"));
      mainStage.setTitle("Production Line productiontracker");
      mainStage.setScene(new Scene(root, 600, 550));
      root.getStylesheets().add("productiontracker/production_style.css");
      mainStage.show();
    } catch (Exception newE) {
      newE.printStackTrace();
    }
  }
}
