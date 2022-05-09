package com.Rafa.GestorFinanciero.modelDAO;

import java.io.IOException;
import java.sql.Connection;

import com.Rafa.GestorFinanciero.IngresarController;
import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.utils.Connect;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Util;

public abstract class Ingreso extends MovimientoDao {

	public static boolean ingreso(Movimientos m) throws IOException {
		boolean result = false;
		MovimientoDao dao=new MovimientoDao();
		if (Util.esDecimal(String.valueOf(m.getCantidad()))) {
			if (UsuarioDao.cambiarSaldo(m.getCorreo(), DataService.user.getDinero() + m.getCantidad())) {
				if (dao.insertar(m)) {
					result = true;
					try {
						
						IngresarController.saldo.setText(String.valueOf(DataService.user.getDinero()));
						Util.alertAdd("INGRESO", "INGRESO REALIZADO CON ÉXITO!", "");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Util.print("Error en el ingreso");
					}
				}else {
					Util.errorAdd("ERROR BBDD", "ERROR AL INSERTA MOVIMIENTO EN LA BBDD", "");
				}
				
			} else {
				Util.errorAdd("FALLO", "EL SALDO NO SE HA PODIDO CAMBIAR", "");
			}
		} else {
			Util.errorAdd("ERROR", "CANTIDAD INVÁLIDA", "");
		}

		return result;
	}

}
