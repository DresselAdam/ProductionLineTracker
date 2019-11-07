package productionTracker;

public class Product implements Item {

  private int id;
  private ItemType type;
  private String name;
  private String manufacturer;

  Product(String name, ItemType type, String manufacturer) {
    this.name = name;
    this.type = type;
    this.manufacturer = manufacturer;
  }

  Product(String name, String typeCode, String manufacturer) {
    this.name = name;
    this.type = filterType(typeCode);
    this.manufacturer = manufacturer;
  }

  /**
   * @param typeCode gets the code passed into a constructor and uses it to determine the itemType
   * @return The corresponding itemType related to the code
   */
  public ItemType filterType(String typeCode) {
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

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return this.manufacturer;
  }

  public String toString() {
    String data =
        "name: "
            + this.name
            + "\nManufacturer: "
            + this.manufacturer
            + "\nType: "
            + this.type.toString();
    return data;
  }
}
