package com.Rafa.GestorFinanciero;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class AppController {
	   @FXML
	    private void login() throws IOException {
		   App.setRoot("Login");
		   }
	   @FXML
	    private void registro() throws IOException {
	        App.setRoot("Registro");
	    }
	   @FXML
	    private void acercaDe() throws IOException {
	        App.setRoot("AcercaDe");
	    }
	   
}
