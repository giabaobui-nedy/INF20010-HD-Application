package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddProductController {

    @FXML
    TextField price;

    @FXML
    TextArea response;

    @FXML
    TextField prodId;

    @FXML
    TextField prodName;

    @FXML
    private void addProductToDB() {
        int prodId;
        String prodName;
        float price;
        try {
            prodId = Integer.parseInt(this.prodId.getText());
            prodName = this.prodName.getText();
            try {
                price = Float.parseFloat(this.price.getText());
                response.setText(DatabaseConnection.callAddProductToDB(prodId, prodName, price));
            } catch (NumberFormatException e) {
                this.response.setText("Please enter a valid price!");
            }
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number!");
        } finally {
            this.prodId.setText("");
            this.prodName.setText("");
            this.price.setText("");
        }
    }
}