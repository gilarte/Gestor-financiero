package com.Rafa.GestorFinanciero;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.modelDAO.MovimientoDao;
import com.Rafa.GestorFinanciero.utils.DataService;

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

public class HistorialController implements Initializable{

	List<Movimientos> misMovimientos;
	MovimientoDao m = new MovimientoDao();
	
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
	private TableColumn<Movimientos, Double> cantidad;
	@FXML
	private TableColumn<Movimientos, String> concepto;
	@FXML
	private TableColumn<Movimientos, Integer> id;
	
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.nombre.setText(DataService.user.getNombre());
		this.saldo.setText(String.valueOf(DataService.user.getDinero()));
		this.error.setText("");
		
		this.conigureTable();
		misMovimientos = (List<Movimientos>) m.getAll();
		tabla.setItems(FXCollections.observableArrayList(misMovimientos));
	}
	
	public void conigureTable() {
		//ID
		id.setCellValueFactory(misMovimientos ->{
			ObservableValue<Integer> ov = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) ov).setValue(misMovimientos.getValue().getId());
			return ov;
		});
		//FECHA
		fecha.setCellValueFactory(misMovimientos ->{
				ObservableValue<LocalDateTime> ssp = new SimpleObjectProperty<LocalDateTime>();
				((ObjectProperty<LocalDateTime>) ssp).setValue(misMovimientos.getValue().getFecha());
				return ssp;
			});
		//Cantidad
		 cantidad.setCellValueFactory(misMovimientos ->{
				ObservableValue<Double> ov = new SimpleDoubleProperty().asObject();
				((ObjectProperty<Double>) ov).setValue(misMovimientos.getValue().getCantidad());
				return ov;
			});	
		 //Concepto
		 concepto.setCellValueFactory(misMovimientos ->{
				SimpleStringProperty ssp = new SimpleStringProperty();
				ssp.setValue(misMovimientos.getValue().getConcepto());
				return ssp;
			});
		
	}

}
