package com.Rafa.GestorFinanciero;

import java.io.IOException;

import javafx.fxml.FXML;

public class RegistroController {

	@FXML
    private void volver() throws IOException {
        App.setRoot("App");
    }
	//Hacer ventana emergente que diga si el usuario se ha logueado o no. 
	//Si se ha logueado, que salga una ventana diciendo que esta logueado y cuando cierre la ventana con un boton que lleve al inicio de sesion.
	//Si no se ha logueado correctamente, que salga una ventana de error y cuando se cierre que lleve al registro otra vez.
	//Hacer ventana de "Acerca de" y de "help", esta ultima personalizada para cada escena.
}
