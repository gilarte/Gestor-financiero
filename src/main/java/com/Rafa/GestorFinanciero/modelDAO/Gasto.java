package com.Rafa.GestorFinanciero.modelDAO;

import java.io.IOException;
import java.text.DecimalFormat;

import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Loggers;
import com.Rafa.GestorFinanciero.utils.Util;

import javafx.scene.control.Label;

public class Gasto extends MovimientoDao{

	/**
	 * Método que valida y crea un gasto añadiendo un nuevo movimiento a la bbdd
	 * @param m: movimiento que va a ser añadido
	 * @param saldo: Label donde se guarda el saldo, para modificar su valor al realizar un gasto
	 * @return true si se ha realizado el gasto correctamente, false si no
	 * @throws IOException
	 */
	public boolean gastar(Movimientos m, Label saldo) throws IOException {
		boolean result = false;
		if (Util.esDecimal(String.valueOf(m.getCantidad()))) {
			if (UsuarioDao.cambiarSaldo(m.getCorreo(), (DataService.user.getDinero() + m.getCantidad()))) {
				if (insertar(m)) {
					result = true;
					try {
						
						DecimalFormat df = new DecimalFormat("###.##");
						saldo.setText(String.valueOf(df.format(DataService.user.getDinero()) + " €"));
						Util.alertAdd("GASTO", "GASTO REALIZADO CON ÉXITO!", "");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Util.print("Error en el ingreso");
					}
				}else {
					Util.errorAdd("ERROR BBDD", "ERROR AL INSERTA MOVIMIENTO EN LA BBDD", "");
					Loggers.LogsSevere("Fallo en BBDD");
				}
				
			} else {
				Util.errorAdd("FALLO", "EL SALDO NO SE HA PODIDO CAMBIAR", "");
				Loggers.LogsSevere("Gasto NO añadido");
			}
		} else {
			Util.errorAdd("ERROR", "CANTIDAD INVÁLIDA", "");
			Loggers.LogsSevere("Gasto NO añadido");
		}

		return result;
	}

}
