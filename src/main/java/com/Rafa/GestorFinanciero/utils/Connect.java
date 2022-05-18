package com.Rafa.GestorFinanciero.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlTransient;

public class Connect {
	
	@XmlTransient
	private static Connection con;
	@XmlTransient
	private String file = "archivo.xml";
	@XmlTransient
	private static Connect _instance;

	private Connect() {
		//Cargamos los datos de la conexion del xml
		DatosConexion dc=load();
		//Establecemos la conexion
		try {
			con = DriverManager.getConnection(dc.getServer()+"/"+dc.getDatabase(),dc.getUsername(), dc.getPassword());
			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con=null;
		}
	}
	
	public static Connection getConnect() {
		if(_instance==null) {
			_instance = new Connect();
		}
		return con;
	}
	
	public DatosConexion load() {
		DatosConexion DatosCon=null;
		JAXBContext c;
		try {
			c=JAXBContext.newInstance(DatosConexion.class);
			Unmarshaller m = c.createUnmarshaller();
			DatosConexion newR= (DatosConexion) m.unmarshal (Connect.class.getResource("/com/Rafa/Conexion/archivo.xml"));
			DatosCon=newR;
		} catch (Exception e) {
			System.out.println(e);
		}
		return DatosCon;
	}
}
