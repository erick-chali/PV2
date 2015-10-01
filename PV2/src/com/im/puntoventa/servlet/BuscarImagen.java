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
    
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codigoP = null;
		request.getSession().setAttribute("codigoProducto", request.getParameter("codigoProducto"));
		codigoP = (String) request.getSession().getAttribute("codigoProducto");
		System.out.println("Codigo producto" + codigoP);
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		String imagen = null;

		
		
		try{
			
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_PVBuscaImgProducto(?)}");
			stmt.setString(1, codigoP);
			rs = stmt.executeQuery();
			while(rs.next()){
				imagen = rs.getString("Imagen");
				System.out.println(imagen);
			}
			if(imagen!=null){
				PrintWriter respuesta = response.getWriter();
				respuesta.write(imagen);
				System.out.println("Imagen: " + imagen);
			}else if(imagen == null){
				imagen = "0";
				PrintWriter respuesta = response.getWriter();
				respuesta.write(imagen);
				System.out.println("Imagen: " + imagen);
			}
			stmt.close();
			con.close();
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
	}

}
