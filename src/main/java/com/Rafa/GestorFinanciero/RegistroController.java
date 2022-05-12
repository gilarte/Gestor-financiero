package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Rafa.GestorFinanciero.model.Usuario;
import com.Rafa.GestorFinanciero.modelDAO.UsuarioDao;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

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
			
				if (Util.validaCorreo(Correo.getText()) && Util.esDecimal(Saldo.getText()) && Util.deStringaDecimal(Saldo.getText())>=0 && Contraseña.getText().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
					//este if lo tengo que hacer con el search
					if(!UsuarioDao.search(this.Correo.getText())) {
						/**
						String p=Contraseña.getText();
						p=Util.cifrar(p);*/
						Double d = Double.parseDouble(Saldo.getText());
						Usuario aux = new Usuario(Correo.getText(), Nombre.getText(), Contraseña.getText(), d);
						
						UsuarioDao.insert(aux);
						Util.alertAdd("INFORMACION", "USUARIO AÑADIDO", "El usuario se ha añadido correctamente");
					}else {
						Util.errorAdd("ERROR", "ERROR EN EL CORREO", "El correo introducido, ya esta registrado en nuestra app!");
					}
					
				} else {
					Util.errorAdd("INFORMACION", "USUARIO NO AÑADIDO", "Escribe un correo valido, el sueldo con un numero,\n por ejemplo, 500.25 o 500, y que la contraseña cumpla lo siguiente:\n"
							+ "Al menos 8 caracteres\r\n"
							+ "Contiene al menos un dígito\r\n"
							+ "Contiene al menos un carácter alfa inferior y un carácter alfa superior\r\n"
							+ "Contiene al menos un carácter dentro de un conjunto de\ncaracteres especiales (@#%$^ etc.)\r\n"
							+ "No contiene espacio, tabulador, etc.\r\n");
				}
			
			
		} catch (Exception e) {
			Util.print("ERROR inesperado");
		}
	}

	


	
}
