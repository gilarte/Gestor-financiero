package com.Rafa.GestorFinanciero.modelDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Rafa.GestorFinanciero.interfaces.IMovimientoDao;
import com.Rafa.GestorFinanciero.model.Movimientos;
import com.Rafa.GestorFinanciero.utils.Connect;
import com.Rafa.GestorFinanciero.utils.Util;

public class MovimientoDao implements IMovimientoDao{
	/**
	 * Inserta un nuevo movimiento en la tabla movimientos
	 * @param m movimiento que va a ser insertado
	 * @return devuelve true si lo ha insertado correctamente y false si no
	 */
	public boolean insertar(Movimientos m) {
		boolean result = false;
		Connection myConnection = Connect.getConnect();
		String query = "INSERT INTO Movimientos (Correo, Fecha, Cantidad, Concepto) VALUES (?,?,?,?)";

		try {
			PreparedStatement sentence = myConnection.prepareStatement(query);
			sentence.setString(1, m.getCorreo());
			sentence.setObject(2, m.getFecha());
			sentence.setDouble(3, m.getCantidad());
			sentence.setString(4, m.getConcepto());
			sentence.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Borra un movimiento de la tabla movimientos
	 * @param id
	 * @return
	 * @throws  
	 */
	public boolean delete(String id) {
		boolean result = false;

		if(Util.esInteger(id)) {
			Connection myConnection = Connect.getConnect();
			String query = "DELETE FROM Movimientos WHERE id = '" + id+"'";

			try {
				PreparedStatement sentence = myConnection.prepareStatement(query);
				sentence.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				Util.errorAdd("ERROR", "NO ES ENTERO EL ID", "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	/**
	 * Método que recibe un movimiento con el id movimiento que va a ser actualizado y sus nuevos datos 
	 */
	public boolean update(Movimientos m) {
		boolean result = false;
		Connection myConnection = Connect.getConnect();
		String query = "UPDATE movimientos SET Concepto=?, Cantidad=?, Fecha=? WHERE id='"+m.getId()+"'";

		try {
			PreparedStatement sentence = myConnection.prepareStatement(query);
			sentence.setString(1, m.getConcepto());
			sentence.setObject(3, m.getFecha());
			sentence.setDouble(2, m.getCantidad());
			sentence.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Método qe devuelve una lista de los Movimientos que haya realizado el usuario con el correo recibido
	 */
	public List<Movimientos> getAll(String correo) {
		List<Movimientos> list = new ArrayList<Movimientos>();
		Connection myConnection = Connect.getConnect();
		String query = "SELECT id, Correo, Fecha, Cantidad, Concepto FROM movimientos WHERE Correo='"+correo+"'";

		try {
			Statement st = myConnection.createStatement();
			ResultSet rs = st.executeQuery(query);
			Movimientos aux = null;
			while (rs.next()) {
				aux = new Movimientos();
				aux.setId(rs.getInt(1));
				aux.setCorreo(rs.getString(2));
				aux.setFecha(rs.getTimestamp(3).toLocalDateTime());
				aux.setCantidad(rs.getDouble(4));
				aux.setConcepto(rs.getString(5));
				list.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
