module com.Rafa.GestorFinanciero {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires transitive java.desktop;
	requires java.xml.bind;
    
    opens com.Rafa.GestorFinanciero to javafx.fxml,java.xml.bind;
    opens com.Rafa.GestorFinanciero.utils to java.xml.bind;
    exports com.Rafa.GestorFinanciero;
}
