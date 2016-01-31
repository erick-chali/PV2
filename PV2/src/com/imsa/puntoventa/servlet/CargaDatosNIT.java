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
import com.imsa.puntoventa.datos.DatosNIT;

/**
 * Servlet implementation class CargaDatosNIT
 */
@WebServlet("/CargaDatosNIT")
public class CargaDatosNIT extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaDatosNIT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("nit", request.getParameter("nit"));
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosNIT> datosNIT = new ArrayList<DatosNIT>();
		
		try{
			con  = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_Get_CreditoCliente_New(?)}");
			stmt.setString(1, (String)request.getSession().getAttribute("nit"));
			rs = stmt.executeQuery();
			
			while(rs.next()){
				if(rs.getString("Nombre")!=null){
					DatosNIT dato = new DatosNIT();
					dato.setCodigoCliente(rs.getInt("Codigo"));
					request.getSession().setAttribute("codigoCliente", rs.getString("Codigo"));
					dato.setNombreCliente(rs.getString("Nombre"));
					dato.setLimiteCredito(rs.getFloat("Limite_Credito"));
					dato.setTelefono(rs.getString("Telefono"));
					dato.setDireccionFacturacion(rs.getString("DireccionFact"));
					dato.setDireccionEnvio(rs.getString("DireccionEnv"));
					dato.setTipoCliente(rs.getInt("tipo_cliente"));
					request.getSession().setAttribute("tipoCliente", rs.getString("tipo_cliente"));
					dato.setTarjeta(rs.getString("Tarjeta"));
					request.getSession().setAttribute("noTarjeta", rs.getString("Tarjeta"));
					if(rs.getString("Saldo")==null){
						request.getSession().setAttribute("saldoCliente", "0");
					}else{
						request.getSession().setAttribute("saldoCliente", rs.getString("Saldo"));
					}
					
					datosNIT.add(dato);
				}
				
			}
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(datosNIT, new TypeToken<List<DatosNIT>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		datosNIT = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}
