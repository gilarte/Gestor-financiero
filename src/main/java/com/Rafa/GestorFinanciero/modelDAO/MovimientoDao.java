package com.Rafa.GestorFinanciero.modelDAO;

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
	 */
	public boolean delete(String correo) {
		boolean result = false;
		Connection myConnection = Connect.getConnect();
		String query = "DELETE * FROM Movimientos WHERE Correo = " + correo;

		try {
			PreparedStatement sentence = myConnection.prepareStatement(query);
			sentence.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(Movimientos m) {
		boolean result = false;
		Connection myConnection = Connect.getConnect();
		String query = "UPDATE Film SET Title=?, Type=?, Duration=?, Year=?, Rating=? WHERE ID_F=?";

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

	public List<Movimientos> getAll() {
		List<Movimientos> list = new ArrayList<Movimientos>();
		Connection myConnection = Connect.getConnect();
		String query = "SELECT ID_F, Title, Type, Duration, Year, Rating FROM Film";

		try {
			Statement st = myConnection.createStatement();
			ResultSet rs = st.executeQuery(query);
			Movimientos aux = null;
			while (rs.next()) {
				aux = new Movimientos();
				aux.setCorreo(rs.getString(1));
				aux.setFecha(rs.getTimestamp(2).toLocalDateTime());
				aux.setCantidad(rs.getDouble(3));
				aux.setConcepto(rs.getString(4));
				list.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
