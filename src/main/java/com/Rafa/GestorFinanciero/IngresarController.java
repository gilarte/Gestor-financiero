package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.modelDAO.Ingreso;
import com.Rafa.GestorFinanciero.modelDAO.MovimientoDao;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IngresarController implements Initializable{

	/**
	 * Variables introducidas por el usuario
	 */
	@FXML
	private Label nombre;
	@FXML
	public static Label saldo=new Label();
	@FXML
	private TextField cantidad;
	@FXML
	private DatePicker fecha;
	@FXML
	private TextArea concepto;
	
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
	
	public void initialize(URL location, ResourceBundle resources) {

		nombre.setText(DataService.user.getNombre());
		saldo.setText(String.valueOf(DataService.user.getDinero()+" €"));
		
	}
	
	
	@FXML
	private void crearIngreso() throws IOException {
		//Timestamp timestamp = Timestamp.valueOf());
		//Timestamp fec = Timestamp.valueOf(fecha.get)
		
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = fecha.getValue();
		localDate = localDateTime.toLocalDate();
		Timestamp fec = Timestamp.valueOf(localDateTime);
		
		Movimientos m=new Movimientos(DataService.user.getCorreo(), fec, Util.deStringaDecimal(this.cantidad.getText()), this.concepto.getText());
		if(m.getFecha()!=null) {
			if(Util.esDecimal(String.valueOf(m.getCantidad()))) {
				Ingreso.ingreso(m);
			}else {
				Util.errorAdd("ERROR", "INTRODUCE CANTIDAD VÁLIDA", "");
			}
		}else {
			Util.errorAdd("ERROR", "INTRODUCE FECHA VÁLIDA", "");
		}
	}
}
