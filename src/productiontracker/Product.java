package productiontracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Primary Object used in this program. Product represents items that can be produced in the
 * production line tracker. Once product exists in the program, it can be used to create production
 * records.
 *
 * @author Adam Dressel
 */
public class Product implements Item {

  private int id;
  private ItemType type;
  private String name;
  private String manufacturer;

  /**
   * Basic constructor for this class.
   *
   * @param name Name of the product
   * @param type Type of the product as a member of the ItemType interface.
   * @param manufacturer Product manufacturer.
   */
  Product(String name, ItemType type, String manufacturer) {
    this.name = name;
    this.type = type;
    this.manufacturer = manufacturer;
  }

  /**
   * Alternative constructor that uses the corresponding code to assign the item type.
   *
   * @param name Name of the product.
   * @param typeCode Code of the item type.
   * @param manufacturer Manufacturer of the product.
   */
  Product(String name, String typeCode, String manufacturer) {
    this.name = name;
    this.type = filterType(typeCode);
    this.manufacturer = manufacturer;
  }

  /**
   * Switch case matches code with corresponding item type.
   *
   * @param typeCode gets the code passed into a constructor and uses it to determine the itemType
   * @return The corresponding itemType related to the code
   */
  private ItemType filterType(String typeCode) {
    boolean isValid = false;
    ItemType newType = null;
    do {
      switch (typeCode) {
        case "AU":
          newType = ItemType.AUDIO;
          break;
        case "VI":
          newType = ItemType.VISUAL;
          break;
        case "AM":
          newType = ItemType.AUDIO_MOBILE;
          break;
        case "VM":
          newType = ItemType.VISUAL_MOBILE;
          break;
        default:
          isValid = true;
      }
    } while (isValid);

    return newType;
  }

  /**
   * Gets the itemType of this object.
   *
   * @return ItemType of this product.
   */
  public ItemType getType() {
    return type;
  }

  // --Commented out by Inspection START (11/9/2019 10:45 PM):
  //  /**
  //   * Sets the type for this product.
  //   *
  //   * @param type Takes the an ItemType object to set for this product.
  //   */
  //  public void setType(ItemType type) {
  //    this.type = type;
  //  }
  // --Commented out by Inspection STOP (11/9/2019 10:45 PM)

  // --Commented out by Inspection START (11/9/2019 10:52 PM):
  //  /**
  //   * Gets the id as an integer for this product.
  //   *
  //   * @return Returns the id of this product.
  //   */
  //  public int getId() {
  //    return this.id;
  //  }
  // --Commented out by Inspection STOP (11/9/2019 10:52 PM)

  // --Commented out by Inspection START (11/9/2019 10:45 PM):
  //  /**
  //   * Sets the id for this product.
  //   *
  //   * @param id Takes an integer and sets this as the id.
  //   */
  //  public void setId(int id) {
  //    this.id = id;
  //  }
  // --Commented out by Inspection STOP (11/9/2019 10:45 PM)

  // --Commented out by Inspection START (11/9/2019 11:15 PM):
  //  /**
  //   * Sets the name of this product.
  //   *
  //   * @param name Takes a String that will be assigned the name of the product.
  //   */
  //  public void setName(String name) {
  //    this.name = name;
  //  }
  // --Commented out by Inspection STOP (11/9/2019 11:15 PM)

  /**
   * Gets the name of this product.
   *
   * @return A string corresponding to the name of the product.
   */
  public String getName() {
    return this.name;
  }

  // --Commented out by Inspection START (11/9/2019 11:14 PM):
  //  /**
  //   * Sets the manufacturer of this product.
  //   *
  //   * @param manufacturer String that will be set as this product's manufacturer.
  //   */
  //  public void setManufacturer(String manufacturer) {
  //    this.manufacturer = manufacturer;
  //  }
  // --Commented out by Inspection STOP (11/9/2019 11:14 PM)

  /**
   * Gets the manufacturer of this product.
   *
   * @return Returns the manufacturer as a String.
   */
  public String getManufacturer() {
    return this.manufacturer;
  }

  /** */
  public int getProductId() {
    int prodID = 0;
    try {
      Connection conn = Controller.conn;
      Statement selectionStmt = conn.createStatement();
      String selection = "SELECT ID, NAME, TYPE, MANUFACTURER FROM PRODUCT";
      ResultSet selectRes = selectionStmt.executeQuery(selection);
      while (selectRes.next()) {
        if (this.getName().equals(selectRes.getString("NAME"))
            && this.getManufacturer().equals(selectRes.getString("MANUFACTURER"))) {
          prodID = selectRes.getInt("ID");
        }
      }
    } catch (Exception se) {
      se.printStackTrace();
    }
    return prodID;
  }

  /**
   * Converts the information of this Product into a String. Used to display information on this
   * product.
   *
   * @return Returns the products fields as a formatted string.
   */
  public String toString() {

    return "Name: "
        + this.name
        + "\nManufacturer: "
        + this.manufacturer
        + "\nType: "
        + this.type.toString();
  }
}
