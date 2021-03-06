package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import com.Rafa.GestorFinanciero.modelDAO.UsuarioDao;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Loggers;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModificarSalarioController implements Initializable{
	@FXML
	private Label Nombre;
	@FXML
	private Label saldo;
	@FXML
	private TextField newSaldo;
	
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
	 * Método que se ejecuta al iniciar la escena
	 */
	public void initialize(URL location, ResourceBundle resources) {

		Nombre.setText(DataService.user.getNombre());
		DecimalFormat df = new DecimalFormat("###.##");
		saldo.setText(String.valueOf(df.format(DataService.user.getDinero()) + " €"));		
	}
	
	/**
	 * Método que modifica el saldo del cliente que ha iniciado sesión
	 * @throws IOException
	 */
	@FXML
	private void nuevoSaldo() throws IOException {
		if(Util.esDecimal(newSaldo.getText()) && Util.deStringaDecimal(newSaldo.getText())>=0) {
			if(UsuarioDao.cambiarSaldo(DataService.user.getCorreo(), Double.parseDouble(newSaldo.getText()))) {
				DataService.user.setDinero(Double.parseDouble(newSaldo.getText()));
				DecimalFormat df = new DecimalFormat("###.##");
				saldo.setText(String.valueOf(df.format(DataService.user.getDinero()) + " €"));
				Util.alertAdd("Saldo cambiado", "Saldo cambiado con éxito!", "La modificación del saldo no cuenta como un movimiento,\n por lo que NO será registrada en el historial.");
				this.newSaldo.setText("");
				Loggers.LogsInfo("Saldo cambiado");
			}else {
				Util.errorAdd("ERROR", "Fallo al cambiar en la base de datos", "El valor no ha sido cambiado en la base de datos");
				Loggers.LogsSevere("Saldo NO cambiado");
			}
			
		}else {
			Util.errorAdd("ERROR", "Cantidad inválida", "El valor introducido no es un número válido");
			Loggers.LogsSevere("Saldo NO cambiado");
		}
		
	}
	
}
