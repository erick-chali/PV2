package com.imsa.puntoventa.servlet;

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
 * Servlet implementation class ValidaPermisos
 */
@WebServlet("/ValidaPermisos")
public class ValidaPermisos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidaPermisos() {
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
		request.getSession().setAttribute("usuario", request.getParameter("usuario"));
		request.getSession().setAttribute("clave", request.getParameter("clave"));
		request.getSession().setAttribute("operacion", request.getParameter("operacion"));
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call Stp_UdPv_ValClaves_New(?,?,?)}");
			stmt.setString(1, (String)request.getSession().getAttribute("usuario"));
			stmt.setString(2, (String)request.getSession().getAttribute("clave"));
			stmt.setInt(3, Integer.parseInt((String)request.getSession().getAttribute("operacion")));
			rs = stmt.executeQuery();
			while(rs.next()){
				response.setContentType("text/html");
				response.getWriter().print(rs.getString(1));
			}
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}
	}

}
