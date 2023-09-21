package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GetProductController {

    @FXML
    TextArea response;

    @FXML
    TextField prodId;

    @FXML
    private void getCustomerFromDB() {
        int prodId;
        try {
            prodId = Integer.parseInt(this.prodId.getText());
            response.setText(DatabaseConnection.callGetProductStringFromDB(prodId));
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number!");
        } finally {
            this.prodId.setText("");
        }
    }
}
