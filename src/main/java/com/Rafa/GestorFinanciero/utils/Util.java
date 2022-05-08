package com.Rafa.GestorFinanciero.utils;

import java.io.IOException;

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
}
