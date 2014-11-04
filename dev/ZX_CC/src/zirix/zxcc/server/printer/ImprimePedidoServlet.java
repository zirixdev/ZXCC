package zirix.zxcc.server.printer;

import javax.servlet.*; 
import javax.servlet.http.*; 
import java.util.*; 
import java.io.*;


import antena.printer.*;

public class ImprimePedidoServlet extends HttpServlet { 

  public void doGet(HttpServletRequest request, 
    HttpServletResponse response) throws ServletException { 
 
    try {

        ByteArrayOutputStream output = new ByteArrayOutputStream();

	Vector<String> txtList = new Vector<String>();
	txtList.add("balba");
	txtList.add("balbal");
	txtList.add("balbabal");
	txtList.add("balbalbal");

        output = ADPDFCreator.createPDF(null,null,ADFont.HELVETICA,txtList);

	response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=yourFile.pdf");
        response.getOutputStream().write(output.toByteArray());


/*	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println("<h1>" + "YEAH" + "</h1>");*/

    } catch (Exception ex) {            
        ex.printStackTrace();
    }   
  }
} 
