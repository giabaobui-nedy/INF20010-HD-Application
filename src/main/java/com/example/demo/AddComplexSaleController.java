package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddComplexSaleController {
    @FXML
    TextField custId;

    @FXML
    TextField prodId;

    @FXML
    TextField quantity;

    @FXML
    TextField date;

    @FXML
    Text response;

    public void addComplexSale() {
        int custId;
        int prodId;
        int quantity;
        String date;
        try {
            custId = Integer.parseInt(this.custId.getText());
            prodId = Integer.parseInt(this.prodId.getText());
            quantity = Integer.parseInt(this.quantity.getText());
            date = this.date.getText();
            response.setText(DatabaseConnection.callAddComplexSale(custId, prodId, quantity, date));
        } catch (NumberFormatException e) {
            response.setText("Invalid input. The inputs must be number");
        } finally {
            this.custId.setText("");
            this.prodId.setText("");
            this.quantity.setText("");
            this.date.setText("");
        }
    }
}
