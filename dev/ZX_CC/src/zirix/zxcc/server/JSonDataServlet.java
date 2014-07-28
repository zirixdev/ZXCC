/*ZIRIX CONTROL CENTER - MAIN PAGE
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/JSonData"})

public class JSonDataServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		String code = request.getParameter("CODE");
	    HttpSession session = request.getSession(true);
		
		switch (code) {
		case "DOC":
			String[] myJsonDoc = request.getParameterValues("json[]");
		    session.setAttribute("jSonDocumento", myJsonDoc);
		    /*for(int i = 0; i< myJsonDoc.length; i++)
		    	System.err.println("myJsonDoc["+ i +"] = " + myJsonDoc[i]);*/
			break;
		case "END":
			String[] myJsonEnd = request.getParameterValues("json[]");
		    session.setAttribute("jSonEndereco", myJsonEnd);
		    /*for(int i = 0; i< myJsonEnd.length; i++)
		    	System.err.println("myJsonEnd["+ i +"] = " + myJsonEnd[i]);*/
			break;
		case "CONTATO":
			String[] myJsonContato = request.getParameterValues("json[]");
		    session.setAttribute("jSonContato", myJsonContato);
		    /*for(int i = 0; i< myJsonContato.length; i++)
		    	System.err.println("myJsonContato["+ i +"] = " + myJsonContato[i]);*/
			break;
		case "EMAIL":
			String[] myJsonEmail = request.getParameterValues("json[]");
		    session.setAttribute("jSonEmail", myJsonEmail);
		    /*for(int i = 0; i< myJsonEmail.length; i++)
		    	System.err.println("\n JSonDataServlet.myJsonEmail["+ i +"] = " + myJsonEmail[i]);*/
			break;

		default:
			break;
		}
	}
}