module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.oracle.database.jdbc;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}