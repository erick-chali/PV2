package com.imsa.puntoventa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.im.puntoventa.conexion.ConectarDB;

/**
 * Servlet implementation class GrabarEncabezado
 */
@WebServlet("/GrabarEncabezado")
public class GrabarEncabezado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrabarEncabezado() {
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
		request.getSession().setAttribute("nit", request.getParameter("nit"));
		request.getSession().setAttribute("nombreCliente", request.getParameter("nombreCliente"));
	  	request.getSession().setAttribute("direccionFactura", request.getParameter("direccionFactura"));
	  	request.getSession().setAttribute("telefono", request.getParameter("telefono"));
	  	request.getSession().setAttribute("direccionEnvio", request.getParameter("direccionEnvio"));
	  	request.getSession().setAttribute("tipoDocumento", request.getParameter("tipoDocumento"));
	  	System.out.println("tipo Doc: " + (String)request.getSession().getAttribute("tipoDocumento"));
	  	request.getSession().setAttribute("noDoc", request.getParameter("noDoc"));
	  	request.getSession().setAttribute("fechaVence", request.getParameter("fechaVence"));
	  	request.getSession().setAttribute("tipoPago", request.getParameter("tipoPago"));
	  	request.getSession().setAttribute("tipoCredito", request.getParameter("tipoCredito"));
	  	request.getSession().setAttribute("autoriza", request.getParameter("autoriza"));
	  	request.getSession().setAttribute("cargosEnvio", request.getParameter("cargosEnvio"));
	  	request.getSession().setAttribute("otrosCargos", request.getParameter("otrosCargos"));
	  	request.getSession().setAttribute("montoVenta", request.getParameter("montoVenta"));
	  	request.getSession().setAttribute("montoTotal", request.getParameter("montoTotal"));
	  	request.getSession().setAttribute("serieDev", request.getParameter("serieDev"));
	  	request.getSession().setAttribute("noDocDev", request.getParameter("noDocDev"));
	  	request.getSession().setAttribute("observaciones", request.getParameter("observaciones"));
	  	request.getSession().setAttribute("tipoNota", request.getParameter("tipoNota"));
	  	request.getSession().setAttribute("caja", request.getParameter("caja"));
	  	request.getSession().setAttribute("fechaEntrega", request.getParameter("fechaEntrega"));
	  	request.getSession().setAttribute("codDept", request.getParameter("codDept"));
	  	request.getSession().setAttribute("codGen", request.getParameter("codGen"));
	  	request.getSession().setAttribute("noConsigna", request.getParameter("noConsigna"));
	  	request.getSession().setAttribute("codMovDev", request.getParameter("codMovDev"));
	  	request.getSession().setAttribute("generaSolicitud", request.getParameter("generaSolicitud"));
	  	request.getSession().setAttribute("tipoPagoNC", request.getParameter("tipoPagoNC"));
	  	request.getSession().setAttribute("cantidadDevolver", request.getParameter("cantidadDevolver"));
	  	request.getSession().setAttribute("autorizoDespacho", request.getParameter("autorizoDespacho"));
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_InUp_Mov_Enc_New(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setInt(1, Integer.parseInt((String)request.getSession().getAttribute("codigoCliente")));
			stmt.setString(2, (String)request.getSession().getAttribute("nit"));
			stmt.setString(3, (String)request.getSession().getAttribute("nombreCliente"));
			stmt.setString(4, (String)request.getSession().getAttribute("direccionFactura"));
			stmt.setString(5, (String)request.getSession().getAttribute("telefono"));
			stmt.setString(6, (String)request.getSession().getAttribute("noTarjeta"));
			stmt.setString(7, (String)request.getSession().getAttribute("direccionEnvio"));
			stmt.setString(8, (String)request.getSession().getAttribute("codigoVendedor"));
			stmt.setString(9, (String)request.getSession().getAttribute("usuarioID"));
			stmt.setInt(10, Integer.parseInt((String)request.getSession().getAttribute("tipoDocumento")));
			stmt.setString(11, (String)request.getSession().getAttribute("noDocumento"));
			Date fechaVence = new Date();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			try {
				fechaVence = formatoFecha.parse((String)request.getSession().getAttribute("fechaVence"));
				stmt.setDate(12, new java.sql.Date(fechaVence.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				response.setContentType("application/json");
				response.getWriter().print(e.getMessage());
			}
//			stmt.setString(12, (String)request.getSession().getAttribute("fechaVence"));
			stmt.setInt(13, Integer.parseInt((String)request.getSession().getAttribute("tipoPago")));
			stmt.setInt(14, Integer.parseInt((String)request.getSession().getAttribute("codigoPago")));
			stmt.setString(15, (String)request.getSession().getAttribute("autoriza"));
			Date fechaDocumento = new Date();
			stmt.setDate(16, new java.sql.Date(fechaDocumento.getTime()));
			stmt.setFloat(17, Float.parseFloat((String)request.getSession().getAttribute("cargosEnvio")));
			stmt.setFloat(18, Float.parseFloat((String)request.getSession().getAttribute("otrosCargos")));
			stmt.setFloat(19, Float.parseFloat((String)request.getSession().getAttribute("montoVenta")));
			stmt.setFloat(20, Float.parseFloat((String)request.getSession().getAttribute("montoTotal")));
			stmt.setString(21, (String)request.getSession().getAttribute("serieDev"));
			stmt.setString(22, (String)request.getSession().getAttribute("noDocDev"));
			stmt.setString(23, (String)request.getSession().getAttribute("observaciones"));
			stmt.setInt(24, Integer.parseInt((String)request.getSession().getAttribute("tipoNota")));
			stmt.setInt(25, Integer.parseInt((String)request.getSession().getAttribute("caja")));
			Date fechaEntrega = new Date();
			formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			try {
				fechaEntrega = formatoFecha.parse((String)request.getSession().getAttribute("fechaEntrega"));
				stmt.setDate(26, new java.sql.Date(fechaEntrega.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				response.setContentType("application/json");
				response.getWriter().print(e.getMessage());
			}
			stmt.setString(27, (String)request.getSession().getAttribute("codDept"));
			stmt.setString(28, (String)request.getSession().getAttribute("codGen"));
			stmt.setString(29, (String)request.getSession().getAttribute("noConsigna"));
			stmt.setInt(30, Integer.parseInt((String)request.getSession().getAttribute("codMovDev")));
			stmt.setString(31, (String)request.getSession().getAttribute("generaSolicitud"));
			stmt.setInt(32, Integer.parseInt((String)request.getSession().getAttribute("tipoPagoNC")));
			stmt.setInt(33, Integer.parseInt((String)request.getSession().getAttribute("tipoCliente")));
			stmt.setString(34, (String)request.getSession().getAttribute("codigoNegocio"));
			stmt.setFloat(35, Float.parseFloat((String)request.getSession().getAttribute("cantidadDevolver")));
			stmt.setString(36, (String)request.getSession().getAttribute("autorizoDespacho"));
			stmt.setFloat(37, Float.parseFloat((String)request.getSession().getAttribute("saldoCliente")));
			rs = stmt.executeQuery();
			while(rs.next()){
				request.getSession().setAttribute("numeroDocumento", rs.getString(2));
				request.getSession().setAttribute("serieDocumento", rs.getString(1));
				System.out.println("Generado: " + (String)request.getSession().getAttribute("numeroDocumento"));
				response.setContentType("text/html");
				response.getWriter().write(rs.getString(2));
			}
			con.close();
			stmt.close();
			rs.close();
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}finally{
			
//			if(con != null || stmt != null || rs != null){
//				try {
//					con.close();
//					stmt.close();
//					rs.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					response.setContentType("application/json");
//					response.getWriter().print(e.getMessage());
//				}
//			}
		}
		
	}

}
