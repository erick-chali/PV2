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
import com.imsa.puntoventa.datos.DatosBodegas;
import com.imsa.puntoventa.datos.DatosNIT;

/**
 * Servlet implementation class CargarBodegas
 */
@WebServlet("/CargarBodegas")
public class CargarBodegas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarBodegas() {
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
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosBodegas> bodegas = new ArrayList<>();
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_PVCargarBodegas_New}");
			rs = stmt.executeQuery();
			while(rs.next()){
				DatosBodegas datos = new DatosBodegas();
				datos.setCodigoBodega(rs.getString("codigo_bodega"));
				datos.setDescripcion(rs.getString("descripcion"));
				bodegas.add(datos);
			}
			con.close();
			rs.close();
			stmt.close();
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(bodegas, new TypeToken<List<DatosNIT>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		bodegas = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}
