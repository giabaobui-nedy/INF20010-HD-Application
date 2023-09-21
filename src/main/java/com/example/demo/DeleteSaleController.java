package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DeleteSaleController {

    @FXML
    Text response;

    @FXML
    private void deleteAllSales() {
        response.setText(DatabaseConnection.callDeleteAllSalesFromDB());
    }
}
