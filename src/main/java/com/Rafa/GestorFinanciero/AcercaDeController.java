package com.Rafa.GestorFinanciero;

import java.io.IOException;

import com.Rafa.GestorFinanciero.utils.Loggers;

import javafx.fxml.FXML;

/**
 * Controlador de la vista AcercaDe
 * @author Rafa Gilarte
 *
 */
public class AcercaDeController {

	/**
	 * Métodos que llaman a otras escenas
	 * @throws IOException
	 */
	@FXML
    private void volverApp() throws IOException {
        App.setRoot("App");
        Loggers.LogsInfo("La escena ha cambiado a App");
    }
	@FXML
    private void volverInicio() throws IOException {
        App.setRoot("Inicio");
        Loggers.LogsInfo("La escena ha cambiado a Inicio");
    }
	@FXML
    private void volverRegistro() throws IOException {
        App.setRoot("Registro");
        Loggers.LogsInfo("La escena ha cambiado a Registros");
    }
	@FXML
    private void volverLogin() throws IOException {
        App.setRoot("Login");
        Loggers.LogsInfo("La escena ha cambiado a Login");
    }
}
