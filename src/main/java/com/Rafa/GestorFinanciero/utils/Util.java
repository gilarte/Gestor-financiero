package com.Rafa.GestorFinanciero.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Util {

	public static void print(String f) {
		System.out.println(f);
	}
	
	public static void alertAdd(String titulo, String header, String content) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.toFront();
	}
	
	public static void errorAdd(String titulo, String header, String content) throws IOException {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.toFront();
	}
	
	public static Double deStringaDecimal(String cad) {
		try {
			return Double.parseDouble(cad);
		} catch (NumberFormatException nfe) {
			return -1.0;
		}
	}
	
	public static boolean esDecimal(String cad) {
		try {
			Double.parseDouble(cad);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	/**
	 * Método que verifica que el correo introducido es válido
	 * @param email: correo que ha introducido el cliente
	 * @return true si es correcto el correo, false si no lo es
	 */
	public static boolean validaCorreo(String email) {
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
}
