package com.im.puntoventa.servlet;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.im.puntoventa.conexion.NombreDB;
import com.im.puntoventa.dao.ImplementaLogin;
import com.im.puntoventa.dao.InterLogin;
import com.im.puntoventa.datos.DatosLogin;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatosLogin datos= new DatosLogin();
		InterLogin interfaz = new ImplementaLogin();
		
		HttpSession sesion = request.getSession();
		sesion.setAttribute("usuario", (String) request.getParameter("usuario"));
		sesion.setAttribute("clave", (String) request.getParameter("clave"));
		
		datos.setUsuario((String) sesion.getAttribute("usuario"));
		datos.setClave((String) sesion.getAttribute("clave"));
		
		datos = interfaz.validaUsuario(datos);
		
		if(datos.getRespuesta()==1){
			String nombres = null;
			NombreDB nombreDB = new NombreDB();
			nombres = nombreDB.obtenerNombreDb();
			sesion.setAttribute("sucursal", datos.getSucursal());
			sesion.setAttribute("codSucursal", datos.getCodSucursal());
			String textoSucursal = (String) sesion.getAttribute("sucursal");
			String[] partes = textoSucursal.split("\\s+");
			sesion.setAttribute("codigoBodegaLocal", partes[0]);
			System.out.println("bodega Local: " + (String)request.getSession().getAttribute("codigoBodegaLocal"));
			sesion.setAttribute("vendedor", datos.getNombre());
			sesion.setAttribute("usuarioID", datos.getUsuarioID());
			sesion.setAttribute("codigoVendedor", datos.getCodigoVendedor());
			sesion.setAttribute("nombreEmpresa", datos.getNombreEmpresa());
			sesion.setAttribute("moneda", datos.getMoneda());
			sesion.setAttribute("nombreDB", nombres);
			sesion.setAttribute("codigoLista", "1");
			sesion.setMaxInactiveInterval(30*30);
			Date now = new Date();
			 
	        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
	        System.out.println(simpleDateformat.format(now));
	 
	        simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
	        System.out.println(simpleDateformat.format(now));
	 
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(now);
	        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); //
	        
	        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("es"));
		     // for the current Locale :
		     //   DateFormatSymbols symbols = new DateFormatSymbols(); 
		     String[] dayNames = symbols.getWeekdays();
		     for (String s : dayNames) { 
		        System.out.print(s + " ");
		     }
			response.sendRedirect("m.pv.jsp");
			
		}else if(datos.getRespuesta()==0){
			request.setAttribute("msjSesion", datos.getNotificacion());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}
