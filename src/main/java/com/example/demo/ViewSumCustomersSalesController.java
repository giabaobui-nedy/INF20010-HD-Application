package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewSumCustomersSalesController implements Initializable
{

    @FXML
    Text sum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sum.setText(DatabaseConnection.callSumCustomerSalesYtd());
    }
}
