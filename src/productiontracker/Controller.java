package productiontracker;

import java.sql.*;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Controller class drives the program connecting the UI with general functionality.
 *
 * @author Adam Dressel
 */
@SuppressWarnings("WeakerAccess")
public class Controller {
  // Corresponds to all of the UI objects that are interactive in the program.
  @FXML private TextField productNameTxt;
  @FXML private TextField manufacturerTxt;
  @FXML private TextArea productionLog;
  @FXML private Button productLineBtn;
  @FXML private ChoiceBox<String> itemTypeChb;
  @FXML private TableView<Product> productTable;
  @FXML private TableColumn prodNameCol;
  @FXML private TableColumn manCol;
  @FXML private TableColumn itemTypeCol;
  @FXML private ListView prodList;
  @FXML private Button recProdBtn;
  @FXML private Label warningLbl;
  @FXML private ComboBox<Integer> chQntCb;
  @FXML private Button logoutBtn1;
  @FXML private Button logoutBtn2;
  @FXML private Button logoutBtn3;

  // Item type list used to populate combo box in products tab.
  private final ItemType[] itemTypes = ItemType.values();
  // List of products that are used to populate the Table View.
  private ObservableList<Product> productLine = FXCollections.observableArrayList();

  /**
   * initialize method is the driver for the program. This method is called in main once the UI is
   * generated. All other methods are called from the initialize method.
   */
  public void initialize() {
    setItemTypeChb(itemTypes, itemTypeChb);

    productLineBtn.setOnAction(this::addProd);
    recProdBtn.setOnAction(this::recProd);
    logoutBtn1.setOnAction(this::logout);
    logoutBtn2.setOnAction(this::logout);
    logoutBtn3.setOnAction(this::logout);

    setChQntCb();

    setProductLine();
    setProduceList();
    loadProductList();
    loadProductionLog();
  }

  /**
   * addProd method is used to insert products into the database. This is done through the UI by
   * extracting the values for the text fields and choice box and using them in a prepared
   * statement. Also verifies that the fields are not empty.
   *
   * @param addProdEvent Signals a button event for the setOnAction method
   */
  // Triggers warning in inspect code but I need parameter to trigger event.
  @FXML
  private void addProd(ActionEvent addProdEvent) {
    String prodName = productNameTxt.getText();
    String manufacturer = manufacturerTxt.getText();
    String code = itemTypeChb.getSelectionModel().getSelectedItem();
    if (code == null || manufacturer.length() == 0 || prodName.length() == 0) {
      warningLbl.setText("Please fill in required fields.");
      return;
    } else {
      warningLbl.setText("");
    }

    Product addedProd = new Product(prodName, code, manufacturer);
    productLine.add(addedProd);
    PreparedStatement pstmtUpdate;
    try {
      // Uses of prepared statement to parameterize queries.

      String query = "INSERT INTO PRODUCT(NAME,TYPE,MANUFACTURER)" + " VALUES (?,?,?)";

      // Bug says the statement may not be closed in the event of an exception.
      pstmtUpdate = EmployeeController.conn.prepareStatement(query);

      pstmtUpdate.setString(1, prodName);
      pstmtUpdate.setString(2, code);
      pstmtUpdate.setString(3, manufacturer);
      pstmtUpdate.executeUpdate();

      pstmtUpdate.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Sets the values of the columns to the fields of the Product class. The TableView is then
   * associated with the observable list product line and is updated when the list is changed
   * automatically.
   */
  private void setProductLine() {
    prodNameCol.setCellValueFactory(new PropertyValueFactory("name"));
    manCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    itemTypeCol.setCellValueFactory(new PropertyValueFactory("type"));
    productTable.setItems(productLine);
  }

  /**
   * Sets the item types for the choice box in a for each loop.
   *
   * @param types Takes an array of strings to assign to the choice box.
   * @param cb The choice box is passed so that it can use the data in the array.
   */
  private void setItemTypeChb(ItemType[] types, ChoiceBox<String> cb) {
    for (ItemType type : types) {
      String code = type.code;
      cb.getItems().add(code);
    }
  }

  /**
   * sets the choices for the combo box in the produce tab. Also calls two methods for additional
   * functionality.
   */
  private void setChQntCb() {
    for (int choices = 1; choices <= 10; choices++) {
      chQntCb.getItems().add(choices);
    }
    chQntCb.setEditable(false);
    chQntCb.getSelectionModel().selectFirst();
  }

  /**
   * loadProductList sets the productline fomr the database on startup. Products that can be
   * produced are now accessed by the program from the database.
   */
  private void loadProductList() {

    Statement selStmt;
    try {
      selStmt = EmployeeController.conn.createStatement();
      String selection = "SELECT NAME, TYPE, MANUFACTURER FROM PRODUCT";
      ResultSet selectRes = selStmt.executeQuery(selection);
      while (selectRes.next()) {
        String name = selectRes.getString("NAME");
        String manufacturer = selectRes.getString("MANUFACTURER");
        String code = selectRes.getString("TYPE");
        Product selectedProd = new Product(name, code, manufacturer);
        productLine.add(selectedProd);
      }
      selStmt.close();
    } catch (Throwable se) {
      se.printStackTrace();
    }
  }

  /**
   * Method used when the Production is recorded. Gets the data from the list view observable list,
   * as well as the combo box. A production record object is then created and put in the production
   * log Text Area.
   *
   * @param recProdEvent Event used to listen for a button press.
   */
  private void recProd(@SuppressWarnings("unused") ActionEvent recProdEvent) {
    ObservableList<Product> products = prodList.getItems();
    Product selectedProd = products.get(prodList.getSelectionModel().getSelectedIndex());
    int quantity = chQntCb.getSelectionModel().getSelectedItem();
    ArrayList<ProductionRecord> productionRun = new ArrayList<ProductionRecord>();
    for (int amtProduced = 0; amtProduced < quantity; amtProduced++) {
      ProductionRecord record = new ProductionRecord(selectedProd, amtProduced);
      productionRun.add(record);
      // productionLog.appendText(product.toString(selectedProd) + "\n");
    }
    updateProdRec(productionRun);
    showProduction(productionRun);
  }

  /**
   * Gets ProductionRecord objects from arrayList and adds them to the database.
   *
   * @param produced an arraylist of ProductionRecord objects
   */
  private void updateProdRec(ArrayList<ProductionRecord> produced) {
    PreparedStatement updateProdRec;
    String insertQuery;
    try {
      for (ProductionRecord product : produced) {
        insertQuery =
            "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID,SERIAL_NUM,DATE_PRODUCED)" + "VALUES(?,?,?)";
        updateProdRec = EmployeeController.conn.prepareStatement(insertQuery);

        updateProdRec.setInt(1, product.getProductID());
        updateProdRec.setString(2, product.getSerialNumber());
        updateProdRec.setTimestamp(3, new Timestamp(product.getDateProduced().getTime()));

        updateProdRec.executeUpdate();
        updateProdRec.close();
      }

    } catch (Exception ee) {
      ee.printStackTrace();
    }
  }

  /**
   * loadProductionLog gets items from the database and creates ProductionRecord objects from them.
   */
  private void loadProductionLog() {
    ArrayList<ProductionRecord> productionLog = new ArrayList<ProductionRecord>();
    try {
      Statement selectRec = EmployeeController.conn.createStatement();
      String selectQuery = "SELECT PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED FROM PRODUCTIONRECORD";
      ResultSet selectRes = selectRec.executeQuery(selectQuery);
      while (selectRes.next()) {
        int prodID = selectRes.getInt("PRODUCT_ID");
        String serial = selectRes.getString("SERIAL_NUM");
        Timestamp date = selectRes.getTimestamp("DATE_PRODUCED");
        ProductionRecord newRecord = new ProductionRecord(prodID, serial, date);
        productionLog.add(newRecord);
      }
      selectRec.close();
    } catch (Exception selectError) {
      selectError.printStackTrace();
    }
    showProduction(productionLog);
  }

  /**
   * showProduction displays ProductionRecord Objects to the textArea to display information.
   *
   * @param prodLog ProductionRecord Array list used to access the information from the
   *     ProductionLog.
   */
  private void showProduction(ArrayList<ProductionRecord> prodLog) {
    for (ProductionRecord record : prodLog) {
      productionLog.appendText(record.toString() + "\n");
    }
  }

  /** Sets the list view in the produce tab to the product line observable list. */
  private void setProduceList() {
    prodList.setItems(productLine);
  }

  /**
   * Heads back to the login menu by closing this fxml layout and opening up the Employee layout.
   *
   * @param logout Button event for Java FX.
   */
  @FXML
  private void logout(ActionEvent logout) {
    Stage primaryStage = new Stage();
    try {
      Parent empRoot = FXMLLoader.load(getClass().getResource("employee.fxml"));
      primaryStage.setTitle("Employee Login");
      primaryStage.setScene(new Scene(empRoot, 600, 500));
      primaryStage.show();
      Stage current = (Stage) logoutBtn1.getScene().getWindow();
      current.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
