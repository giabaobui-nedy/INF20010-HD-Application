package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;


public class AddSimpleSaleController {
    @FXML
    TextField custId;
    @FXML
    TextField prodId;
    @FXML
    TextField quantity;
    @FXML
    Text response;

    public void addSimpleSale() {
        int custId;
        int prodId;
        int quantity;
        try {
            custId = Integer.parseInt(this.custId.getText());
            prodId = Integer.parseInt(this.prodId.getText());
            quantity = Integer.parseInt(this.quantity.getText());
            response.setText(DatabaseConnection.callAddSimpleSaleToDb(custId, prodId, quantity));
        } catch (NumberFormatException e) {
            response.setText("Invalid input. The inputs must be number");
        } finally {
            this.custId.setText("");
            this.prodId.setText("");
            this.quantity.setText("");
        }
    }
}
