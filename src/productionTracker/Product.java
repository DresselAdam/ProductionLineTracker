package productionTracker;

public abstract class Product implements Item {

  private int id;
  private itemType type;
  private String name;
  private String manufacturer;

  Product(String name, itemType type, String manufacturer) {
    this.name = name;
    this.type = type;
    this.manufacturer = manufacturer;
  }

    public itemType getType() {
        return type;
    }

    public void setType(itemType type) {
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
        "name: " + this.name + "\nManufacturer: " + this.manufacturer + "\nType: " + this.type.toString();
    return data;
  }
}
