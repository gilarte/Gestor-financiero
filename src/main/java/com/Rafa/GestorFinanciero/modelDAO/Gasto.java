package com.Rafa.GestorFinanciero.modelDAO;

import java.io.IOException;

import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.utils.DataService;
import com.Rafa.GestorFinanciero.utils.Util;

public class Gasto extends MovimientoDao{

	public boolean gasto(Movimientos m) {
		boolean result = false;
		if(UsuarioDao.cambiarSaldo(m.getCorreo(), DataService.user.getDinero()-m.getCantidad())) {
			result=true;
			try {
				Util.alertAdd("GASTO", "GASTO REALIZADO CON ÉXITO!", "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
