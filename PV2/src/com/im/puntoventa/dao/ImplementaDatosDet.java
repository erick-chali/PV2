package com.im.puntoventa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.im.puntoventa.conexion.ConectarDB;
import com.im.puntoventa.datos.DatosDet;

public class ImplementaDatosDet implements InterfazDatosDet{
	
	public static String unidad;
	@Override
	public DatosDet obtenerDatos(DatosDet datos) {
		// TODO Auto-generated method stub
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_obtenerMedida(?,?)}");
			stmt.setString(1, datos.getCodigoProducto());
			stmt.setInt(2, 1);
			rs = stmt.executeQuery();
			if(rs==null){
				unidad = "25";
			}
			while(rs.next()){
				unidad = rs.getString("unidad_medida");
			}
			con.close();
//			stmt.close();
//			rs.close();
			stmt = null;
			rs = null;
			con = new ConectarDB().getConnection();
//			System.out.println(1);
			stmt = con.prepareCall("{call stp_UDPV_InUp_Mov_Det(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//			System.out.println(2);
			stmt.setInt(1, Integer.parseInt(datos.getTipoDoc()));
//			System.out.println(3);
			stmt.setString(2, datos.getSerieDoc());
//			System.out.println(4);
			stmt.setString(3, datos.getNoDoc());
//			System.out.println(5);
			stmt.setInt(4, Integer.parseInt(datos.getCorrelativo()));
//			System.out.println(6);
			stmt.setString(5, datos.getCodigoProducto());
//			System.out.println(7);
			stmt.setString(6, unidad);
//			System.out.println(8);
			stmt.setInt(7, Integer.parseInt(datos.getCantidad()));
//			System.out.println(9);
			stmt.setString(8, datos.getPrecio());
//			System.out.println(10);
			stmt.setString(9, datos.getPorDescuento());
//			System.out.println(11);
			stmt.setString(10, datos.getDescuento());
//			System.out.println(12);
			stmt.setString(11, datos.getTotal());
//			System.out.println(13);
			stmt.setInt(12, Integer.parseInt(datos.getCodigoCliente()));
//			System.out.println(14);
			stmt.setInt(13, Integer.parseInt(datos.getPromo()));
//			System.out.println(15);
			stmt.setString(14, datos.getBodega());
//			System.out.println(16);
			stmt.setInt(15, Integer.parseInt(datos.getEnvia()));
//			System.out.println(17);
			stmt.setString(16, datos.getObservaciones());
//			System.out.println(18);
			stmt.setInt(17, Integer.parseInt(datos.getLista()));
//			System.out.println(19);
			stmt.setInt(18, Integer.parseInt(datos.getPago()));
//			System.out.println(20);
			stmt.setString(19, datos.getKit());
//			System.out.println(21);
			stmt.setInt(20, Integer.parseInt(datos.getCorrKit()));
//			System.out.println(22);
			stmt.setInt(21, Integer.parseInt(datos.getCodPromo()));
//			System.out.println(23);
			stmt.setString(22, datos.getSerieDevProy());
//			System.out.println(24);
			stmt.setString(23, datos.getNumeroDevProy());
//			System.out.println(25);
			stmt.setInt(24, Integer.parseInt(datos.getOrdenCompra()));
//			System.out.println(26);
			rs = stmt.executeQuery();
//			System.out.println(27);
			while(rs.next()){
//				System.out.println(28);
				System.out.println("Detalle de producto guardado " + datos.getCodigoProducto());
			}
//			System.out.println(29);
			con.close();
			stmt.close();
			rs.close();
			
//			con = new ConectarDB().getConnection();
//			stmt = con.prepareCall("{call stp_udpv_VerificaCotiza(?,?)}");
//			stmt.setString(1, "");
//			stmt.setString(2, datos.getNoDoc());
//			rs = stmt.executeQuery();
//			
//			while(rs.next()){
//				System.out.println("Det " + rs.getString("error"));
//			}
//			con.close();
//			stmt.close();
//			rs.close();
			
		}catch(SQLException e ){
			datos.setMensaje("Error Det: " + e.getMessage());
		}
		return datos;
	}
	
}
