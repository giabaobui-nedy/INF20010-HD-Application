package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene applicationScene = new Scene(fxmlLoader.load(), 1000, 700);
        VBox parent = (VBox) applicationScene.getRoot();

        registerMenuItem(parent, 0, 0, "add-cust.fxml");
        registerMenuItem(parent, 0, 1, "delete-all-custs.fxml");
        registerMenuItem(parent, 0, 2, "get-cust.fxml");
        registerMenuItem(parent, 0, 3, "upd-cust-sales.fxml");
        registerMenuItem(parent, 0, 4, "upd-cust-status.fxml");
        registerMenuItem(parent, 0, 5, "view-sum-cust-sales.fxml");
        registerMenuItem(parent, 0, 6, "view-all-customers.fxml");
        registerMenuItem(parent, 0, 7, "delete-a-customer.fxml");
        registerMenuItem(parent, 1, 0, "add-prod.fxml");
        registerMenuItem(parent, 1, 1, "delete-all-prods.fxml");
        registerMenuItem(parent, 1, 2, "get-prod.fxml");
        registerMenuItem(parent, 1, 3, "upd-prod-sales.fxml");
        registerMenuItem(parent, 1, 4, "view-all-products.fxml");
        registerMenuItem(parent, 1, 5, "delete-a-product.fxml");
        registerMenuItem(parent, 2, 0, "add-simple-sale.fxml");
        registerMenuItem(parent, 2, 1, "add-complex-sale.fxml");
        registerMenuItem(parent, 2, 2, "view-all-sales.fxml");
        registerMenuItem(parent, 2, 3, "get-count-sales.fxml");
        registerMenuItem(parent, 2, 4, "delete-a-sale.fxml");
        registerMenuItem(parent, 2, 5, "delete-all-sales.fxml");


        stage.setTitle("HD Demo App");
        stage.setScene(applicationScene);
        stage.show();
    }

    private void registerMenuItem(VBox parent, Integer menuIndex, Integer menuItemIndex, String url) {
        getMenuItem(parent, menuIndex, menuItemIndex).setOnAction(e ->
        {
            openWindow(parent, url);
        });
    }

    private MenuItem getMenuItem(VBox parent, Integer menuIndex, Integer menuItemIndex) {
        MenuItem menuItem;
        BorderPane menuContainer = (BorderPane) parent.getChildren().get(0);
        MenuBar menuBar = (MenuBar) menuContainer.getChildren().get(0);
        Menu menu = menuBar.getMenus().get(menuIndex);
        menuItem = menu.getItems().get(menuItemIndex);
        return menuItem;
    }

    private void openWindow(VBox parent, String url) {
        if (parent.getChildren().size() > 1) {
            parent.getChildren().remove(1);
        }
        try {
            parent.getChildren().add((new FXMLLoader(HelloApplication.class.getResource(url))).load());
        } catch (IOException ex) {
            System.out.println("Fail to open: " + url);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}