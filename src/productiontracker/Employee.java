package productiontracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class employee is used for this program's login screen. All employees are created from here.
 * This class also verifies the formatting of user input.
 *
 * @author Adam Dressel
 */
public class Employee {
  public StringBuilder name;
  private String username;
  private String password;
  private String email;

  /**
   * This constructor is used to match the password entered by the user with the passwords contained
   * in the database.
   *
   * @param username The employee's username.
   * @param reversePass The employee's password how it is written in the database.
   */
  Employee(String username, String reversePass) {
    this.username = username;
    this.password = reversePass;
  }

  /**
   * This constructor is used in the create Account method for the creation of new employees. Calls
   * various check methods to ensure proper name and password are entered.
   *
   * @param name First and last name entered by the user.
   * @param password Password entered by the user.
   */
  Employee(StringBuilder name, String password) {
    this.name = name;
    if (checkName(name)) {
      setUsername(name);
      setEmail(name);
    } else {
      this.username = "default";
      this.email = "user@oracleacademy.Test";
    }
    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }

  /**
   * setUsername is called when the user's name passes the checkName method. The username is set by
   * formatting the Stringbuilder name.
   *
   * @param name Stringbuilder used for generation of username.
   */
  private void setUsername(StringBuilder name) {
    String newName = name.toString();
    String[] fullName = newName.split(" ");
    this.username = fullName[0].charAt(0) + fullName[1];
  }

  /**
   * checkName makes sure the name inputted by the user is two "names" separated by an underscore.
   *
   * @param name Name passed in to check format.
   * @return Returns boolean based on the match or lack thereof.
   */
  private boolean checkName(StringBuilder name) {
    String namePattern = "\\w+\\s\\w+";
    Pattern newPattern = Pattern.compile(namePattern);
    Matcher match = newPattern.matcher(name);
    if (match.matches()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Similar to setUsername. Uses the name to generate an email for the user.
   *
   * @param name String builder passed in for formatting.
   */
  private void setEmail(StringBuilder name) {
    String newName = name.toString();
    String[] fullName = newName.split(" ");
    this.email = fullName[0] + "." + fullName[1] + "@oracleacademy.Test";
  }

  /**
   * Generates three different regex to verify that the password entered matches the criteria.
   *
   * @param password Password entered that needs to be verified.
   * @return boolean based on whether all criteria are met.
   */
  private boolean isValidPassword(String password) {
    String upperCase = "[A-Z]";
    String lowerCase = "[a-z]";
    String specialChar = "\\W";

    Pattern upper = Pattern.compile(upperCase);
    Pattern lower = Pattern.compile(lowerCase);
    Pattern special = Pattern.compile(specialChar);

    Matcher hasUpper = upper.matcher(password);
    Matcher hasLower = lower.matcher(password);
    Matcher hasSpecial = special.matcher(password);

    if (hasLower.find() && hasUpper.find() && hasSpecial.find()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the name of this employee.
   *
   * @return A string builder object.
   */
  public StringBuilder getName() {
    return name;
  }

  /**
   * Gets the username of this employee.
   *
   * @return A string representing the username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Returns the password for this user.
   *
   * @return A string representing the password for this employee.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Returns the email for this employee.
   *
   * @return A string representing the email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Override of toString method to display the information of this employee.
   *
   * @return String value with employee details.
   */
  public String toString() {
    return "Employee Details \n"
        + "Name : "
        + this.name
        + "\nUsername : "
        + this.username
        + "\nEmail : "
        + this.email
        + "\nInitial Password : "
        + this.password;
  }

  /**
   * The reverse string method is used for storing the password to the database. The reversed
   * version of the password is stored.
   *
   * @param pw The string to be reversed
   * @return The reversed string
   */
  public String reverseString(String pw) {
    int length = pw.length();
    if (length == 0) {
      return pw;
    } else {
      return pw.charAt(length - 1) + reverseString(pw.substring(0, length - 1));
    }
  }
}
