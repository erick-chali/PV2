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
import com.imsa.puntoventa.datos.DatosBusquedaClientes;

/**
 * Servlet implementation class CargarBusquedaClientes
 */
@WebServlet("/CargarBusquedaClientes")
public class CargarBusquedaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarBusquedaClientes() {
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
		request.getSession().setAttribute("criterioBusquedaClientes", request.getParameter("criterioBusquedaClientes"));
		request.getSession().setAttribute("textoBusquedaClientes", request.getParameter("textoBusquedaClientes"));
		
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosBusquedaClientes> datosClientes = new ArrayList<>();
		
		if(Integer.parseInt((String)request.getSession().getAttribute("criterioBusquedaClientes")) == 0){
			try{
				con = new ConectarDB().getConnection();
				stmt = con.prepareCall("{call stp_UDPV_LookUp_Clientes_FilterNomb_New(?)}");
				stmt.setString(1, (String)request.getSession().getAttribute("textoBusquedaClientes"));
				rs = stmt.executeQuery();
				while(rs.next()){
					DatosBusquedaClientes datos = new DatosBusquedaClientes();
					datos.setNit(rs.getString("Nit"));
					datos.setNombre(rs.getString("Nombre"));
					datosClientes.add(datos);
				}
			}catch(SQLException e){
				response.setContentType("application/json");
				response.getWriter().print(e.getMessage());
			}finally{
				if(con != null){
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						response.setContentType("application/json");
						response.getWriter().print(e.getMessage());
					}
				}
				if(stmt != null){
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						response.setContentType("application/json");
						response.getWriter().print(e.getMessage());
					}
				}
				if(rs != null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						response.setContentType("application/json");
						response.getWriter().print(e.getMessage());
					}
				}
			}
		}else if(Integer.parseInt((String)request.getSession().getAttribute("criterioBusquedaClientes")) == 1){
			try{
				con = new ConectarDB().getConnection();
				stmt = con.prepareCall("{call stp_UDPV_LookUp_Clientes_FilterNit_New(?)}");
				stmt.setString(1, (String)request.getSession().getAttribute("textoBusquedaClientes"));
				rs = stmt.executeQuery();
				while(rs.next()){
					DatosBusquedaClientes datos = new DatosBusquedaClientes();
					datos.setNit(rs.getString("Nit"));
					datos.setNombre(rs.getString("Nombre"));
					datosClientes.add(datos);
				}
			}catch(SQLException e){
				response.setContentType("application/json");
				response.getWriter().print(e.getMessage());
			}finally{
				if(con != null){
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						response.setContentType("application/json");
						response.getWriter().print(e.getMessage());
					}
				}
				if(stmt != null){
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						response.setContentType("application/json");
						response.getWriter().print(e.getMessage());
					}
				}
				if(rs != null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						response.setContentType("application/json");
						response.getWriter().print(e.getMessage());
					}
				}
			}
		}
		
		
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(datosClientes, new TypeToken<List<DatosBusquedaClientes>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		datosClientes = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}
