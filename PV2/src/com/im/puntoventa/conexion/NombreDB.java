package com.im.puntoventa.conexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class NombreDB {
	public String obtenerNombreDb(){
//		leer UDL
		BufferedReader reader=null;
		String[] datos = new String[14];
		String[] datosObtenidos = new String[4];
		String[] clave = new String[2];
		String[] usuario = new String[2];
		String[] db = new String[2];
		String[] servidor = new String[2];
		String pass = "", user = "", bd = "", server = "";
		File direccion = new File("c:\\PuntoVenta\\millenium.udl");
		try {
			try {
				reader = new BufferedReader(
							new InputStreamReader(new FileInputStream(direccion), "UTF-16"));
			} catch (UnsupportedEncodingException e) {
				System.out.println("Encoding de archivo no soportada: " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Archivo no encontrado: " + e.getMessage());
		}
		while (true) {
		    String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("No se pudo Leer el archivo: " + e.getMessage());
			}
		    if (line == null) {
			break;
		    }
		    String[] parts = line.split(";");
		    int x = 0;
		    for (String part : parts) {
		    	datos[x] = part;
		    	x++;
		    }
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de Lectura de archivo: " + e.getMessage());
		}
		pass = datos[1];
		user = datos[3];
		bd = datos[4];
		server = datos[5];
		clave = pass.split("=");
		datosObtenidos [0] = clave[1];
		usuario = user.split("=");
		datosObtenidos [1] = usuario[1];
		db = bd.split("=");
		datosObtenidos [2] = db[1];
		servidor = server.split("=");
		datosObtenidos [3] = servidor[1];
		String nombreDB = null;
		nombreDB = db[1];
		return nombreDB;
	}
}
