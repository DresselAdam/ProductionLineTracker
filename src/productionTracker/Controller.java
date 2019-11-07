package productionTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class drives the program connecting the UI with general functionality.
 *
 * @author Adam Dressel
 */
public class Controller {

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

  private ItemType[] ItemTypes = ItemType.values();
  private ObservableList<Product> productLine = FXCollections.observableArrayList();

  @FXML
  // Combo box for the produce tab.
  private ComboBox<Integer> chQntCb;

  private Connection conn;

  /**
   * initialize method is the driver for the program. This method is called in main once the UI is
   * generated. All other methods are called from the initialize method.
   */
  public void initialize() {
    setItemTypeChb(ItemTypes, itemTypeChb);

    initializeDatabase();

    productLineBtn.setOnAction(this::addProd);

    setChQntCb();

    setProductionLog();
    setProductLine();
    setProduceList();
    // showProducts();

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
   * addProd method is used to insert products into the database. This is done through the UI by
   * extracting the values for the textfields and choice box and using them in a prepared statement.
   *
   * @param addProdEvent Signals a button event for the setOnAction method
   */
  // Triggers warning in inspect code but I need parameter to trigger event.
  public void addProd(ActionEvent addProdEvent) {

    System.out.println("Inserting records into the table...");
    String prodName = productNameTxt.getText();
    String manufacturer = manufacturerTxt.getText();
    String code = itemTypeChb.getValue();
    Product addedProd = new Product(prodName, code, manufacturer);
    productLine.add(addedProd);
    try {
      PreparedStatement pstmtUpdate;

      String query = "INSERT INTO PRODUCT(NAME,TYPE,MANUFACTURER)" + " VALUES (?,?,?)";

      // Bug says the statement may not be closed in the event of an exception.
      pstmtUpdate = conn.prepareStatement(query);

      pstmtUpdate.setString(1, prodName);
      pstmtUpdate.setString(2, manufacturer);
      pstmtUpdate.setString(3, code);
      pstmtUpdate.executeUpdate();

      pstmtUpdate.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

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
    chQntCb.setEditable(true);
    chQntCb.getSelectionModel().selectFirst();
  }

  private void setProductionLog() {
    int testNum = 10;
    Product testProduct = new AudioPlayer("test", "ProductTesters", "WAV", "MP3");
    ProductionRecord testRecord = new ProductionRecord(testProduct, testNum);
    productionLog.setText(testRecord.toString(testProduct));
  }

  private void setProduceList() {
      prodList.setItems(productLine);
  }
}
