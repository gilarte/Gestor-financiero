package com.Rafa.GestorFinanciero.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Util {

	/**
	 * Método que imprimer un String por consola
	 * 
	 * @param f: String que va a ser mostrado por consola
	 */
	public static void print(String f) {
		System.out.println(f);
	}

	/**
	 * Método que muestra una ventana de alerta
	 * 
	 * @param titulo  de la alerta
	 * @param header  de la alerta
	 * @param content de la alerta
	 * @throws IOException
	 */
	public static void alertAdd(String titulo, String header, String content) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.toFront();
	}

	/**
	 * Método que muestra una ventana de error
	 * 
	 * @param titulo  del error
	 * @param header  del error
	 * @param content del error
	 * @throws IOException
	 */
	public static void errorAdd(String titulo, String header, String content) throws IOException {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.toFront();
	}
	
	/**
	 * Muestra una ventana de confirmación
	 * @param titulo
	 * @param header
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static boolean confirmacion(String titulo, String header, String content) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		Stage s = (Stage) alert.getDialogPane().getScene().getWindow();
		s.toFront();
		
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==ButtonType.OK) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Método que pasa un String a un Double
	 * 
	 * @param cad: String que va a ser transformado en Double
	 * @return devuelve el Double de la cadena, o -1 si la cadena no contiene un
	 *         Double
	 */
	public static Double deStringaDecimal(String cad) {
		try {
			return Double.parseDouble(cad);
		} catch (NumberFormatException nfe) {
			return -1.0;
		}
	}

	/**
	 * Comprueba si la cadena introducida es un double
	 * 
	 * @param cad cadena que va a ser comprobada si contiene double
	 * @return true si contiene double, false si no
	 */
	public static boolean esDecimal(String cad) {
		try {
			Double.parseDouble(cad);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Método que verifica que el correo introducido es válido
	 * 
	 * @param email: correo que ha introducido el cliente
	 * @return true si es correcto el correo, false si no lo es
	 */
	public static boolean validaCorreo(String email) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(email);

		if (mather.find() == true) {
			// correo válido
			valid = true;
		}
		return valid;
	}

	public static boolean validateJavaDate(String strDate) {
		// Comprueba si el dato es nulo 
		if (strDate.trim().equals("")) {
			return false;
		}
		// El dato NO es nulo 
		else {
			/*
			 * Creo un formato para la fecha
			 */	
			SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
			sdfrmt.setLenient(false);
			      
			try {
				Date javaDate = sdfrmt.parse(strDate);
				System.out.println(strDate + " formato valido");
				 
			}
			// Formato invalido
			catch (Exception e) {
				System.out.println(e);
				System.out.println(strDate + " formato invalido");
				return false;
			}
			
			return true;
		}
	}
	
	/**
	 * Comprueba que un double es positivo
	 * @param x double que va a ser comprobado
	 * @return true si es positivo, false si no lo es
	 */
	public static boolean esPositivo(Double x) {
		if(x>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Comprueba que una cadena es un integer
	 * @param cad cadena que va a ser comprobada
	 * @return true si es un integer, false si no
	 */
	public static boolean esInteger(String cad) {
		try {
			Integer.parseInt(cad);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	/**
	public static String cifrar(String s) {
        String msg = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(s.getBytes(StandardCharsets.UTF_8));
            msg = toHexString(hash);
            System.out.println(toHexString(hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    private static String toHexString(byte[] array){
        StringBuilder sb = new StringBuilder(array.length*2);
        for (byte b: array){
            int value = 0xFF & b;
            String toAppend = Integer.toHexString(value);
            sb.append(toAppend);
        }
        sb.setLength(sb.length()-1);

        return sb.toString();
    }*/
}
