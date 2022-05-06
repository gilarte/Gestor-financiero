package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Rafa.GestorFinanciero.model.Usuario;
import com.Rafa.GestorFinanciero.modelDAO.UsuarioDao;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {

	@FXML
	private void volver() throws IOException {
		App.setRoot("App");
	}

	@FXML
	private TextField Correo;
	@FXML
	private TextField Nombre;
	@FXML
	private TextField Contraseña;
	@FXML
	private TextField Saldo;

	/**
	 * Método que se encarga de añadir un usuario con los datos introducidos por el cliente a la tabla de usuarios 
	 */
	public void creaCuenta() {
		try {
			if (validaCorreo(Correo.getText()) && esDecimal(Saldo.getText()) && Contraseña.getText().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
				Double d = Double.parseDouble(Saldo.getText());
				Usuario aux = new Usuario(Correo.getText(), Nombre.getText(), Contraseña.getText(), d);
				UsuarioDao.insert(aux);
				alertAdd();
			} else {
				errorAdd();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Util.print("ERROR inesperado");
		}
	}

	/**
	 * Método que verifica que el correo introducido es válido
	 * @param email: correo que ha introducido el cliente
	 * @return true si es correcto el correo, false si no lo es
	 */
	public boolean validaCorreo(String email) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(email);

		if (mather.find() == true) {
			// correo válido
			valid = true;
		}
		return valid;
	}

	private void alertAdd() throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMACION");
		alert.setHeaderText("USUARIO AÑADIDO");
		alert.setContentText("El usuario se ha añadido correctamente");
		alert.show();
		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.toFront();
	}

	private void errorAdd() throws IOException {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("INFORMACION");
		alert.setHeaderText("USUARIO NO AÑADIDO");
		alert.setContentText(
						"Escribe un correo valido, el sueldo con un numero,\n por ejemplo, 500.25 o 500, y que la contraseña cumpla lo siguiente:\n"
						+ "Al menos 8 caracteres\r\n"
						+ "Contiene al menos un dígito\r\n"
						+ "Contiene al menos un carácter alfa inferior y un carácter alfa superior\r\n"
						+ "Contiene al menos un carácter dentro de un conjunto de\ncaracteres especiales (@#%$^ etc.)\r\n"
						+ "No contiene espacio, tabulador, etc.\r\n");
		alert.show();
		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.toFront();
	}

	//Devuelve true si la cadena que llega es un numero decimal, false en caso contrario
	public boolean esDecimal(String cad) {
		try {
			Double.parseDouble(cad);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
