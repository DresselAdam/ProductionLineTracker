package productiontracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * EmployeeController controls the UI for the sign in screen. This controller also updates and reads
 * from the database. This class also initializes the database.
 *
 * @author Adam Dressel
 */
public class EmployeeController {
  @FXML private Tab signinTab;
  @FXML private Button signInBtn;
  @FXML private TextField userField;
  @FXML private Label userLbl;
  @FXML private Label passLbl;
  @FXML private PasswordField passField;
  @FXML private Tab creatAcctTab;
  @FXML private Label nameCreatLbl;
  @FXML private Label passCreatLbl;
  @FXML private TextField nameCreateTf;
  @FXML private PasswordField passCreateTf;
  @FXML private Button acctBtn;
  @FXML private Label empWarning;
  @FXML private Label loginWarnLbl;

  // Connection object used to set up the database.
  static Connection conn;

  /** Initialize is the driver for the employee sign in stage. */
  public void initialize() {
    initializeDatabase();
    signInBtn.setOnAction(this::signIn);
    acctBtn.setOnAction(this::createAcct);
  }

  /**
   * initializeDatabase sets up the connection to the H2 product database. Since this connection is
   * made at the beginning, multiple queries can be made without creating another connection.
   */
  private void initializeDatabase() {
    String path = "jdbc:h2:./res/ProdDB";
    final String jdbcDriver = "org.h2.Driver";

    try {
      Class.forName(jdbcDriver);

      conn = DriverManager.getConnection(path);
      System.out.println("Connection Successful");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is called on action for the sign in button. Calls verify sign and check employee
   * Db. Launches the stage for the production UI.
   *
   * @param signEvent is the button parameter for JavaFX.
   */
  @FXML
  private void signIn(ActionEvent signEvent) {
    if (!verifySignIn()) {
      return;
    } else if (!checkEmployeeDB()) {
      loginWarnLbl.setText("Incorrect Username or Password");
      return;
    }

    Stage mainStage = new Stage();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("trackerFormat.fxml"));
      mainStage.setTitle("Production Line");
      mainStage.setScene(new Scene(root, 600, 600));
      root.getStylesheets().add("productiontracker/production_style.css");
      mainStage.show();
      Stage current = (Stage) signInBtn.getScene().getWindow();
      current.close();
    } catch (Exception newE) {
      newE.printStackTrace();
    }
  }

  /**
   * Ensures the user and password fields are not empty.
   *
   * @return A boolean based on whether the check is passed or not.
   */
  private boolean verifySignIn() {
    String username = userField.getText();
    String password = passField.getText();
    if (username.equals("") && password.equals("")) {
      userLbl.setText("Please enter your username");
      passLbl.setText("Please enter your password");
      return false;
    } else if (password.equals("")) {
      passLbl.setText("Please enter your password");
      return false;
    } else if (username.equals("")) {
      userLbl.setText("Please enter your username");
      return false;
    } else {
      return true;
    }
  }

  /**
   * Checks to see if there is a match between the text entered in the TextFields, and the employees
   * from the DB.
   *
   * @return A boolean based on whether or not there is a username and password match.
   */
  private boolean checkEmployeeDB() {
    String username = userField.getText();
    String password = passField.getText();
    boolean exists = false;
    try {
      Statement selStmt = EmployeeController.conn.createStatement();
      String selection = "SELECT USERNAME,PASSWORD FROM EMPLOYEE";
      ResultSet selectRes = selStmt.executeQuery(selection);
      while (selectRes.next()) {
        String existingUser = selectRes.getString("USERNAME");
        String existingPass = selectRes.getString("PASSWORD");
        Employee realEmployee = new Employee(existingUser, existingPass);
        if (username.equals(existingUser)
            && password.equals(realEmployee.reverseString(existingPass))) {
          exists = true;
        }
      }
      selStmt.close();
    } catch (Exception se) {
      se.printStackTrace();
    }
    return exists;
  }

  /**
   * Creates an account based upon the text entered in the corresponding text fields. Each field is
   * verified before creating a new employee. Once the employee is created, an alert pops up with
   * the account information.
   *
   * @param createEvent Button event for Java FX.
   */
  @FXML
  private void createAcct(ActionEvent createEvent) {
    StringBuilder name = new StringBuilder(nameCreateTf.getText());
    String password = passCreateTf.getText();
    if (name.toString().equals("") || password.equals("")) {
      empWarning.setText("You must fill in name and password");
      return;
    }
    Employee newEmployee = new Employee(name, password);
    if (newEmployee.getUsername().equals("default")) {
      empWarning.setText("Invalid username or password.");
    } else {
      addEmployee(newEmployee);
      Alert accountInfo = new Alert(Alert.AlertType.INFORMATION);
      accountInfo.setTitle("Account Info");
      accountInfo.setHeaderText("Success! Account Created!");
      accountInfo.setContentText(
          newEmployee.toString() + "\n Make sure to keep this info in a safe place!");
      accountInfo.showAndWait();
      empWarning.setText("");
    }
  }

  /**
   * Adds the created employee from the createAcct to the database.
   *
   * @param newEmployee The employee added to the database.
   */
  private void addEmployee(Employee newEmployee) {
    String empName = newEmployee.getName().toString();
    String userName = newEmployee.getUsername();
    String securePass = newEmployee.reverseString(newEmployee.getPassword());
    String email = newEmployee.getEmail();
    PreparedStatement pstmtUpdate;
    try {
      // Uses of prepared statement to parameterize queries.

      String query = "INSERT INTO EMPLOYEE(NAME,USERNAME,PASSWORD,EMAIL)" + " VALUES (?,?,?,?)";

      // Bug says the statement may not be closed in the event of an exception.
      pstmtUpdate = EmployeeController.conn.prepareStatement(query);

      pstmtUpdate.setString(1, empName);
      pstmtUpdate.setString(2, userName);
      pstmtUpdate.setString(3, securePass);
      pstmtUpdate.setString(4, email);
      pstmtUpdate.executeUpdate();

      pstmtUpdate.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
