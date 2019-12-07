package productiontracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
  public StringBuilder name;
  private String username;
  private String password;
  private String email;

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

  private void setUsername(StringBuilder name) {
    String newName = name.toString();
    String[] fullName = newName.split(" ");
    this.username = fullName[0].charAt(0) + fullName[1];
  }

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

  private void setEmail(StringBuilder name) {
    String newName = name.toString();
    String[] fullName = newName.split(" ");
    this.username = fullName[0] + "." + fullName[1] + "@oracleacademy.Test";
  }

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

  public String reverseString(String pw) {
    int length = pw.length();
    if(length == 0){
      return pw;
    }
    else{
      return pw.charAt(length - 1) + reverseString(pw.substring(0, length - 1));
    }
  }
}
