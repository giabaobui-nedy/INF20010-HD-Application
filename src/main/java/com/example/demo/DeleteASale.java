package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DeleteASale {

    @FXML
    Text response;

    @FXML
    private void deleteASale() {
        response.setText(DatabaseConnection.callDeleteMinSale());
    }
}
