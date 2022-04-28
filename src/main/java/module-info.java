module com.Rafa.GestorFinanciero {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.Rafa.GestorFinanciero to javafx.fxml;
    exports com.Rafa.GestorFinanciero;
}
