package com.Rafa.GestorFinanciero;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.modelDAO.MovimientoDao;
import com.Rafa.GestorFinanciero.modelDAO.UsuarioDao;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Loggers;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistorialController implements Initializable {

	List<Movimientos> misMovimientos;
	MovimientoDao m = new MovimientoDao();

	/**
	 * Elementos de la escena
	 */
	@FXML
	private Label nombre;
	@FXML
	private Label saldo;
	@FXML
	private Label error;
	@FXML
	private Label idBorrar;
	@FXML
	private TableView<Movimientos> tabla;
	@FXML
	private TableColumn<Movimientos, LocalDateTime> fecha;
	@FXML
	private TableColumn<Movimientos, String> cantidad;
	@FXML
	private TableColumn<Movimientos, String> concepto;
	@FXML
	private TableColumn<Movimientos, Integer> id;

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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.nombre.setText(DataService.user.getNombre());
		DecimalFormat df = new DecimalFormat("###.##");
		saldo.setText(String.valueOf(df.format(DataService.user.getDinero()) + " ???"));
		this.error.setText("");
		this.idBorrar.setText("");

		this.conigureTable();
		misMovimientos = (List<Movimientos>) m.getAll(DataService.user.getCorreo());
		tabla.setItems(FXCollections.observableArrayList(misMovimientos));
		tabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestraInfo(newValue);
		});
	}

	/**
	 * M??todo que carga el TableView
	 */
	public void conigureTable() {
		// ID
		id.setCellValueFactory(misMovimientos -> {
			ObservableValue<Integer> ov = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) ov).setValue(misMovimientos.getValue().getId());
			return ov;
		});
		// FECHA
		fecha.setCellValueFactory(misMovimientos -> {
			ObservableValue<LocalDateTime> ssp = new SimpleObjectProperty<LocalDateTime>();
			((ObjectProperty<LocalDateTime>) ssp).setValue(misMovimientos.getValue().getFecha());
			return ssp;
		});
		// Cantidad
		
		cantidad.setCellValueFactory(misMovimientos -> {
			DecimalFormat df = new DecimalFormat("###.##");
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(df.format(misMovimientos.getValue().getCantidad()));
			return ssp;
		});
		// Concepto
		concepto.setCellValueFactory(misMovimientos -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(misMovimientos.getValue().getConcepto());
			return ssp;
		});

	}

	/**
	 * Muestra en el label la id del movimiento seleccionado en la tabla
	 * 
	 * @param p Movimiento seleccionado
	 */
	private void muestraInfo(Movimientos p) {

		if (p != null) {
			idBorrar.setText(Integer.toString(p.getId()));

		} else {
			idBorrar.setText("");
		}
	}

	/**
	 * Borra el movimiento que tiene la id del label
	 * 
	 * @throws IOException
	 */
	public void borraMov() throws IOException {
		MovimientoDao m = new MovimientoDao();
		if (Util.esInteger(this.idBorrar.getText())) {
			Util.alertAdd("MOVIMIENTO BORRADO", "MOVIMIENTO BORRARDO",
					"el movimiento con la id " + this.idBorrar.getText() + " ha sido borrado");
			m.delete(this.idBorrar.getText());
			App.setRoot("Historial");
			Loggers.LogsInfo("Movimiento borrado");
		} else {
			this.error.setText("Selecciona un movimiento para borrarlo");
			Loggers.LogsSevere("Movimiento no borrado");
		}
	}

}
