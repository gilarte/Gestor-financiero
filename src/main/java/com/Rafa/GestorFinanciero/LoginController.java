package com.Rafa.GestorFinanciero;

import java.io.IOException;

import javafx.fxml.FXML;

public class LoginController {

	@FXML
    private void volver() throws IOException {
        App.setRoot("App");
    }
	@FXML
    private void inicio() throws IOException {
        App.setRoot("Inicio");
    }
}
