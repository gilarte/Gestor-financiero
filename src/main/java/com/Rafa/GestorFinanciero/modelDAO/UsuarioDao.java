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

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

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
	 * 
	 * @param correo: correo del usuario que va a ser borrado
	 * @return true si se ha borrado, false si no
	 */
	public static boolean delete(String correo) {
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
	 * 
	 * @param u usuario que va a ser modificado
	 * @return true si se ha modificado correctamente, false si no
	 */
	public static boolean update(Usuario u) {
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
	 * 
	 * @return devuelve una lista con todos los usuarios
	 */
	public static List<Usuario> getAll() {
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

	/**
	 * Método que comprueba si el correo y la contraseña introducidos coinciden con un usuario de la base de datos
	 * @param correo: correo del usuario
	 * @param contrasena: contraseña del usuario
	 * @return Devuelve el usuario perteneciente a ese correo y contraseña, o null si no ha encontrado el usuario con
	 * dichos parámetros
	 */
	public static Usuario identificar(String correo, String contrasena) {
		Connection myConnection = Connect.getConnect();
		String query = "SELECT Correo, Nombre, Contraseña, Dinero from usuario WHERE Correo='" + correo
				+ "' AND Contraseña='" + contrasena + "'";
		Usuario iniciado = null;
		Statement st;
		try {
			st = myConnection.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				iniciado = new Usuario();
				iniciado.setCorreo(rs.getString(1));
				iniciado.setNombre(rs.getString(2));
				iniciado.setContraseña(rs.getString(3));
				iniciado.setDinero(rs.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("INFORMACION");
			alert.setHeaderText("ERROR createStatement");
			alert.setContentText(correo+contrasena);
			alert.show();
			Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
			s.toFront();
		}

		return iniciado;
	}
	
	/**
	 * Método que comprueba si un correo existe en la base de datos o no
	 * @param correo: correo que va a ser buscado en la base de datos
	 * @return true si existe, false si no
	 */
	public static boolean search(String correo) {
		boolean valid=false;
		Connection myConnection = Connect.getConnect();
		String query = "SELECT Correo, Nombre, Contraseña, Dinero FROM usuario WHERE correo='"+correo+"'";
		Usuario aux=null;
		Statement st;

		try {
			st = myConnection.createStatement();
			ResultSet rs = st.executeQuery(query);
			aux=new Usuario();
			aux.setCorreo(rs.getString(1));
			if(aux.getCorreo()==correo) {
				valid=true;;
			}
		} catch (Exception e) {
			valid=false;
		}
		return valid;
	}
}
