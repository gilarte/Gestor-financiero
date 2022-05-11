package com.Rafa.GestorFinanciero;

import java.io.IOException;

import javafx.fxml.FXML;

public class AcercaDeController {

	/**
	 * Métodos que llaman a otras escenas
	 * @throws IOException
	 */
	@FXML
    private void volverApp() throws IOException {
        App.setRoot("App");
    }
	@FXML
    private void volverInicio() throws IOException {
        App.setRoot("Inicio");
    }
	@FXML
    private void volverRegistro() throws IOException {
        App.setRoot("Registro");
    }
	@FXML
    private void volverLogin() throws IOException {
        App.setRoot("Login");
    }
}
