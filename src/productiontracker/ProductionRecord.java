package productiontracker;

import java.util.Date;

/**
 * Production Record class is used to keep log of produced items.
 *
 * @author Adam Dressel
 */
class ProductionRecord {
  // Soon: increment unique serial number based on item types in database.
  private final int productionNumber;
  private int productID;
  private final String serialNumber;
  private final Date dateProduced;

  @SuppressWarnings("unused")
  ProductionRecord(int productID) {
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  // --Commented out by Inspection START (11/9/2019 10:46 PM):
  //  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced)
  // {
  //    this.dateProduced = dateProduced;
  //    this.productionNumber = productionNumber;
  //    this.productID = productID;
  //    this.serialNumber = serialNumber;
  //  }
  // --Commented out by Inspection STOP (11/9/2019 10:46 PM)

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
    String uniqueNum = convertTypeNum(typeNum);
    this.serialNumber = manu + typeCode + uniqueNum;
    this.productionNumber = 0;
    this.dateProduced = new Date();
  }

  /**
   * Method gets the number of items created in the corresponding type, and converts it for use in
   * the serial number.
   *
   * @return Returns the converted number padded with zeros as a string.
   */
  private String convertTypeNum(int typeNum) {
    // Pads the number typeNum with the amount of zeros corresponding to 5 - #of digits in typeNum
    return String.format("%5d", typeNum).replace(' ', '0');
  }

  /**
   * Will convert the product record information into a displayable format.
   *
   * @param newProduct Product whose methods will be used to get corresponding field values
   * @return Will return the formatted String to caller.
   */
  String toString(Product newProduct) {
    return "Product Name: "
        + newProduct.getName()
        + " Product ID: "
        + this.productID
        + " Serial Num: "
        + this.serialNumber
        + " Date: "
        + this.dateProduced;
  }
}
