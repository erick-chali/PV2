package com.imsa.puntoventa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.imsa.puntoventa.datos.DatosMovimiento;

/**
 * Servlet implementation class CargarMovimiento
 */
@WebServlet("/CargarMovimiento")
public class CargarMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarMovimiento() {
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
		
		request.getSession().setAttribute("codigoMovimiento", request.getParameter("codigoMovimiento"));
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosMovimiento> datosMovimiento = new ArrayList<DatosMovimiento>();
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_Get_TipoMovimiento_New(?,?)}");
			stmt.setInt(1, Integer.parseInt((String)request.getSession().getAttribute("codigoMovimiento")));
			stmt.setInt(2, Integer.parseInt((String)request.getSession().getAttribute("usuarioID")));
			rs = stmt.executeQuery();
			while(rs.next()){
				DatosMovimiento dato = new DatosMovimiento();
				dato.setCodigoMovimiento(rs.getInt("codigo_movimiento"));
				dato.setMovimiento(rs.getString("Descripcion"));
				Connection con2 = null;
				CallableStatement stmt2 = null;
				ResultSet rs2 = null;
				con2 = new ConectarDB().getConnection();
				stmt2 = con2.prepareCall("{call stp_UDPV_Get_FechaVence_New(?)}");
				stmt2.setInt(1, rs.getInt("Tipo_Movimiento"));
				rs2 = stmt2.executeQuery();
				while(rs2.next()){
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					dato.setFechaFormateada(formato.format(rs2.getDate("FechaVence")));
				}
				datosMovimiento.add(dato);
				con2.close();
				stmt2.close();
				rs2.close();
			}
			con.close();
			stmt.close();
			rs.close();
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(datosMovimiento, new TypeToken<List<DatosMovimiento>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		datosMovimiento = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}
