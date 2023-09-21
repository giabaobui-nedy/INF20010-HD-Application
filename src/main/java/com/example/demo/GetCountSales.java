package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GetCountSales {

    @FXML
    Text response;

    @FXML
    TextField day;

    @FXML
    private void getCountSales() {
        int numberOfDays;
        try {
            numberOfDays = Integer.parseInt(this.day.getText());
            response.setText(DatabaseConnection.callGetCountSales(numberOfDays));
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number!");
        } finally {
            this.day.setText("");
        }
    }
}
