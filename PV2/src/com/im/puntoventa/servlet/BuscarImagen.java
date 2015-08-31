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

import com.im.puntoventa.conexion.ConectarDB;

/**
 * Servlet implementation class BuscarImagen
 */
@WebServlet("/BuscarImagen")
public class BuscarImagen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarImagen() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codigoP = null;
		request.getSession().setAttribute("codigoProducto", request.getParameter("codigoProducto"));
		codigoP = (String) request.getSession().getAttribute("codigoProducto");
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html");
		PrintWriter respuesta = response.getWriter();
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_PVBuscaImgProducto(?)}");
			stmt.setString(1, codigoP);
			rs = stmt.executeQuery();
			while(rs.next()){
				respuesta.println(rs.getString("Imagen"));
			}
		}catch(SQLException e){
			respuesta.println("Error: " + e.getMessage());
		}
	}

}
