package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DeleteProductController {

    @FXML
    Text response;

    @FXML
    private void deleteAllProducts() {response.setText(DatabaseConnection.callDeleteAllProductFromDB());}
}
