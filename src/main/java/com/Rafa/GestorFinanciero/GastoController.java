package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ResourceBundle;

import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.modelDAO.Gasto;
import com.Rafa.GestorFinanciero.modelDAO.Ingreso;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GastoController {

	/**
	 * Variables introducidas por el usuario
	 */
	@FXML
	private Label nombre;
	@FXML
	private Label saldo;
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
	private void crearGasto() throws IOException {
		
		if(this.fecha!=null) {
			if(Util.esDecimal(cantidad.getText())) {
				DateTimeFormatter formatter = new DateTimeFormatterBuilder()
						.appendPattern("uuu-MM-dd")
						.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
						.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
						.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
						.toFormatter();
				LocalDateTime fecha = LocalDateTime.parse(this.fecha.getValue().toString(), formatter);
				
				Movimientos m=new Movimientos(DataService.user.getCorreo(), fecha, Util.deStringaDecimal(this.cantidad.getText()), this.concepto.getText());
				Gasto.gastar(m, this.saldo);
				//if(Gasto.gastar(m, this.saldo)) {
					//Util.alertAdd("BIEN", "", "BIEN");
				//}
			}else {
				Util.errorAdd("ERROR", "INTRODUCE CANTIDAD VÁLIDA", "");
			}
		}else {
			Util.errorAdd("ERROR", "INTRODUCE FECHA VÁLIDA", "");
		}
	}
}