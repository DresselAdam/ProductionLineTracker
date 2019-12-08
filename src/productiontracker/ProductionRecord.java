package productiontracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 * Production Record class is used to keep log of produced items.
 *
 * @author Adam Dressel
 */
class ProductionRecord {
  // Soon: increment unique serial number based on item types in database.
  private Product product;
  private int productID;
  private final String serialNumber;
  private final Date dateProduced;

  @SuppressWarnings("unused")
  ProductionRecord(int productID, String serialNumber, Date dateProduced) {
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
    this.setProduct();
  }

  /**
   * This constructor will be used to create a production record for a product and assign it a
   * serial number.
   *
   * @param newProduct the Product that is being produced. Used to obtain fields for serial num
   * @param typeNum the number of products of this type that have been produced.
   */
  ProductionRecord(Product newProduct, int typeNum) {
    String manu;
    // Test to see if manufacturer is long enough to generate substring.
    if (newProduct.getManufacturer().length() < 4) {
      manu = newProduct.getManufacturer();
    } else {
      manu = newProduct.getManufacturer().substring(0, 3);
    }
    String typeCode = newProduct.getType().code;
    String uniqueNum = convertTypeNum(typeNum, typeCode);
    this.serialNumber = manu + typeCode + uniqueNum;
    this.productID = newProduct.getId();
    this.dateProduced = new Date();
    this.product = newProduct;
  }

  /**
   * Method gets the number of items created in the corresponding type, and converts it for use in
   * the serial number.
   *
   * @return Returns the converted number padded with zeros as a string.
   */
  private String convertTypeNum(int typeNum, String code) {
    try {
      Connection conn = EmployeeController.conn;
      Statement selectionStmt = conn.createStatement();
      String select = "SELECT SERIAL_NUM FROM PRODUCTIONRECORD";
      ResultSet selectRecord = selectionStmt.executeQuery(select);
      while (selectRecord.next()) {
        String serialNum = selectRecord.getString("SERIAL_NUM");
        if (serialNum.contains(code)) {
          typeNum++;
        }
      }
      selectionStmt.close();
    } catch (Exception selE) {
      selE.printStackTrace();
    }
    // Pads the number typeNum with the amount of zeros corresponding to 5 - #of digits in typeNum
    return String.format("%5d", typeNum).replace(' ', '0');
  }

  /**
   * Will convert the product record information into a displayable format.
   *
   * @return Will return the formatted String to caller.
   */
  public String toString() {
    return " Product Name: "
        + this.product.getName()
        + " Serial Num: "
        + this.serialNumber
        + " Date: "
        + this.dateProduced;
  }

  /**
   * Returns the id of this product.
   *
   * @return An integer value corresponding to the product ID.
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Returns the serial number.
   *
   * @return A String of this object's serialNumber.
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Gets the date object for the date this record was made.
   *
   * @return A date object for this production record.
   */
  public Date getDateProduced() {
    return dateProduced;
  }

  /**
   * setProduct accesses the product table to determine which Product this record is logging. This
   * is used to print the name of the object in the production log.
   */
  public void setProduct() {
    try {
      Connection conn = EmployeeController.conn;
      Statement selectionStmt = conn.createStatement();
      String selQuer = "SELECT ID, NAME, TYPE, MANUFACTURER FROM PRODUCT";
      ResultSet searchProd = selectionStmt.executeQuery(selQuer);
      while (searchProd.next()) {
        int id = searchProd.getInt("ID");
        if (this.productID == id) {
          String name = searchProd.getString("NAME");
          String code = searchProd.getString("TYPE");
          String manufacturer = searchProd.getString("MANUFACTURER");
          this.product = new Product(name, code, manufacturer);
        }
      }
      selectionStmt.close();
    } catch (Exception selErr) {
      selErr.printStackTrace();
    }
  }
}
