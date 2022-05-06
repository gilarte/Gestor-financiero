package com.Rafa.GestorFinanciero.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Rafa.GestorFinanciero.model.Usuario;
import com.Rafa.GestorFinanciero.utils.Connect;
import com.Rafa.GestorFinanciero.utils.Util;

public class UsuarioDao {

	/**
	 * Inserta un usuario a la base de datos
	 * 
	 * @param u usuario que va a ser agregado
	 * @return true si se ha agregado, false si no
	 */
	public static boolean insert(Usuario u) {
		boolean result = false;
		Connection myConnection = Connect.getConnect();
		String query = "INSERT INTO usuario (Correo, Nombre, Contraseña, Dinero) VALUES (?,?,?,?)";

		try {
			PreparedStatement sentence = myConnection.prepareStatement(query);
			sentence.setString(1, u.getCorreo());
			sentence.setString(2, u.getNombre());
			sentence.setString(3, u.getContraseña());
			sentence.setDouble(4, u.getDinero());
			sentence.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Borra usuario a traves del correo electronico
	 * @param correo: correo del usuario que va a ser borrado
	 * @return true si se ha borrado, false si no
	 */
	public boolean delete(String correo) {
		boolean result = false;
		Connection myConnection = Connect.getConnect();
		String query = "DELETE FROM Usuario WHERE Correo = " + correo;

		try {
			PreparedStatement sentence = myConnection.prepareStatement(query);
			sentence.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Modifica un usuario para cambiar todos sus parámetros
	 * @param u usuario que va a ser modificado
	 * @return true si se ha modificado correctamente, false si no
	 */
	public boolean update(Usuario u) {
		boolean result = false;
		Connection myConnection = Connect.getConnect();
		String query = "UPDATE Usuario SET Nombre=?, Contraseña=?, Dinero=? WHERE Correo=?";

		try {
			PreparedStatement sentence = myConnection.prepareStatement(query);
			sentence.setString(1, u.getNombre());
			sentence.setString(2, u.getContraseña());
			sentence.setDouble(3, u.getDinero());
			sentence.setString(4, u.getCorreo());
			sentence.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Crea una lista en la que añade a todos los usuarios de la base de datos
	 * @return devuelve una lista con todos los usuarios
	 */
	public List<Usuario> getAll() {
		ArrayList<Usuario> list = new ArrayList<Usuario>();
		Connection myConnection = Connect.getConnect();
		String query = "SELECT Correo, Nombre, Contraseña, Dinero FROM usuario";

		try {
			Statement st = myConnection.createStatement();
			ResultSet rs = st.executeQuery(query);
			Usuario aux = null;
			while (rs.next()) {
				aux = new Usuario();
				aux.setCorreo(rs.getString(1));
				aux.setNombre(rs.getString(2));
				aux.setContraseña(rs.getString(3));
				aux.setDinero(rs.getDouble(4));
				list.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
