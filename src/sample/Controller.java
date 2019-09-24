package sample;

import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField productNameTxt;
    @FXML
    private Button productLineBtn;
    @FXML
    private Label manufacturerLbl;



    public void addProd(ActionEvent clicked) {
        System.out.println("This is the product line");

    }

    public void recProd(ActionEvent clicked) {
        System.out.println("Record Production");
    }

}
