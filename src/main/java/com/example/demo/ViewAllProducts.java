package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewAllProducts implements Initializable {
    @FXML TextArea response;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        response.setText(DatabaseConnection.callGetAllProduct());
    }
}
