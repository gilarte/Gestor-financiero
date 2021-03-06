package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.JOptionPane;

import com.Rafa.GestorFinanciero.model.Usuario;
import com.Rafa.GestorFinanciero.modelDAO.UsuarioDao;
import com.Rafa.GestorFinanciero.utils.Connect;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Loggers;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

	/**
	 * Métodos que llaman a otras escenas
	 * @throws IOException
	 */
	@FXML
    private void volver() throws IOException {
        App.setRoot("App");
    }
	@FXML
    private void inicio() throws IOException {
        App.setRoot("Inicio");
    }
	@FXML
    private void Login() throws IOException {
        App.setRoot("OlvidaContraseña");
    }
	
	/**
	 * Variables de la escena Login
	 */
	@FXML
	private TextField Correo;
	@FXML
	private TextField Contrasena;
	
	/**
	 * Método que comprueba que el login ha funcionado correctamente
	 * @throws IOException
	 */
	@FXML
	public void validaContraseña() throws IOException {
		String correo=Correo.getText();
		String contrasena=DigestUtils.sha256Hex(Contrasena.getText());
		Usuario aux=UsuarioDao.identificar(correo, contrasena);
		if(aux==null) {
			Util.errorAdd("ALERTA", "USUARIO NO ENCONTRADO", "El correo introducido o la contraseña son incorrectos.");
			Loggers.LogsSevere("No se ha podido iniciar sesión");
		}else {
			/*String p=Contrasena.getText();
			p=Util.cifrar(p);
			aux.setContraseña(p);*/
			DataService.user=aux;
			Util.alertAdd("LOGIN CORRECTO", "¡BIENVENIDO!", "Has iniciado sesion como "+DataService.user.getNombre());
			App.setRoot("Inicio");
			Loggers.LogsInfo("Sesión iniciada con el usuario "+DataService.user.getNombre());
		}
		
	}


}
