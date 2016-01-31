package com.im.puntoventa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.im.puntoventa.conexion.ConectarDB;

/**
 * Servlet implementation class ProductosAlternos
 */
@WebServlet("/ProductosAlternos")
public class ProductosAlternos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosAlternos() {
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
		request.getSession().setAttribute("codigoProducto", request.getParameter("codigoProducto"));
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		String hayDefectuoso;
		hayDefectuoso = null;
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_udpv_proBuscaDefectuoso(?)}");
			stmt.setString(1, (String)request.getSession().getAttribute("codigoProducto"));
			rs = stmt.executeQuery();
			while(rs.next()){
				hayDefectuoso = rs.getString("cuenta");
			}
			con.close();
			stmt.close();
			rs.close();
		}catch(SQLException e){
			response.setContentType("text/html");
			response.getWriter().write(e.getMessage());
			System.out.println("Error: " + e.getMessage());
		}
		response.setContentType("text/html");
		response.getWriter().write(hayDefectuoso);
	}

}
