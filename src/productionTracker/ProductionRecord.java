package productionTracker;

import java.util.Date;

public class ProductionRecord {
  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  ProductionRecord(int productID) {
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.dateProduced = dateProduced;
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
  }

  /**
   * @brief This constructor will be used to create a production record for a product and assign it
   *     a serial number.
   * @param newProduct, the Product that is being produced. Used to obtain fields for serial num
   * @param typeNum, the number of products of this type that have been produced.
   */
  ProductionRecord(Product newProduct, int typeNum) {
    String manu = newProduct.getManufacturer().substring(0, 3);
    String typeCode = newProduct.getType().code;
    String uniqueNum = convertTypeNum(typeNum);
    this.serialNumber = manu + typeCode + uniqueNum;
    this.productionNumber = 0;
    this.dateProduced = new Date();
  }

  /**
   * @brief convertTypeNum gets the number of items created in the corresponding type, and converts
   *     it for use in the serial number.
   * @return Returns the converted number padded with zeros as a string.
   */
  private String convertTypeNum(int typeNum) {
    // Pads the number typeNum with the amount of zeros corresponding to 5 - #of digits in typeNum
    return String.format("%5d", typeNum).replace(' ', '0');
  }

  public int getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public String toString(Product newProduct) {
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
