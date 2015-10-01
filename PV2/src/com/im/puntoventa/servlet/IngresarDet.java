package com.im.puntoventa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.im.puntoventa.conexion.ConectarDB;

/**
 * Servlet implementation class IngresarDet
 */
@WebServlet("/IngresarDet")
public class IngresarDet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngresarDet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int registros = 0;
		request.getSession().setAttribute("datosTabla", request.getParameter("datos"));
		JsonParser parser = new JsonParser();
		JsonElement arreglo = parser.parse((String)request.getSession().getAttribute("datosTabla"));
		registros = arreglo.getAsJsonArray().size();
		
		for(int x =0; x < registros; x++){
			Connection con = null;
			CallableStatement stmt = null;
			ResultSet rs = null;
			String unidad;
			unidad = null;
			try{
				con = new ConectarDB().getConnection();
				stmt = con.prepareCall("{call stp_obtenerMedida(?,?)}");
				stmt.setString(1, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("codigoProducto").getAsString());
				stmt.setInt(2, 1);
				rs = stmt.executeQuery();
				while(rs.next()){
					unidad = rs.getString("unidad_medida");
				}
				rs = null;
				con = new ConectarDB().getConnection();
				stmt = con.prepareCall("{call stp_UDPV_InUp_Mov_Det(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				stmt.setInt(1, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("tipoDocumento").getAsInt());
				stmt.setString(2, (String) request.getSession().getAttribute("serieDoc"));
				stmt.setString(3, (String)request.getSession().getAttribute("numeroDocumento"));
				stmt.setInt(4, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("correlativo").getAsInt());
				stmt.setString(5, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("codigoProducto").getAsString());
				stmt.setString(6, unidad);
				stmt.setInt(7, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("cantidad").getAsInt());
				stmt.setString(8, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("precio").getAsString());
				stmt.setString(9, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("porcentaje").getAsString());
				stmt.setString(10, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("descuento").getAsString());
				stmt.setString(11, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("importe").getAsString());
				stmt.setInt(12, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("codigoCliente").getAsInt());
				stmt.setInt(13, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("promo").getAsInt());
				stmt.setString(14, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("bodega").getAsString());
				stmt.setInt(15, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("envio").getAsInt());
				stmt.setString(16, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("observaciones").getAsString());
				stmt.setInt(17, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("lista").getAsInt());
				stmt.setInt(18, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("pago").getAsInt());
				stmt.setString(19, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("kit").getAsString());
				stmt.setInt(20, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("corrKit").getAsInt());
				stmt.setInt(21, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("codPromo").getAsInt());
				stmt.setString(22, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("serieDevProy").getAsString());
				stmt.setString(23, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("numDevProy").getAsString());
				stmt.setInt(24, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("ordenCompra").getAsInt());
				rs = stmt.executeQuery();
				
				while(rs.next()){
					PrintWriter respuesta = response.getWriter();
					respuesta.println("Guardados " + arreglo.getAsJsonArray().size() + " registros en el documento: " + (String)request.getSession().getAttribute("numeroDocumento"));
				}
			}catch(SQLException e ){
				PrintWriter respuesta = response.getWriter();
				respuesta.print("Error al ingresar producto " +arreglo.getAsJsonArray().get(x).getAsJsonObject().get("codigoProducto").getAsString()+" a documento: " + e.getMessage());
			}
			
		}
		
		
	}

}
