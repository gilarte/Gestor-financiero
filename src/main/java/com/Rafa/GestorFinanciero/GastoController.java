package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ResourceBundle;

import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.modelDAO.Gasto;
import com.Rafa.GestorFinanciero.modelDAO.Ingreso;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Loggers;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controlador de la vista App
 * 
 * @author Rafa Gilarte
 *
 */
public class GastoController implements Initializable {

	/**
	 * Variables introducidas por el usuario
	 */
	@FXML
	private Label nombre;
	@FXML
	private Label saldoCliente;
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
		saldoCliente.setText(String.valueOf(df.format(DataService.user.getDinero()) + " €"));
	}

	/**
	 * Refresca el salario
	 */
	public void cargaDatos() {
		this.saldoCliente.setText(DataService.user.getDinero() + " €");
	}

	/**
	 * Crea un nuevo movimiento en la base de datos controlando la validacion de
	 * formulario
	 * 
	 * @throws IOException
	 */
	@FXML
	private void crearGasto() throws IOException {

		Gasto g=new Gasto();
		if (fecha.getValue() != null) {
			System.out.println(fecha.getValue().toString());
			if (Util.validateJavaDate(fecha.getValue().toString())) {
				if (Util.esDecimal(cantidad.getText()) && Util.esPositivo(Util.deStringaDecimal(cantidad.getText()))) {
					if(Util.deStringaDecimal(this.cantidad.getText())<=DataService.user.getDinero()) {
						DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("uuu-MM-dd")
								.parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
								.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0).toFormatter();
						LocalDateTime fecha = LocalDateTime.parse(this.fecha.getValue().toString(), formatter);

						Movimientos m = new Movimientos(DataService.user.getCorreo(), fecha,
								Util.deStringaDecimal("-" + this.cantidad.getText()), this.concepto.getText());
						g.gastar(m, saldoCliente);
						this.cantidad.setText("");
						this.fecha.setValue(null);
						;
						this.concepto.setText("");
						Loggers.LogsInfo("Gasto añadido");
					}else {
						Util.errorAdd("ERROR", "INTRODUCE CANTIDAD VÁLIDA", "La cantidad introducida no puede\nser superior que el saldo actual");
						Loggers.LogsSevere("Gasto NO añadido, cantidad no válida");
					}
					
					
				} else {
					Util.errorAdd("ERROR", "INTRODUCE CANTIDAD VÁLIDA", "");
					Loggers.LogsSevere("Gasto NO añadido");
				}
			} else {
				Util.errorAdd("ERROR", "FECHA ERRÓNEA", "EL FORMATO DE LA FECHA NO ES VÁLIDO");
				Loggers.LogsSevere("Gasto NO añadido");
			}

		} else {
			Util.errorAdd("ERROR", "INTRODUCE FECHA", "");
			Loggers.LogsSevere("Gasto NO añadido");
		}
	}
}
