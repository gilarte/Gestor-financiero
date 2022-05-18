package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class InicioController implements Initializable{

	/**
	 * Elementos de la escena
	 */
	@FXML
	private Label Nombre;
	@FXML
	private Label saldo;
	
	/**
	 * Cambios de escena
	 * @throws IOException
	 */
	@FXML
    private void inicio() throws IOException {
        App.setRoot("Inicio");
    }
	@FXML
    private void ingresar() throws IOException {
        App.setRoot("Ingresar");
    }
	@FXML
    private void gasto() throws IOException {
        App.setRoot("Gasto");
    }
	@FXML
    private void historial() throws IOException {
        App.setRoot("Historial");
    }
	@FXML
    private void salir() throws IOException {
        App.setRoot("App");
    }
	@FXML
    private void modificarSaldo() throws IOException {
        App.setRoot("ModificarSalario");
    }
	
	/**
	 * Metodo que se ejecuta al cargar la escena
	 */
	public void initialize(URL location, ResourceBundle resources) {

		Nombre.setText(DataService.user.getNombre());
	    DecimalFormat df = new DecimalFormat("###.##");
		saldo.setText(String.valueOf(df.format(DataService.user.getDinero())+" €"));
		
	}
}
