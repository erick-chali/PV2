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
			stmt.setInt(2, Integer.parseInt(datos.getLista()));
			rs = stmt.executeQuery();
			while(rs.next()){
				unidad = rs.getString("unidad_medida");
			}
			con.close();
			stmt.close();
			rs.close();
			
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_InUp_Mov_Det(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setInt(1, Integer.parseInt(datos.getTipoDoc()));
			stmt.setString(2, datos.getSerieDoc());
			System.out.println(datos.getNoDoc());
			stmt.setString(3, datos.getNoDoc());
			stmt.setInt(4, Integer.parseInt(datos.getCorrelativo()));
			stmt.setString(5, datos.getCodigoProducto());
			stmt.setString(6, unidad);
			stmt.setInt(7, Integer.parseInt(datos.getCantidad()));
			stmt.setString(8, datos.getPrecio());
			stmt.setString(9, datos.getPorDescuento());
			stmt.setString(10, datos.getDescuento());
			stmt.setString(11, datos.getTotal());
			stmt.setInt(12, Integer.parseInt(datos.getCodigoCliente()));
			stmt.setInt(13, Integer.parseInt(datos.getPromo()));
			stmt.setString(14, datos.getBodega());
			stmt.setInt(15, Integer.parseInt(datos.getEnvia()));
			stmt.setString(16, datos.getObservaciones());
			stmt.setInt(17, Integer.parseInt(datos.getLista()));
			stmt.setInt(18, Integer.parseInt(datos.getPago()));
			stmt.setString(19, datos.getKit());
			stmt.setInt(20, Integer.parseInt(datos.getCorrKit()));
			stmt.setInt(21, Integer.parseInt(datos.getCodPromo()));
			stmt.setString(22, datos.getSerieDevProy());
			stmt.setString(23, datos.getNumeroDevProy());
			stmt.setInt(24, Integer.parseInt(datos.getOrdenCompra()));
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				datos.setMensaje("Detalle de producto guardado " + datos.getCodigoProducto());
				System.out.println("Detalle de producto guardado " + datos.getCodigoProducto());
			}
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
			e.printStackTrace();
			datos.setMensaje("Error Det: " + e.getMessage());
		}
		return datos;
	}
	
}
