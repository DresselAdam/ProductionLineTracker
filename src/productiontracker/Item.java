package productiontracker;

/**
 * Interface implemented by the Product object. Ensures every product has a way to access and set
 * its name, manufacturer and id.
 *
 * @author Adam Dressel
 */
interface Item {

  int getId();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();
}
