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
	txtList.add("balbalbal");

        output = ADPDFCreator.createPDF(null,null,ADFont.HELVETICA,txtList);

	response.setContentType("application/pdf");
        //response.setHeader("Content-Type", "application/force-download"); 
        response.setHeader("Content-Disposition", "attachment; filename=yourFile.pdf");
        response.getOutputStream().write(output.toByteArray());

    } catch (Exception ex) {            
        ex.printStackTrace();
    }   
  }
} 
