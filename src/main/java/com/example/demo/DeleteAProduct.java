package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DeleteAProduct {

    @FXML
    Text response;

    @FXML
    TextField prodId;

    @FXML
    private void deleteAProduct() {
        int prodId;
        try {
            prodId = Integer.parseInt(this.prodId.getText());
            response.setText(DatabaseConnection.callDeleteAProductFromDB(prodId));
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number!");
        } finally {
            this.prodId.setText("");
        }
    }
}
