package com.imsa.puntoventa.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import com.imsa.puntoventa.datos.DatosRespuestasVarias;

/**
 * Servlet implementation class CargarDocumentoPDF
 */
@WebServlet("/CargarDocumentoPDF")
public class CargarDocumentoPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDocumentoPDF() {
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
		System.out.println("Cargar pdf ejecutado!");
		request.getSession().setAttribute("nombreDocumento", request.getParameter("nombreDocumento"));
		System.out.println("nombre archivo" + (String)request.getSession().getAttribute("nombreDocumento"));
//		String pdfFileName = "pdf-test.pdf";
//		String contextPath = getServletContext().getRealPath(File.separator);
//		File pdfFile = new File("C:\\ReportesGenerados\\"+(String)request.getSession().getAttribute("nombreDocumento")+".pdf");
//		response.setContentType("application/json");
//		response.addHeader("Content-Disposition", "attachment; filename=" + (String)request.getSession().getAttribute("nombreDocumento"));
//		response.setContentLength((int) pdfFile.length());
//
//		FileInputStream fileInputStream = new FileInputStream(pdfFile);
//		OutputStream responseOutputStream = response.getOutputStream();
//		int bytes;
//		while ((bytes = fileInputStream.read()) != -1) {
//			responseOutputStream.write(bytes);
//		}
//		fileInputStream.close();
		ArrayList<DatosRespuestasVarias> respuesta = new ArrayList<>();
		
		try {
	        // Convert a byte array to base64 string
	        byte[] buf = fileToByteArray("C:\\ReportesGenerados\\"+(String)request.getSession().getAttribute("nombreDocumento")+".pdf");
	        String s = new sun.misc.BASE64Encoder().encode(buf);
	        DatosRespuestasVarias datos = new  DatosRespuestasVarias();
	        datos.setMensaje(s);
	        System.out.println("-----");
	        System.out.println(s);
	        System.out.println("-----");
	        respuesta.add(datos);
	        
	        Gson gson = new Gson();
			JsonElement elemento = gson.toJsonTree(respuesta, new TypeToken<List<DatosRespuestasVarias>>(){}.getType());
			JsonArray arreglo = elemento.getAsJsonArray();
			respuesta = null;
			response.setContentType("application/json");
			
			response.getWriter().print(arreglo);
	        // Convert base64 string to a byte array
	        buf = new sun.misc.BASE64Decoder().decodeBuffer(s);
	        // Yout got it - now process it!
	    } catch (IOException e) {
	    }
	}
	public static byte[] fileToByteArray(String fileName) {
	    try {
	      File f = new File(fileName);
	      FileInputStream in = new FileInputStream(f);
	      byte[] bytes = new byte[(int)f.length()];
	      int c = -1;
	      int ix = 0;
	      while ((c = in.read()) > -1) {
	        System.out.println(c);
	        bytes[ix] = (byte)c;
	        ix++;
	      }
	      in.close();
	      return bytes;
	    }
	    catch(IOException e){
	      e.printStackTrace();
	      return null;
	    }
	  }
	
}
