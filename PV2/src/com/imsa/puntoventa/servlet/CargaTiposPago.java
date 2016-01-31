package com.imsa.puntoventa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.im.puntoventa.conexion.ConectarDB;
import com.imsa.puntoventa.datos.DatosTiposPago;

/**
 * Servlet implementation class CargaTiposPago
 */
@WebServlet("/CargaTiposPago")
public class CargaTiposPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaTiposPago() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("codigoPago", request.getParameter("codigoPago"));
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosTiposPago> datosPago = new ArrayList<DatosTiposPago>();
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_pv_traerPago_New(?)}");
			stmt.setInt(1, Integer.parseInt((String)request.getSession().getAttribute("codigoPago")));
			rs = stmt.executeQuery();
			
			while(rs.next()){
				DatosTiposPago dato = new DatosTiposPago();
				dato.setIdPago(rs.getInt("Codigo"));
				dato.setDescripcion(rs.getString("Descripcion"));
				dato.setEsCredito(rs.getString("Credito"));
				datosPago.add(dato);
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(datosPago, new TypeToken<List<DatosTiposPago>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		datosPago = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}
