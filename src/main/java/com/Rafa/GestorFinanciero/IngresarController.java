package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ResourceBundle;

import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.modelDAO.Ingreso;
import com.Rafa.GestorFinanciero.modelDAO.MovimientoDao;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Loggers;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IngresarController implements Initializable {

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
	 * 
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

	/**
	 * Metodo que se ejecuta al cargar la escena
	 */
	public void initialize(URL location, ResourceBundle resources) {

		nombre.setText(DataService.user.getNombre());
		DecimalFormat df = new DecimalFormat("###.##");
		saldo.setText(String.valueOf(df.format(DataService.user.getDinero()) + " €"));
	}

	/**
	 * Crea un nuevo movimiento en la base de datos controlando la validacion de
	 * formulario
	 * 
	 * @throws IOException
	 */
	@FXML
	private void crearIngreso() throws IOException {
		// Util.validateJavaDate(fecha.getValue().toString())
		Ingreso i=new Ingreso();
		if (fecha.getValue() != null) {
			System.out.println(fecha.getValue().toString());
			if (Util.validateJavaDate(fecha.getValue().toString())) {
				if (Util.esDecimal(cantidad.getText()) && Util.esPositivo(Util.deStringaDecimal(cantidad.getText()))) {

					DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("uuu-MM-dd")
							.parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
							.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0).toFormatter();
					LocalDateTime fecha = LocalDateTime.parse(this.fecha.getValue().toString(), formatter);

					Movimientos m = new Movimientos(DataService.user.getCorreo(), fecha,
							Util.deStringaDecimal(this.cantidad.getText()), this.concepto.getText());
					i.ingreso(m, this.saldo);
					this.cantidad.setText("");
					this.fecha.setValue(null);
					;
					this.concepto.setText("");
					Loggers.LogsInfo("Ingreso añadido");
				} else {
					Util.errorAdd("ERROR", "INTRODUCE CANTIDAD VÁLIDA", "");
					Loggers.LogsSevere("Ingreso NO añadido");
				}
			} else {
				Util.errorAdd("ERROR", "FECHA ERRÓNEA", "EL FORMATO DE LA FECHA NO ES VÁLIDO");
				Loggers.LogsSevere("Ingreso NO añadido");
			}

		} else {
			Util.errorAdd("ERROR", "INTRODUCE FECHA", "");
			Loggers.LogsSevere("Ingreso NO añadido");
		}
	}
}
