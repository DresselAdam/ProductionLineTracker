package sample;

public abstract class Product implements Item {

  private int id;
  private String type;
  private String name;
  private String manufacturer;

  Product(String name, String type, String manufacturer) {
    this.name = name;
    this.type = type;
    this.manufacturer = manufacturer;
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
        "name: " + this.name + "\nManufacturer: " + this.manufacturer + "\nType: " + this.type;
    return data;
  }
}
