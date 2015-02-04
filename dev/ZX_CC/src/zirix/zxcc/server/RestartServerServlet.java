/*ZIRIX CONTROL CENTER - RESTART SERVLET
DESENVOLVIDO POR RAPHAEL B. MARQUES

CLIENTE: ZIRIX SOLUCOES EM RASTREAMENTO LTDA.
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name="RestartServerServlet", urlPatterns = {"/services/restart"}, loadOnStartup=1)
public class RestartServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String key = request.getParameter("request");
		if(key.compareTo("REITEAMSYSTEMS") == 0){
			try{
				Runtime.getRuntime().exec("sudo service mysql restart");
			}catch(IOException e){
				System.err.println("exception happened mysql");
				response.sendRedirect("http://127.0.0.1:8080/zxcc/5001.html");
				e.printStackTrace();
			}
			try{
				Runtime.getRuntime().exec("sudo service tomcat7 restart");
			}catch(IOException e){
				System.err.println("exception happened tomcat7");
				response.sendRedirect("http://127.0.0.1:8080/zxcc/5002.html");
				e.printStackTrace();
			}
			response.sendRedirect(ZXMain.URL_ADRESS_);
		}else{
			response.sendRedirect("http://127.0.0.1:8080/zxcc/500.html");
		}
	}
}
